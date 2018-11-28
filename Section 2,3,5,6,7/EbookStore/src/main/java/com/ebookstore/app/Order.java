package com.ebookstore.app;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Order() {
		this.id = 0;
		this.amount = 0;
		this.price = 0;
		this.orderdate = new Date(0);
		this.ordernum = 0;
	}

	public Order(long id, double amount, double price, Date orderdate, long ordernum, Person customer) {
		super();
		this.id = id;
		this.amount = amount;
		this.price = price;
		this.orderdate = orderdate;
		this.ordernum = ordernum;
		this.customer = customer;
	}

	@GeneratedValue
	@Id
	@Column(name = "id", length = 50, nullable = false)
	private long id;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "order_date", nullable = false)
	private Date orderdate;

	@Column(name = "order_num", nullable = false)
	private long ordernum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = true)
	private Person customer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public long getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(long ordernum) {
		this.ordernum = ordernum;
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}
}
