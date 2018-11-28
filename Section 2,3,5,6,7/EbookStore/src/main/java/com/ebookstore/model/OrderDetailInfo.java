package com.ebookstore.model;

public class OrderDetailInfo {

	private int quantity;

	private double price;
	private double amount;

	public OrderDetailInfo(int quantity, double price, double amount) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
