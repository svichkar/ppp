package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	protected SessionFactory sessionFactory;

	public void create(User t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void update(User t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void delete(User t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	public User findByPK(long id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	public User findByName(String username) {
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username)).uniqueResult();
	}

	public User findByNameAndPassword(String username, String password) {
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		users.addAll(sessionFactory.getCurrentSession().createCriteria(User.class).list());
		return users;
	}

}
