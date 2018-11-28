package com.ebookstore.app;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebookstore.config.MyUserPrincipal;
import com.ebookstore.model.ShoppingCart;
import com.ebookstore.model.ShoppingCartInfo;

@Controller
public class EbookStoreController {
	@Autowired
	private PersonRepository personrepository;

	@Value("${spring.application.name}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@RequestMapping(path = "/")
	public String index(final Model model) {
		model.addAttribute("name", name);
		return "index";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {

		return "login";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		return "403";
	}

	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
	public String getAccountInfo(Model model, Principal principal) {
		MyUserPrincipal loginedUser = (MyUserPrincipal) ((Authentication) principal).getPrincipal();

		model.addAttribute("person", loginedUser.getPerson());
		return "accountInfo";
	}

	/*
	 * @RequestMapping(value="/shoppingCart", method=RequestMethod.GET) public
	 * String getShoppingCart(HttpServletRequest request, Model model) {
	 * 
	 * model.addAllAttributes(") return "shoppingCart"; }
	 */

}
