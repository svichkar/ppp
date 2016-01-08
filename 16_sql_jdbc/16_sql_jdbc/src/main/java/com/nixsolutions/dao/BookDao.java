package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Book;

public interface BookDao {
	public List<Book> getAllBooks();
	public Book getBookById(int bookId);
	public void createBook(Book book);
	public void updateBook(Book book);
	public void deleteBook(Book book);
}

