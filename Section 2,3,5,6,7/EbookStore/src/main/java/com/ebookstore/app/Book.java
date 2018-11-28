package com.ebookstore.app;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "book")
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3638672164426743937L;

	@Id
	@GeneratedValue
	@Column(name = "id", length = 50, nullable = false)
	private long id;

	@NotNull
	@Size(min = 1, message = "Title should have at least 1 character")
	@Column(name = "title", length = 50, nullable = false)
	private String title;
	@NotNull
	@Size(min = 1, message = "Description should have at least 1 character")
	@Column(name = "description", length = 200, nullable = false)
	private String description;
	@NotNull
	@PositiveOrZero(message = "Price should be greater than 0")
	@Column(name = "price", nullable = false)
	private double price;

	@NotNull(message = "publishDate must be set")
	@Column(name = "publishdate", nullable = false)
	private Date publishDate;
	@NotNull
	@Size(min = 1, message = "path should have at least 1 character")
	@Column(name = "path", length = 20, nullable = false)
	private String path;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "p_id", nullable = true)
	@JsonIgnore
	private Person author;

	@Column(name = "bfile", nullable = false)
	@Lob
	private byte[] bfile;

	public byte[] getBfile() {
		return bfile;
	}

	public void setBfile(byte[] bfile) {
		this.bfile = bfile;
	}

	public Book() {
		this.title = "";
		this.description = "";
		this.price = 0;
		this.publishDate = new Date(0);
		this.path = "";

	}

	public Book(String title, String description, double price, Date date, String path, byte[] bfile) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.publishDate = date;
		this.path = path;
		this.bfile = bfile;
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
		if (this.description.isEmpty() || this.title.isEmpty() || this.price < 0 || this.path.isEmpty())
			retval = false;
		return retval;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public Person getAuthor() {
		return author;
	}
}
