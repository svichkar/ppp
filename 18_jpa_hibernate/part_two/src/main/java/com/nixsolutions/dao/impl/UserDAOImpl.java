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

public class UserDAOImpl implements UserDAO<User> {

	private final static Logger LOG = LogManager.getLogger(UserDAOImpl.class);
	public static SessionFactory sessionFactory;

	public UserDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(User t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(User t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(User t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public User findByPK(long id) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		user = (User) session.get(User.class, id);
		tx.commit();
		return user;
	}

	public User findByName(String username) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
		tx.commit();
		return user;
	}

	public User findByNameAndPassword(String username, String password) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password)).uniqueResult();
		tx.commit();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		List<User> lUsers = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lUsers.addAll(session.createCriteria(User.class).list());
		tx.commit();
		return lUsers;
	}

}
