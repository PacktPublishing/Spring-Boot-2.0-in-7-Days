package com.ebookstore.model;

import com.ebookstore.app.Person;

public class CustomerInfo {

	private Person customer;

	public CustomerInfo(Person p) {
		this.setCustomer(p);
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}
}
