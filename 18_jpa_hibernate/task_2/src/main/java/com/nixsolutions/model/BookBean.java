package com.nixsolutions.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Category;
import com.nixsolutions.entity.Cell;

public class BookBean {
	public static final Logger LOG = LogManager.getLogger();
	private static final H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	private Book book;
	private List<Author> authors;
	private Category category;
	private Cell cell;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors() {
		this.authors = book.getAuthors();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory() {
		this.category = book.getCategory();
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell() {
		this.cell = book.getCell();
	}

	public BookBean(Book book) {
		super();
		this.book = book;
		setAuthors();
		setCategory();
		setCell();
	}

	public BookBean() {
	};

	public static List<BookBean> getAllBookBeans() {

		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getAllBooks();

		for (Book book : books) {
			allBookBeans.add(new BookBean(book));
		}

		return LOG.exit(allBookBeans);
	}

	public static List<BookBean> getBookBeansByName(String name) {

		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getBooksByName(name);

		for (Book book : books) {
			allBookBeans.add(new BookBean(book));
		}

		return LOG.exit(allBookBeans);
	}

	public static List<BookBean> getBookBeansByAuthor(String name) {

		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getBooksByAuthor(name);

		for (Book book : books) {
			allBookBeans.add(new BookBean(book));
		}

		return LOG.exit(allBookBeans);
	}

	public static List<BookBean> getBookBeansByCategory(String name) {

		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getBooksByCategory(name);

		for (Book book : books) {
			allBookBeans.add(new BookBean(book));
		}

		return LOG.exit(allBookBeans);
	}

}
