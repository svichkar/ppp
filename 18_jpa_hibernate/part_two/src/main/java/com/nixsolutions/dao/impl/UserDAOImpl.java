package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.entities.User;
import com.nixsolutions.error.CustomDataException;

public class UserDAOImpl implements UserDAO<User> {

	private final static Logger LOG = LogManager.getLogger(UserDAOImpl.class);
	public static SessionFactory sessionFactory;

	public UserDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(User t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void update(User t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void delete(User t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public User findByPK(long id) {
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			user = (User) session.get(User.class, id);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return user;
	}

	public User findByName(String username) {
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return user;
	}

	public User findByNameAndPassword(String username, String password) {
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", username))
					.add(Restrictions.eq("password", password)).uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			users.addAll(session.createCriteria(User.class).list());
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return users;
	}

}
