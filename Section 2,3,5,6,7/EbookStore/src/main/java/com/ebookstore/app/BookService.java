package com.ebookstore.app;

public interface BookService {
	void deleteBook(Long id);

	void addBookToAuthor(Book b, Person p);

	void updateBook(Book b);
}
