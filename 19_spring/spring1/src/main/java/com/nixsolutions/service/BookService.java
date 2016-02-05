package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Book;

public interface BookService {
	public List<Book> getAllBooks();

	public List<Book> getBooksByAuthor(String name);

	public List<Book> getBooksByCategory(String name);

	public List<Book> getBooksByName(String name);

	public Book getBookById(Long bookId);

	public void createBook(String bookName, String authorFirstName, String authorLastName,
			String cell, String category, String count);

	public void updateBook(Book book);

	public void deleteBook(Book book);
}
