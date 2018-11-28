package com.ebookstore.app;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Service;

@Service
public class EBookStoreService {

	private Map<Long, Book> booklist = new HashMap<Long, Book>();

	private Map<Long, Person> personlist = new HashMap<Long, Person>();

	private void initBooklist() {
		booklist.put(1L, new Book());
		booklist.put(2L, new Book());
	}

	private void initPersonlist() {
		personlist.put(1L, new Person("user1", "pw1", "Joanne", "Rowling", "AUTHOR"));
		personlist.put(2L, new Person("user2", "pw2", "Petra", "Simonis", "AUTHOR"));
	}

	/*
	 * private final List<Book> booklist = Stream.of( new
	 * Book("Harry Potter and the philosopher's stone",
	 * "young orphan finds out he's a wizard", 0.0, Date.valueOf("2001-12-12"), ""),
	 * new Book("Greg's diary", "young boy describes his life in a comic", 0.0,
	 * Date.valueOf("2001-12-12"),"") ).collect(toList());
	 */

	/*
	 * private final List<Person> personlist = Stream.of( new
	 * Person("user1","pw1","Joanne", "Rowling", "AUTHOR"), new
	 * Person("user2","pw2","Petra", "Simonis", "AUTHOR")
	 * 
	 * ).collect(toList());
	 */

	public EBookStoreService() {
		initBooklist();
		initPersonlist();
	}

	public List<Book> getBooks() {
		return new ArrayList<Book>(booklist.values());
	}

	public List<Person> getAuthors() {
		return new ArrayList<Person>(this.personlist.values());
	}

	public Book newBook(Book newbook) {
		// TODO Auto-generated method stub

		return booklist.put(newbook.getId(), newbook);
	}

	public Book updateBook(Book book) {
		// TODO Auto-generated method stub
		return booklist.put(book.getId(), book);
	}

	public Book findBook(Book book) {
		// TODO Auto-generated method stub
		return booklist.get(book.getId());
	}

	public Book findBookById(Long id) {
		// TODO Auto-generated method stub
		return booklist.get(id);
	}

	public void deleteBook(Long id) {
		// TODO Auto-generated method stub
		booklist.remove(id);
	}

	public Person newPerson(Person newperson) {
		// TODO Auto-generated method stub
		return personlist.put(newperson.getId(), newperson);
	}

	public Person findPersonById(Long id) {
		// TODO Auto-generated method stub
		return personlist.get(id);
	}
}
