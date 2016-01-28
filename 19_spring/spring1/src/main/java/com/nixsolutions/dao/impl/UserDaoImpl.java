package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getAllUsers() {
		LOG.entry();
		List<User> users = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(User.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			users = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(users);
	}

	@Override
	public User getUserByNameAndPswd(String name, String pswd) {
		LOG.entry(name, pswd);
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(User.class)
					.add(Restrictions.eq("userName", name)).add(Restrictions.eq("userPassword", pswd))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			user = (User) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(user);
	}

	@Override
	public User getUserById(Long userId) {
		LOG.entry(userId);
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(User.class)
					.add(Restrictions.eq("userId", userId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			user = (User) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(user);
	}

	@Override
	public void createUser(User user) {
		LOG.entry(user);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}

	@Override
	public void updateUser(User user) {
		LOG.entry(user);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}

	@Override
	public void deleteUser(User user) {
		LOG.entry(user);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}
}
