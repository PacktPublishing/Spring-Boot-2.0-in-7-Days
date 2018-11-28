package com.ebookstore.model;

import javax.servlet.http.HttpServletRequest;

public class ShoppingCart {

	public static ShoppingCartInfo getShoppingCartInfo(HttpServletRequest request) {
		ShoppingCartInfo cart = (ShoppingCartInfo) request.getSession().getAttribute("shoppingCart");

		if (cart == null) {
			cart = new ShoppingCartInfo();
			request.getSession().setAttribute("shoppingCart", cart);
		}
		return cart;
	}

	public static void removeShoppingCart(HttpServletRequest request) {
		request.getSession().removeAttribute("shoppingCart");
	}

	public static ShoppingCartInfo getLastShoppingCartInSession(HttpServletRequest request) {
		return (ShoppingCartInfo) request.getSession().getAttribute("lastOrder");
	}

	public static void storeLastOrderedShoppingCart(HttpServletRequest request, ShoppingCartInfo cart) {
		request.getSession().setAttribute("lastOrder", cart);
	}
}
