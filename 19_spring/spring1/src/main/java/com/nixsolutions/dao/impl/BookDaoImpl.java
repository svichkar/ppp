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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.BookDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Book;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	public static final Logger LOG = LogManager.getLogger();

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Book> getAllBooks() {
		LOG.entry();
		List<Book> books = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		books = criteria.list();
		return LOG.exit(books);
	}

	@Override
	public Book getBookById(Long bookId) {
		LOG.entry(bookId);
		Book book = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class)
				.add(Restrictions.eq("bookId", bookId))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		book = (Book) criteria.uniqueResult();
		return LOG.exit(book);
	}

	@Override
	public void createBook(Book book) {
		LOG.entry(book);
		Session session = sessionFactory.getCurrentSession();
		session.save(book);
	}

	@Override
	public void updateBook(Book book) {
		LOG.entry(book);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(book);
	}

	@Override
	public void deleteBook(Book book) {
		LOG.entry(book);
		Session session = sessionFactory.getCurrentSession();
		session.delete(book);
	}

	@Override
	public List<Book> getBooksByAuthor(String name) {
		LOG.entry(name);
		List<Book> books = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class, "book")
				.createAlias("book.authors", "authors")
				.add(Restrictions.eq("authors.secondName", name))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		books = criteria.list();
		return LOG.exit(books);
	}

	@Override
	public List<Book> getBooksByCategory(String name) {
		LOG.entry(name);
		List<Book> books = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class, "book")
				.createAlias("book.category", "category")
				.add(Restrictions.eq("category.name", name))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		books = criteria.list();
		return LOG.exit(books);
	}

	@Override
	public List<Book> getBooksByName(String name) {
		LOG.entry(name);
		List<Book> books = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class, "book")
				.add(Restrictions.eq("book.name", name))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		books = criteria.list();
		return LOG.exit(books);
	}
}
