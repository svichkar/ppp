package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoImpl implements UserDao {

	public UserDaoImpl() {
	}

	public void create(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}

	public void update(User user) {
		Session session = null;
		Transaction transaction = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		transaction.commit();
	}

	public void delete(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
	}

	public User getByUserId(int userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		User user = (User) session.get(User.class, userId);
		transaction.commit();
		return user;
	}

	public User getByUserName(String userName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("userName", userName));
		User user = (User) c.uniqueResult();
		transaction.commit();
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<User> toReturn = session.createCriteria(User.class).list();
		transaction.commit();
		return toReturn;
	}

	public boolean checkUser(String userName, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password));
		User user = (User) c.uniqueResult();
		transaction.commit();
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}
}