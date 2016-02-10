package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Book;

public interface BookService {
	 List<Book> getAllBooks();
	 List<Book> getBooksByAuthor(String name);
	 List<Book> getBooksByCategory(String name);
	 List<Book> getBooksByName(String name);
	 Book getBookById(Long bookId);
	 void createBook(String bookName, String authorFirstName, String authorLastName,
			String cell, String category, String count);
	 void updateBook(Book book);
	 void deleteBook(Book book);
}
