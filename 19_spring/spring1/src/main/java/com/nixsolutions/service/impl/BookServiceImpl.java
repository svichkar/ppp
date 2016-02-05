package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.BookDao;
import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.Book;
import com.nixsolutions.service.AuthorService;
import com.nixsolutions.service.BookService;
import com.nixsolutions.service.CategoryService;
import com.nixsolutions.service.CellService;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private CellService cellService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AuthorService authorService;
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
	public void createBook(String bookName, String authorFirstName, String authorLastName,
			String cell, String category, String count) {
		// new book
					Book book = new Book();
					book.setName(bookName);
					book.setCell(cellService.getCellByName(cell));
					book.setCategory(categoryService.getCategoryByName(category));
					book.setCount(Integer.valueOf(count));

					// new or existing author
					Author auth = authorService.getAuthorByName(authorLastName);
					List<Author> authors = new ArrayList<>();
					if (auth != null) {
						LOG.debug("we have such an author");
						authors.add(auth);
						book.setAuthors(authors);
					} else {
						auth = new Author();
						auth.setFirstName(authorFirstName);
						auth.setSecondName(authorLastName);
						authorService.createAuthor(auth);
						LOG.debug("during creation of new book the author was created: " + auth);
						authors.add(auth);
						book.setAuthors(authors);
					}
					
		
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
