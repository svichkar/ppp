package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.BookDao;
import com.nixsolutions.entity.Book;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class BookDaoImpl implements BookDao {
	public static final Logger LOG = LogManager.getLogger();

	
	@Override
	public List<Book> getAllBooks() {
		LOG.entry();
		List<Book> books = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Book.class);
		books = criteria.list();
		transaction.commit();
		return LOG.exit(books);
	}

	@Override
	public Book getBookById(Long bookId) {
		LOG.entry(bookId);
		Book book = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Book.class)
				.add(Restrictions.eq("bookId", bookId));
		book = (Book) criteria.uniqueResult();
		transaction.commit();
		return LOG.exit(book);
	}

	@Override
	public Book createBook(Book book) {
		LOG.entry(book);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(book);		
		transaction.commit();
		return book;
	}

	@Override
	public void updateBook(Book book) {
		LOG.entry(book);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.saveOrUpdate(book);	
	}

	@Override
	public void deleteBook(Book book) {
		LOG.entry(book);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(book);	
		transaction.commit();
	}

	@Override
	public List<Book> getBooksByAuthor(String name) {
		LOG.entry(name);
		List<Book> books = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Book.class, "book")
				.createAlias("book.authors", "author")
				.add(Restrictions.eq("authors.secondName", name));
		books = criteria.list();
		transaction.commit();
		return LOG.exit(books);
	}

	@Override
	public List<Book> getBooksByCategory(String name) {
		LOG.entry(name);
		List<Book> books = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Book.class, "book")
				.createAlias("book.category", "category")
				.add(Restrictions.eq("category.name", name));
		books = criteria.list();
		transaction.commit();
		return LOG.exit(books);
	}

	@Override
	public List<Book> getBooksByName(String name) {
		LOG.entry(name);
		List<Book> books = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Book.class, "book")
				.add(Restrictions.eq("book.name", name));
		books = criteria.list();
		transaction.commit();
		return LOG.exit(books);
	}
}
