package com.ebookstore.app;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebookstore.error.BookNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(description = "RESTful webservice")
public class EBSRestController {

	@Autowired
	private BookRepository brepo;

	@Autowired
	PersonRepository prepo;

	public EBSRestController(BookRepository brepo) {
		this.brepo = brepo;
	}

	@ApiOperation(value = "retrieves all books", response = List.class)
	@RequestMapping(value = "/rest/books", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Book> getBooks() {
		return brepo.findAll();
	}

	@RequestMapping(value = "/rest/books/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "retrieves a certain book")

	public Optional<Book> getBook(@ApiParam("Id of the book") @PathVariable Long id) {
		Optional<Book> foundbook = brepo.findById(id);
		if (!foundbook.isPresent())
			throw new BookNotFoundException("id-" + id);
		return foundbook;
	}

	@RequestMapping(value = "/rest/books", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ApiResponses({ @ApiResponse(code = 201, message = "created") })
	public ResponseEntity<Object> newBook(@Valid @RequestBody Book newbook) {
		Book savedbook = brepo.save(newbook);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedbook.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@RequestMapping(value = "/rest/books/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	public void deleteBook(@PathVariable Long id) {
		brepo.deleteById(id);
	}

	@RequestMapping(value = "/rest/books/{id}", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateBook(@Valid @RequestBody Book book, @PathVariable long id) {
		Optional<Book> bookoptional = brepo.findById(id);

		if (!bookoptional.isPresent())

			throw new BookNotFoundException("id-" + id);

		book.setId(id);

		brepo.save(book);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/rest/persons", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	public List<Person> getAuthorsAndCustomers() {
		return prepo.findAll();
	}

	@RequestMapping(value = "/rest/persons/{id}", method = RequestMethod.GET)
	public Optional<Person> getPersonById(@PathVariable Long id) {
		return prepo.findById(id);
	}
}
