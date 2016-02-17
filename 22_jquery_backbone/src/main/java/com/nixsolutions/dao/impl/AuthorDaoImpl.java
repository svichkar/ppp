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
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Author;

@Repository("authorDao")
public class AuthorDaoImpl implements AuthorDao {
	public static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Author> getAllAuthors() {
		LOG.entry();
		List<Author> authors = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Author.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			authors = criteria.list();
		return LOG.exit(authors);
	}

	@Override
	public Author getAuthorByName(String name) {
		LOG.entry(name);
		Author author = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Author.class, "author")
					.add(Restrictions.eq("author.secondName", name))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			author = (Author) criteria.uniqueResult();
		return LOG.exit(author);
	}

	@Override
	public Author getAuthorById(Long authorId) {
		LOG.entry(authorId);
		Author author = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Author.class, "author")
					.add(Restrictions.eq("author.authorId", authorId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			author = (Author) criteria.uniqueResult();
		return LOG.exit(author);
	}

	@Override
	public Author createAuthor(Author author) {
		LOG.entry(author);
		Session session = sessionFactory.getCurrentSession();
			session.save(author);
		return author;
	}

	@Override
	public void updateAuthor(Author author) {
		LOG.entry(author);
		Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(author);
	}

	@Override
	public void deleteAuthor(Author author) {
		LOG.entry(author);
		Session session = sessionFactory.getCurrentSession();
			session.delete(author);
	}
}
