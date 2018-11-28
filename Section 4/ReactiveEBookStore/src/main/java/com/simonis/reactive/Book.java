package com.simonis.reactive;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3638672164426743937L;
	
	@Id
	private long id;
	private String title;
	private String description;
	private double price;
	private Date publishDate;
	private String path;
	
	public Book()
	{
		this.title="";
		this.description="";
		this.price=0;
		this.publishDate=new Date();
		this.path="";
	}
	
	@JsonCreator
	public Book(@JsonProperty("title")String title,
			@JsonProperty("description") String description, 
			@JsonProperty("price")double price, 
			@JsonProperty("publishDate")Date date, 
			@JsonProperty("path")String path)
	{
		this.title=title;
		this.description=description;
		this.price=price;
		this.publishDate=date;
		this.path=path;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		boolean retval = true;
		if (this.description.isEmpty() 
				|| this.title.isEmpty()
				||this.price<0 
				|| this.path.isEmpty())
			retval=false;
		return retval;
	}
	
}
