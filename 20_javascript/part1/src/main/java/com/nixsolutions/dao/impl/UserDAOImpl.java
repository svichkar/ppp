package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.entities.User;

@Repository("userDao")
@Transactional
public class UserDAOImpl implements UserDAO {

	private final static Logger LOG = LogManager.getLogger(UserDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public void create(User t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(User t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(User t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public User findByPK(long id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public User findByName(String username) {
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username)).uniqueResult();
	}

	@Override
	public User findByNameAndPassword(String username, String password) {
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		users.addAll(sessionFactory.getCurrentSession().createCriteria(User.class).list());
		return users;
	}

}
