package com.nixsolutions.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
			allBookBeans.add(getBookBean(book));
		}
				
		return LOG.exit(allBookBeans);
	}

	public static List<BookBean> getBookBeansByName(String name) {
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getBooksByName(name);

		for (Book book : books) {
			allBookBeans.add(getBookBean(book));
		}
			
		return LOG.exit(allBookBeans);
	}

	public static List<BookBean> getBookBeansByAuthor(String name) {
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getBooksByAuthor(name);
		
		for (Book book : books) {
			allBookBeans.add(getBookBean(book));
		}
			
		return LOG.exit(allBookBeans);
	}

	public static List<BookBean> getBookBeansByCategory(String name) {
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		List<BookBean> allBookBeans = new ArrayList<>();
		List<Book> books = factory.getBookDao().getBooksByCategory(name);
		
		for (Book book : books) {
			allBookBeans.add(getBookBean(book));
		}
			
		return LOG.exit(allBookBeans);
	}

	public static void saveBookBean(BookBean bookBean){
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		
		//1 if author new - save it
		
		//2 save book
		
		//3 save author_book info (auth id and book id)
		
	}
	
	public BookBean createBookBean(HttpServletRequest request) {
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		String bookName = request.getParameter("bookname");
		String authorFirstName = request.getParameter("authorfirstname");
		String authorLastName = request.getParameter("authorlastname");
		String cell = request.getParameter("selectcell");
		String category = request.getParameter("selectcategory");
		
		BookBean bookBean = new BookBean();
		
		//category from list
		bookBean.setCategory(factory.getCategoryDao().getCategoryByName(category));
		
		//cell from list
		bookBean.setCell(factory.getCellDao().getCellByName(cell));
		
		//new or existing author
		List<Author> listAuth = new ArrayList<>();
		Author auth = new Author();
		auth.setFirstName(authorFirstName);
		auth.setSecondName(authorLastName);
		listAuth.add(auth);
		bookBean.setAuthors(listAuth);
		
		/*List<Author> listAuth = factory.getAuthorDao().getAuthorsByName(authorLastName);

		if(listAuth != null){
			bookBean.setAuthors(listAuth);
		}else{
		Author auth = new Author();
		auth.setFirstName(authorFirstName);
		auth.setSecondName(authorLastName);
		factory.getAuthorDao().createAuthor(auth);
		listAuth = factory.getAuthorDao().getAuthorsByName(authorLastName);
		bookBean.setAuthors(listAuth);
		}*/
				
		//new book
		Book book = new Book();
		book.setName(bookName);
		book.setCellId(bookBean.getCell().getCellId());
		book.setCategoryId(bookBean.getCategory().getCategoryId());
		
		return bookBean;

	}
	
	public static BookBean getBookBean(Book book){
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
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
		return bookBean;
	}

}
