package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.H2ConnManager;
import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.Book;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class AuthorDaoImpl implements AuthorDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Author> getAllAuthors() {
		LOG.entry();
		List<Author> authors = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Author.class);
		authors = criteria.list();
		transaction.commit();
		return LOG.exit(authors);
	}
	
	@Override
	public Author getAuthorByName(String name) {
		LOG.entry(name);
		Author author = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Author.class, "author")
				.add(Restrictions.eq("author.secondName", name));
		author = (Author) criteria.uniqueResult();
		transaction.commit();
		return LOG.exit(author);
	}

	@Override
	public Author getAuthorById(Long authorId) {
		LOG.entry(authorId);
		Author author = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Author.class, "author")
				.add(Restrictions.eq("author.authorId", authorId));
		author = (Author)criteria.uniqueResult();
		transaction.commit();
		return LOG.exit(author);
	}

	@Override
	public Author createAuthor(Author author) {
		LOG.entry(author);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(author);
		transaction.commit();
		return author;
	}

	@Override
	public void updateAuthor(Author author) {
		LOG.entry(author);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.saveOrUpdate(author);
		transaction.commit();
	}

	@Override
	public void deleteAuthor(Author author) {
		LOG.entry(author);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(author);
		transaction.commit();
	}
}
