package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	public static Logger LOG = LogManager.getLogger(UserDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public User createFrom(User entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		User user = null;
		try {
			session.saveOrUpdate("user", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		try {
			user = (User) session.get(User.class, entity.getUserId());
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return user;
	}

	@Override
	public void update(User entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("user", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(User entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("user", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public User getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = null;
		Transaction tx = session.beginTransaction();
		try {
			user = (User) session.byId(User.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = null;
		Transaction tx = session.beginTransaction();
		try {
			userList = session.createCriteria(User.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return userList;
	}

	@Override
	public User getUserByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		User user = null;
		Transaction tx = session.beginTransaction();
		try {
			user = (User) session.createCriteria(User.class).add(Restrictions.eq("userLogin", login)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = null;
		Transaction tx = session.beginTransaction();
		try {
			userList = session.createCriteria(User.class).add(Restrictions.eq("role.roleId", 2)).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getWorkers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = null;
		Transaction tx = session.beginTransaction();
		try {
			userList = session.createCriteria(User.class).add(Restrictions.eq("role.roleId", 3)).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return userList;
	}
}
