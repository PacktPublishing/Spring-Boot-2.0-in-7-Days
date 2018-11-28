package com.ebookstore.model;

public class ShoppingCartLineInfo {
	private BookInfo bookInfo;
	private int quantity;

	public ShoppingCartLineInfo() {
		this.setQuantity(0);
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSum() {
		return this.quantity * this.getBookInfo().getPrice();
	}
}
