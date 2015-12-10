package com.nixsolutions.dao.hibernate;

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

public class UserDaoHibernate implements UserDAO {

	public static Logger LOG = LogManager.getLogger(UserDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(User entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(User entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(User entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public User getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = null;
		Transaction tx = session.beginTransaction();
		user = (User) session.get(User.class, id);
		tx.commit();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = null;
		Transaction tx = session.beginTransaction();
		userList = session.createCriteria(User.class).list();
		tx.commit();
		return userList;
	}

	@Override
	public User getUserByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		User user = null;
		Transaction tx = session.beginTransaction();
		user = (User) session.createCriteria(User.class).add(Restrictions.eq("userLogin", login)).uniqueResult();
		tx.commit();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = null;
		Transaction tx = session.beginTransaction();
		userList = session.createCriteria(User.class).add(Restrictions.eq("role.roleId", 2)).list();
		tx.commit();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getWorkers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = null;
		Transaction tx = session.beginTransaction();
		userList = session.createCriteria(User.class).add(Restrictions.eq("role.roleId", 3)).list();
		tx.commit();
		return userList;
	}
}
