package com.ebookstore.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebookstore.config.MyUserPrincipal;
import com.ebookstore.model.BookInfo;
import com.ebookstore.model.CustomerInfo;
import com.ebookstore.model.ShoppingCart;
import com.ebookstore.model.ShoppingCartInfo;
import com.ebookstore.model.ShoppingCartLineInfo;

import java.util.zip.*;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

	@Autowired
	OrderRepository orderrepository;

	@Autowired
	OrderDetailRepository detailrepository;

	@Autowired
	BookRepository bookrepository;
	ZipOutputStream zipfile;

	@GetMapping("/buyBook")
	public String buyBook(HttpServletRequest request, Model model, @RequestParam("id") Long id) {
		Optional<Book> book = bookrepository.findById(id);

		if (book.isPresent()) {

			BookInfo bookinfo = new BookInfo(book.get());

			ShoppingCartInfo cartinfo = ShoppingCart.getShoppingCartInfo(request);

			cartinfo.addBook(bookinfo, 1);
		}

		return "redirect:/order/shoppingCart";
	}

	@GetMapping("/shoppingCart")
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		ShoppingCartInfo myCart = ShoppingCart.getShoppingCartInfo(request);

		if (myCart != null)
			model.addAttribute("shoppingCart", myCart);
		else
			model.addAttribute("shoppingCart", new ShoppingCartInfo());

		return "shoppingCart";
	}

	/*
	 * @PostMapping("/shoppingCart") public String shoppingCart(HttpServletRequest
	 * request, Model model) { ShoppingCartInfo cart =
	 * ShoppingCart.getShoppingCartInfo(request);
	 * 
	 * return "redirect:/order/shoppingCart"; }
	 */

	@RequestMapping(value = "/shoppingCartCounter", method = RequestMethod.GET)
	public String goToShoppingCartCounter(HttpServletRequest request, Model model, Principal principal) {
		ShoppingCartInfo cart = ShoppingCart.getShoppingCartInfo(request);

		if (cart == null || cart.isEmpty()) {
			return "redirect:/order/shoppingCart";
		}

		if (principal != null) {
			MyUserPrincipal myuserprincipal = (MyUserPrincipal) ((Authentication) principal).getPrincipal();
			CustomerInfo customer = new CustomerInfo(myuserprincipal.getPerson());
			cart.setCustomerInfo(customer);
		}
		if (cart.getCustomerInfo() == null) {
			return "redirect:/login";
		}

		model.addAttribute("shoppingCart", cart);
		return "shoppingCartCounter";
	}

	@PostMapping("/shoppingCartCounter")
	public String payAndDownload(HttpServletRequest request, Model model) {
		ShoppingCartInfo cart = ShoppingCart.getShoppingCartInfo(request);
		Order neworder;
		if (cart == null)
			return "redirect:/order/shoppingCart";

		if (cart.getCustomerInfo() == null || cart.getCustomerInfo().getCustomer() == null)
			return "redirect:/login";

		try {
			long id = orderrepository.getMaxId();
			long ordernum = orderrepository.getMaxOrdernumFromCustomer(cart.getCustomerInfo().getCustomer().getId());
			System.out.println(cart.getCustomerInfo().getCustomer().getId());
			Order order = new Order(id + 1, cart.getSumTotal(), 0, new Date(System.currentTimeMillis()), ordernum + 1,
					cart.getCustomerInfo().getCustomer());
			neworder = orderrepository.save(order);

			long maxid = detailrepository.getMaxId();
			zipfile = new ZipOutputStream(new FileOutputStream("ebooks.zip"));
			for (ShoppingCartLineInfo line : cart.getCartLines()) {

				maxid = maxid + 1;
				Optional<Book> b = bookrepository.findById(line.getBookInfo().getId());
				OrderDetail orderdetail = new OrderDetail(maxid, line.getQuantity(), line.getSum(), b.get(), neworder);
				detailrepository.save(orderdetail);

				ZipEntry entry = new ZipEntry(b.get().getPath());
				entry.setMethod(ZipEntry.DEFLATED);
				zipfile.putNextEntry(entry);
				zipfile.write(b.get().getBfile());
				zipfile.closeEntry();
			}
			zipfile.close();
			
			cart.setOrderNum(neworder.getOrdernum());

			model.addAttribute("zipfile", new String("ebooks.zip"));
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		ShoppingCart.storeLastOrderedShoppingCart(request, cart);
		ShoppingCart.removeShoppingCart(request);
		return "redirect:/order/order";
	}

	@GetMapping("/order")
	public String getOrder(HttpServletRequest request, Model model) {
		ShoppingCartInfo lastOrder = ShoppingCart.getLastShoppingCartInSession(request);

		if (lastOrder == null)
			return "redirect:/order/shoppingCart";
		model.addAttribute("lastOrder", lastOrder);
		return "order";
	}

	@GetMapping("/orderList")
	public String listOrders(HttpServletRequest request, Model model, Principal principal) {
		List<Order> orderlist = new ArrayList();
		if (principal != null) {
			MyUserPrincipal myuserprincipal = (MyUserPrincipal) ((Authentication) principal).getPrincipal();
			Person p = myuserprincipal.getPerson();
			orderlist = orderrepository.getOrdersByCustomer(p.getId());

		}

		model.addAttribute("orderlist", orderlist);
		return "orderList";
	}
	
	@GetMapping("/ebooks.zip")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		FileInputStream in = new FileInputStream("ebooks.zip");
		
		response.setContentType("application/zip");
		response.setHeader("Content-disposition", "attachment; filename=ebooks.zip");
	    
	    IOUtils.copy(in, response.getOutputStream());
	}
}
