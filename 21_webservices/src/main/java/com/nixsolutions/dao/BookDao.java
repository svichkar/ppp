package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Book;

public interface BookDao {
	 List<Book> getAllBooks();
	 List<Book> getBooksByAuthor(String name);
	 List<Book> getBooksByCategory(String name);
	 List<Book> getBooksByName(String name);
	 Book getBookById(Long bookId);
	 void createBook(Book book);
	 void updateBook(Book book);
	 void deleteBook(Book book);
}

