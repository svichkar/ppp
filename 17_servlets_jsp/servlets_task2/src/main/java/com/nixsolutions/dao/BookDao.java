package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Book;

public interface BookDao {
	public List<Book> getAllBooks();
	public List<Book> getBooksByAuthor(String name);
	public List<Book> getBooksByCategory(String name);
	public List<Book> getBooksByName(String name);
	public Book getBookById(int bookId);
	public Book createBook(Book book);
	public void updateBook(Book book);
	public void deleteBook(Book book);
}

