package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class UserDaoImpl implements UserDao{
	public static final Logger LOG = LogManager.getLogger();
	
	@Override
	public List<User> getAllUsers() {
		LOG.entry();
		List<User> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(User.class);
		users = criteria.list();
		transaction.commit();
		return LOG.exit(users);
	}
	
	@Override
	public User getUserByNameAndPswd(String name, String pswd) {
		LOG.entry(name, pswd);
		User user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(User.class)
				.add(Restrictions.eq("userName", name))
				.add(Restrictions.eq("userPassword", pswd));
		user = (User) criteria.uniqueResult();
		transaction.commit();
		return LOG.exit(user);
	}
	
	@Override
	public User getUserById(Long userId) {
		LOG.entry(userId);
		User user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		transaction.begin();
		Criteria criteria = session.createCriteria(User.class)
				.add(Restrictions.eq("userId", userId));
		user = (User) criteria.uniqueResult();
		transaction.commit();
		return LOG.exit(user);
	}

	@Override
	public void createUser(User user) {
		LOG.entry(user);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(user);
		transaction.commit();
	}

	@Override
	public void updateUser(User user) {
		LOG.entry(user);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.saveOrUpdate(user);
		transaction.commit();
	}

	@Override
	public void deleteUser(User user) {
		LOG.entry(user);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(user);
		transaction.commit();
	}

}
