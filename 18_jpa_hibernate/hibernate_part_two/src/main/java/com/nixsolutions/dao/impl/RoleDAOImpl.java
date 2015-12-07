package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class RoleDAOImpl implements RoleDAO {

	public static Logger LOG = LogManager.getLogger(RoleDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Role entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("role", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void update(Role entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("role", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Role entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("role", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public Role getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = null;
		Transaction tx = session.beginTransaction();
		try {
			role = (Role) session.byId(Role.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Role> roleList = null;
		Transaction tx = session.beginTransaction();
		try {
			roleList = session.createCriteria(Role.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return roleList;
	}
}
