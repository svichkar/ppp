package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.hibernate.entity.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	public static Logger LOG = LogManager.getLogger(UserDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(User entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("user", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(User entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("user", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(User entity) {
		try {
			sessionFactory.getCurrentSession().delete("user", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public User getByPK(int id) {
		try {
			return (User) sessionFactory.getCurrentSession().byId(User.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(User.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@Override
	public User getUserByLogin(String login) {
		try {
			return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("userLogin", login))
					.uniqueResult();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("role.roleId", 2))
					.list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getWorkers() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("role.roleId", 3))
					.list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}
}
