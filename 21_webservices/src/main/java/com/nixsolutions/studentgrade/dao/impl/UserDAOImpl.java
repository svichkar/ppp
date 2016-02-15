package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.studentgrade.dao.UserDAO;
import com.nixsolutions.studentgrade.entity.User;

@Repository("userDao")
public class UserDAOImpl implements UserDAO {

	@Autowired
    SessionFactory sessionFactory;

	@Override
	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserById(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = null;
		try {
			user = (User) session.get(User.class, userId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	@Override
	public User findUserByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		User user = null;
		try {
			user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = new ArrayList<User>();
		try {
			users = session.createCriteria(User.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return users;
	}

}
