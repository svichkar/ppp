package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.BookDao;
import com.nixsolutions.entity.Book;
import com.nixsolutions.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Override
	public List<Book> getBooksByAuthor(String name) {
		return bookDao.getBooksByAuthor(name);
	}

	@Override
	public List<Book> getBooksByCategory(String name) {
		return bookDao.getBooksByCategory(name);
	}

	@Override
	public List<Book> getBooksByName(String name) {
		return bookDao.getBooksByName(name);
	}

	@Override
	public Book getBookById(Long bookId) {
		return bookDao.getBookById(bookId);
	}

	@Override
	public void createBook(Book book) {
		bookDao.createBook(book);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	@Override
	public void deleteBook(Book book) {
		bookDao.deleteBook(book);
	}

}
