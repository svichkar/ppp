package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.BookDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Book;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class BookDaoImpl implements BookDao {
	public static final Logger LOG = LogManager.getLogger();
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public List<Book> getAllBooks() {
		LOG.entry();
		List<Book> books = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Book.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			books = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

		return LOG.exit(books);
	}

	@Override
	public Book getBookById(Long bookId) {
		LOG.entry(bookId);
		Book book = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {			
			Criteria criteria = session.createCriteria(Book.class)
					.add(Restrictions.eq("bookId", bookId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			book = (Book) criteria.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(book);
	}

	@Override
	public void createBook(Book book) {
		LOG.entry(book);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {			
			session.save(book);
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		transaction.commit();
	}

	@Override
	public void updateBook(Book book) {
		LOG.entry(book);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {		
			session.saveOrUpdate(book);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

	}

	@Override
	public void deleteBook(Book book) {
		LOG.entry(book);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {		
			session.delete(book);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

	}

	@Override
	public List<Book> getBooksByAuthor(String name) {
		LOG.entry(name);
		List<Book> books = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {			
			Criteria criteria = session.createCriteria(Book.class, "book")
					.createAlias("book.authors", "authors")
					.add(Restrictions.eq("authors.secondName", name))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			books = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

		return LOG.exit(books);
	}

	@Override
	public List<Book> getBooksByCategory(String name) {
		LOG.entry(name);
		List<Book> books = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Book.class, "book")
					.createAlias("book.category", "category")
					.add(Restrictions.eq("category.name", name))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			books = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

		return LOG.exit(books);
	}

	@Override
	public List<Book> getBooksByName(String name) {
		LOG.entry(name);
		List<Book> books = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {			
			Criteria criteria = session.createCriteria(Book.class, "book")
					.add(Restrictions.eq("book.name", name))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			books = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

		return LOG.exit(books);
	}
}
