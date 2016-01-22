package com.nixsolutions.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Client;
import com.nixsolutions.entity.RentJournal;

public class LoanBean {
	public static final Logger LOG = LogManager.getLogger();
	private static final H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	private RentJournal loan;
	private Book book;
	private Client reader;
	private String expirationState; // null if not expired or count of overdue
									// days

	public LoanBean(RentJournal loan) {
		setLoan(loan);
		setBook(loan.getBookId());
		setReader(loan.getClientId());
		// here we will calculate expiration state
		checkForExpiration();
	}

	private void checkForExpiration() {

		if (loan.getReturnDate() == null) {

			DateTime dt1 = new DateTime(loan.getRentDate());
			DateTime dt2 = new DateTime();
			String expiration = Days.daysBetween(dt1, dt2).getDays() + " days "
					+ Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours "
					+ Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes";
			setExpirationState(expiration);
			LOG.debug(expiration);
		}

	}

	public RentJournal getLoan() {
		return loan;
	}

	public void setLoan(RentJournal loan) {
		this.loan = loan;
	}

	public Book getBook() {
		return book;
	}

	private void setBook(Long id) {
		this.book = factory.getBookDao().getBookById(id);
	}

	public Client getReader() {
		return reader;
	}

	private void setReader(Long id) {
		this.reader = factory.getClientDao().getClientById(id);
	}

	public String getExpirationState() {
		return expirationState;
	}

	public void setExpirationState(String expirationState) {
		this.expirationState = expirationState;
	}

	public static List<LoanBean> getLoanBeansByClientId(Long id) {
		List<LoanBean> LoanBeans = new ArrayList<>();
		List<RentJournal> loans = factory.getRentJournalDao().getRentsByClientId(id);

		for (RentJournal loan : loans) {
			LoanBeans.add(new LoanBean(loan));
		}

		return LOG.exit(LoanBeans);
	}

	public static List<LoanBean> getActiveLoanBeansByClientId(Long id) {
		List<LoanBean> LoanBeans = new ArrayList<>();
		List<RentJournal> loans = factory.getRentJournalDao().getRentsByClientId(id);

		for (RentJournal loan : loans) {
			if (loan.getReturnDate() == null) {
				LoanBeans.add(new LoanBean(loan));
			}
		}

		return LOG.exit(LoanBeans);
	}

	/*
	 * public static List<LoanBean> getAllBookBeans() {
	 * 
	 * H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	 * List<LoanBean> allBookBeans = new ArrayList<>(); List<Book> books =
	 * factory.getBookDao().getAllBooks();
	 * 
	 * for (Book book : books) { allBookBeans.add(getBookBean(book)); }
	 * 
	 * return LOG.exit(allBookBeans); }
	 * 
	 * public static List<LoanBean> getBookBeansByName(String name) {
	 * H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	 * List<LoanBean> allBookBeans = new ArrayList<>(); List<Book> books =
	 * factory.getBookDao().getBooksByName(name);
	 * 
	 * for (Book book : books) { allBookBeans.add(getBookBean(book)); }
	 * 
	 * return LOG.exit(allBookBeans); }
	 * 
	 * public static List<LoanBean> getBookBeansByCategory(String name) {
	 * H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	 * List<LoanBean> allBookBeans = new ArrayList<>(); List<Book> books =
	 * factory.getBookDao().getBooksByCategory(name);
	 * 
	 * for (Book book : books) { allBookBeans.add(getBookBean(book)); }
	 * 
	 * return LOG.exit(allBookBeans); }
	 * 
	 * public static LoanBean getBookBean(Book book){ H2DaoFactory factory =
	 * DaoFactory.getDAOFactory(DaoFactory.H2); List<Author> authors = new
	 * ArrayList<Author>(); List<AuthorBook> authBook; LoanBean bookBean = new
	 * LoanBean(); bookBean.setBook(book);
	 * bookBean.setCell(factory.getCellDao().getCellById(book.getCellId()));
	 * bookBean.setCategory(factory.getCategoryDao().getCategoryById(book.
	 * getCategoryId()));
	 * 
	 * authBook =
	 * factory.getAuthorBookDao().getAuthorIdByBookId(book.getBookId()); for
	 * (AuthorBook authorBook : authBook) {
	 * authors.add(factory.getAuthorDao().getAuthorById(authorBook.getAuthorId()
	 * )); }
	 * 
	 * bookBean.setAuthors(authors); return bookBean; }
	 */
}
