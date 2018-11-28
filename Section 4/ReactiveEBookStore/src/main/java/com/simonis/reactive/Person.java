/**
 * 
 */
package com.simonis.reactive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Petra Simonis
 *
 */
@Document(collection="authors")
public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3075727814481272627L;
	private static final String ROLE_AUTHOR="AUTHOR";
	private static final String ROLE_CUSTOMER = "CUSTOMER";
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String userRole;
	@Id
	private long id;
	

	private List<Book> books = new ArrayList<Book>();
	
	public Person()
	{
		this.username="";
		this.password="";
		this.firstName="";
		this.lastName="";
		this.userRole="";
	}
	
	@JsonCreator
	public Person(@JsonProperty("username") String username, 
			@JsonProperty("password")String password,
			@JsonProperty("firstName")String firstName, 
			@JsonProperty("lastName")String lastName, 
			@JsonProperty("userRole")String userRole,
			@JsonProperty("books") List<Book> books)
	{
		this.username=username;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
		this.userRole=userRole;
		this.books = books;
	
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
	
	public List<Book> getBooks(){
		return books;
	}
	
	public void setBooks(List<Book> books)
	{
		this.books = books;
	}
}
