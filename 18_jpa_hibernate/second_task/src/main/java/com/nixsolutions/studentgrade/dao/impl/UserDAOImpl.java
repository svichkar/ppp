package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.studentgrade.dao.UserDAO;
import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
		} 
	}

	@Override
	public User findUserById(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = null;
		Transaction transaction = session.beginTransaction();
		user = (User) session.get(User.class, userId);
		transaction.commit();
		return user;
	}

	@Override
	public User findUserByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		User user = null;
		Transaction transaction = session.beginTransaction();
		user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
		transaction.commit();
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = new ArrayList<User>();
		Transaction transaction = session.beginTransaction();
		users = session.createCriteria(User.class).list();
		transaction.commit();
		return users;
	}

}
