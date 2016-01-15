package com.nixsolutions.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.AuthorBook;
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Category;
import com.nixsolutions.entity.Cell;

public class BookBean {
	public static final Logger LOG = LogManager.getLogger();
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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
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
		
		for (Book book : books) {
			List<Author> authors = new ArrayList<Author>();
			List<AuthorBook> authBook;
			BookBean bookBean = new BookBean();
			bookBean.setBook(book);
			bookBean.setCell(factory.getCellDao().getCellById(book.getCellId()));
			bookBean.setCategory(factory.getCategoryDao().getCategoryById(book.getCategoryId()));
			
			authBook = factory.getAuthorBookDao().getAuthorIdByBookId(book.getBookId());
			for (AuthorBook authorBook : authBook) {
				authors.add(factory.getAuthorDao().getAuthorById(authorBook.getAuthorId()));
			}
			
			bookBean.setAuthors(authors);
			
			allBookBeans.add(bookBean);
		}
			
		return LOG.exit(allBookBeans);

	}

	public static List<BookBean> getBookBeansByName(String name) {
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getBooksByName(name);
		
		for (Book book : books) {
			List<Author> authors = new ArrayList<Author>();
			List<AuthorBook> authBook;
			BookBean bookBean = new BookBean();
			bookBean.setBook(book);
			bookBean.setCell(factory.getCellDao().getCellById(book.getCellId()));
			bookBean.setCategory(factory.getCategoryDao().getCategoryById(book.getCategoryId()));
			
			authBook = factory.getAuthorBookDao().getAuthorIdByBookId(book.getBookId());
			for (AuthorBook authorBook : authBook) {
				authors.add(factory.getAuthorDao().getAuthorById(authorBook.getAuthorId()));
			}
			
			bookBean.setAuthors(authors);
			
			allBookBeans.add(bookBean);
		}
			
		return LOG.exit(allBookBeans);
	}

	public static List<BookBean> getBookBeansByAuthor(String name) {
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		//1 check if author exists in author table (get its id) - what will happen if we have two authors?
		//2 in author_book get all book id's related to author
		//3 create list of author by set of id's
		
		
		
		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getBooksByAuthor(name);
		
		for (Book book : books) {
			List<Author> authors = new ArrayList<Author>();
			List<AuthorBook> authBook;
			BookBean bookBean = new BookBean();
			bookBean.setBook(book);
			bookBean.setCell(factory.getCellDao().getCellById(book.getCellId()));
			bookBean.setCategory(factory.getCategoryDao().getCategoryById(book.getCategoryId()));
			
			authBook = factory.getAuthorBookDao().getAuthorIdByBookId(book.getBookId());
			for (AuthorBook authorBook : authBook) {
				authors.add(factory.getAuthorDao().getAuthorById(authorBook.getAuthorId()));
			}
			
			bookBean.setAuthors(authors);
			
			allBookBeans.add(bookBean);
		}
			
		return LOG.exit(allBookBeans);
	}

	public static List<BookBean> getBookBeansByCategory(String name) {
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getBooksByCategory(name);
		
		for (Book book : books) {
			List<Author> authors = new ArrayList<Author>();
			List<AuthorBook> authBook;
			BookBean bookBean = new BookBean();
			bookBean.setBook(book);
			bookBean.setCell(factory.getCellDao().getCellById(book.getCellId()));
			bookBean.setCategory(factory.getCategoryDao().getCategoryById(book.getCategoryId()));
			
			authBook = factory.getAuthorBookDao().getAuthorIdByBookId(book.getBookId());
			for (AuthorBook authorBook : authBook) {
				authors.add(factory.getAuthorDao().getAuthorById(authorBook.getAuthorId()));
			}
			
			bookBean.setAuthors(authors);
			
			allBookBeans.add(bookBean);
		}
			
		return LOG.exit(allBookBeans);
	}

}
