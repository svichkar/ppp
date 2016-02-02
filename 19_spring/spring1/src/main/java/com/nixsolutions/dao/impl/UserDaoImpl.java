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
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getAllUsers() {
		LOG.entry();
		List<User> users = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(User.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			users = criteria.list();
		return LOG.exit(users);
	}

	@Override
	public User getUserByNameAndPswd(String name, String pswd) {
		LOG.entry(name, pswd);
		User user = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(User.class)
					.add(Restrictions.eq("userName", name)).add(Restrictions.eq("userPassword", pswd))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			user = (User) criteria.uniqueResult();
		return LOG.exit(user);
	}

	@Override
	public User getUserById(Long userId) {
		LOG.entry(userId);
		User user = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(User.class)
					.add(Restrictions.eq("userId", userId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			user = (User) criteria.uniqueResult();
		return LOG.exit(user);
	}

	@Override
	public void createUser(User user) {
		LOG.entry(user);
		Session session = sessionFactory.getCurrentSession();
			session.save(user);
	}

	@Override
	public void updateUser(User user) {
		LOG.entry(user);
		Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(user);
	}

	@Override
	public void deleteUser(User user) {
		LOG.entry(user);
		Session session = sessionFactory.getCurrentSession();
			session.delete(user);
	}
}
