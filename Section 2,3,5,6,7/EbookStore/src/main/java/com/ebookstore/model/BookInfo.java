package com.ebookstore.model;

import com.ebookstore.app.Book;

public class BookInfo {

	private String title;
	private String author;
	private double price;
	private long id;

	public BookInfo(Book book) {
		this.title = book.getTitle();
		this.author = book.getAuthor().getLastName() + ", " + book.getAuthor().getFirstName();
		this.price = book.getPrice();
		this.id = book.getId();
	}

	public BookInfo(String title, String author, double price, long id) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
