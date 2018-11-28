/**
 * 
 */
package com.ebookstore.app;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ebookstore.config.MyUserPrincipal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Petra Simonis
 *
 */
@Controller
@RequestMapping(path = "/books")
@Api(value = "creates and retrieves books")
public class BookController {

	@Autowired
	BookRepository bookrepository;

	BookServiceImpl bookservice = new BookServiceImpl();

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String getBooks(Model model, Principal p) {
		List<Book> allBooks = new ArrayList();
		if (p != null) {
			MyUserPrincipal myuserprincipal = (MyUserPrincipal) ((Authentication) p).getPrincipal();
			System.out.println("myuserprincipal " + myuserprincipal.getPerson().getUsername());

			if (myuserprincipal.getPerson() != null)
				allBooks = bookrepository.findBooksByAuthor(myuserprincipal.getPerson().getId());
			else
				allBooks = bookrepository.findAll();
			model.addAttribute("booklist", allBooks);
		} else {
			allBooks = bookrepository.findAll();
			model.addAttribute("booklist", allBooks);
		}
		return "books";
	}

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	@ApiOperation(value = "create a new book", response = String.class)
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		return "book";
	}

	@RequestMapping(path = "/new", method = RequestMethod.POST)
	public String newBookWithFile(@ModelAttribute Book book, @RequestParam("user-file") MultipartFile multipartFile,
			Principal principal) throws IOException {
		MyUserPrincipal myprincipal = (MyUserPrincipal) ((Authentication) principal).getPrincipal();
		book.setBfile(multipartFile.getBytes());
		book.setPath(multipartFile.getOriginalFilename());
		book.setAuthor(myprincipal.getPerson());
		bookrepository.save(book);

		return "redirect:/books/list";
	}

	@RequestMapping(path = "/update", method = RequestMethod.GET)
	public String getUpdateBookForm(Model model, @RequestParam(value = "id") Long id) {
		Optional<Book> book = bookrepository.findById(id);

		if (book.isPresent())
			model.addAttribute("book", book.get());

		return "updatebook";

	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute Book book) {
		bookservice.updateBook(book);
		return "redirect:/books/list";
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public String searchForBooks(Model model, @RequestParam(value = "searchstring") String search) {
		List<Book> allBooks = new ArrayList();
		allBooks = bookrepository.findBooksByTitle(search);
		model.addAttribute("booklist", allBooks);
		return "books";
	}
}
