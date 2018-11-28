/**
 * 
 */
package com.ebookstore.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Petra Simonis
 *
 */
@Entity
@Table(name = "person")
@JsonIgnoreProperties({ "createdBooks", "orders" })

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3075727814481272627L;
	private static final String ROLE_AUTHOR = "AUTHOR";
	private static final String ROLE_CUSTOMER = "CUSTOMER";

	@NotNull
	@Size(min = 1, message = "Username mustn't be empty")
	@Column(name = "username", length = 20, nullable = false)
	private String username;
	@NotNull
	@Size(min = 5, message = "password mustn't be empty and should have at least 5 characters")
	@Column(name = "password", length = 20, nullable = false)
	private String password;
	@NotNull
	@Size(min = 1, message = "firstname mustn't be empty")
	@Column(name = "firstname", length = 30, nullable = false)
	private String firstName;
	@NotNull
	@Size(min = 1, message = "lastname mustn't be empty")
	@Column(name = "lastname", length = 50, nullable = false)
	private String lastName;
	@Column(name = "userrole", length = 20, nullable = false)
	private String userRole;
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;

	@OneToMany(mappedBy = "author")
	private List<Book> createdbooks = new ArrayList();

	@OneToMany(mappedBy = "customer")
	private List<Order> orders = new ArrayList();

	public Person() {
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.userRole = "";
	}

	public Person(String username, String password, String firstName, String lastName, String userRole) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return username + " " + firstName + " " + lastName + " " + userRole;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCreatedBooks(List<Book> books) {
		this.createdbooks = books;
	}

	public List<Book> getCreatedBooks() {
		return createdbooks;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Order> getOrders() {
		return orders;
	}
}
