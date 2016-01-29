package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Author;

@Repository("AuthorDao")
public class AuthorDaoImpl implements AuthorDao {
	public static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Author> getAllAuthors() {
		LOG.entry();
		List<Author> authors = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(Author.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			authors = criteria.list();
		} catch (Exception e) {
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(authors);
	}

	@Override
	public Author getAuthorByName(String name) {
		LOG.entry(name);
		Author author = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(Author.class, "author")
					.add(Restrictions.eq("author.secondName", name))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			author = (Author) criteria.uniqueResult();
		} catch (Exception e) {
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(author);
	}

	@Override
	public Author getAuthorById(Long authorId) {
		LOG.entry(authorId);
		Author author = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(Author.class, "author")
					.add(Restrictions.eq("author.authorId", authorId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			author = (Author) criteria.uniqueResult();
		} catch (Exception e) {
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(author);
	}

	@Override
	public Author createAuthor(Author author) {
		LOG.entry(author);
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(author);
		} catch (Exception e) {
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return author;
	}

	@Override
	public void updateAuthor(Author author) {
		LOG.entry(author);
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(author);
		} catch (Exception e) {
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

	}

	@Override
	public void deleteAuthor(Author author) {
		LOG.entry(author);
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(author);
		} catch (Exception e) {
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}
}
