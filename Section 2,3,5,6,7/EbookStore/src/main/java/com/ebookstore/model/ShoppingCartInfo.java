package com.ebookstore.model;

import java.util.ArrayList;
import java.util.List;
import com.ebookstore.model.CustomerInfo;
import com.ebookstore.model.ShoppingCartLineInfo;

public class ShoppingCartInfo {
	private long orderNum;

	private CustomerInfo customerInfo;

	private final List<ShoppingCartLineInfo> cartLines = new ArrayList<ShoppingCartLineInfo>();

	public ShoppingCartInfo() {

	}

	public long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<ShoppingCartLineInfo> getCartLines() {
		return cartLines;
	}

	public boolean isEmpty() {
		return this.cartLines.isEmpty();
	}

	public void addBook(BookInfo bookinfo, int amount) {

		ShoppingCartLineInfo lineinfo = this.getLineInfo(bookinfo.getId());

		if (lineinfo == null) {
			lineinfo = new ShoppingCartLineInfo();
			lineinfo.setBookInfo(bookinfo);
			lineinfo.setQuantity(0);
			cartLines.add(lineinfo);
		}

		int newamount = lineinfo.getQuantity() + amount;

		if (newamount <= 0) {
			cartLines.remove(lineinfo);
		}

		else
			lineinfo.setQuantity(newamount);
	}

	public ShoppingCartLineInfo getLineInfo(long id) {
		for (ShoppingCartLineInfo info : cartLines) {
			if (info.getBookInfo().getId() == id)
				return info;
		}
		return null;
	}

	public void updateBook(long id, int amount) {
		ShoppingCartLineInfo info = getLineInfo(id);
		if (info != null) {
			if (amount <= 0)
				cartLines.remove(info);
			else
				info.setQuantity(amount);
		}
	}

	public void removeBook(BookInfo bookinfo) {
		ShoppingCartLineInfo info = this.getLineInfo(bookinfo.getId());

		if (info != null)
			cartLines.remove(info);
	}

	public double getSumTotal() {
		double total = 0;
		for (ShoppingCartLineInfo line : this.cartLines) {
			total += line.getSum();
		}
		return total;
	}
}
