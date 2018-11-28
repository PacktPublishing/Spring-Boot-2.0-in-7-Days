package com.ebookstore.model;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private long id;
	private Date orderDate;
	private int orderNum;
	private double amount;
	private String customerName;

	private List<OrderDetailInfo> details;

	public OrderInfo() {

	}

	public OrderInfo(long id, Date orderDate, int orderNum, double amount, String customerName,
			List<OrderDetailInfo> details) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
		this.customerName = customerName;
		this.details = details;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<OrderDetailInfo> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetailInfo> details) {
		this.details = details;
	}
}
