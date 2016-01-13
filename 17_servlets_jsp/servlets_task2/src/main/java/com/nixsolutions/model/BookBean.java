package com.nixsolutions.model;

import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.AuthorBook;
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Category;
import com.nixsolutions.entity.Cell;

public class BookBean {

	private Book book;
	private List<Author> authors;
	private Category category;
	private Cell cell;

	/*
	 * public static BookBean getBookBean(int id){ H2DaoFactory factory =
	 * DaoFactory.getDAOFactory(DaoFactory.H2); BookBean bookBean = new
	 * BookBean(); List<AuthorBook> authBook;
	 * bookBean.setBook(factory.getBookDao().getBookById(id)); cell =
	 * factory.getCellDao().getCellById(book.getCellId()); category =
	 * factory.getCategoryDao().getCategoryById(book.getCategoryId()); authBook
	 * = factory.getAuthorBookDao().getAuthorIdByBookId(book.getBookId()); for
	 * (AuthorBook authorBook : authBook) {
	 * authors.add(factory.getAuthorDao().getAuthorById(authorBook.getAuthorId()
	 * )); } return bookBean; }
	 */
	/*
	 * public BookBean(int id){ H2DaoFactory factory =
	 * DaoFactory.getDAOFactory(DaoFactory.H2); List<AuthorBook> authBook; book
	 * = factory.getBookDao().getBookById(id); cell =
	 * factory.getCellDao().getCellById(book.getCellId()); category =
	 * factory.getCategoryDao().getCategoryById(book.getCategoryId()); authBook
	 * = factory.getAuthorBookDao().getAuthorIdByBookId(book.getBookId()); for
	 * (AuthorBook authorBook : authBook) {
	 * authors.add(factory.getAuthorDao().getAuthorById(authorBook.getAuthorId()
	 * )); } };
	 */

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Author> getAuthor() {
		return authors;
	}

	public void setAuthor(List<Author> authors) {
		this.authors = authors;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public BookBean(Book book, List<Author> authors, Category category, Cell cell) {
		super();
		this.book = book;
		this.authors = authors;
		this.category = category;
		this.cell = cell;
	}

	public BookBean(){};
	
	public static List<BookBean> getAllBookBeans() {

		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getAllBooks();
		List<AuthorBook> authBook;
		List<Author> authors = new ArrayList<Author>();
		
		for (Book book : books) {
			BookBean bookBean = new BookBean();
			bookBean.setBook(book);
			bookBean.setCell(factory.getCellDao().getCellById(book.getCellId()));
			bookBean.setCategory(factory.getCategoryDao().getCategoryById(book.getCategoryId()));
			
			authBook = factory.getAuthorBookDao().getAuthorIdByBookId(book.getBookId());
			for (AuthorBook authorBook : authBook) {
				authors.add(factory.getAuthorDao().getAuthorById(authorBook.getAuthorId()));
			}
			
			bookBean.setAuthor(authors);
			
			allBookBeans.add(bookBean);
		}
			
		return allBookBeans;

	}

	public static void getBookBeansByName() {

	}

	public static void getBookBeansByAuthor() {

	}

	public static void getBookBeansByCategory() {

	}

}
