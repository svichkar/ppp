package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Role;

public class RoleDAOImpl implements RoleDAO<Role> {

	private final static Logger LOG = LogManager.getLogger(RoleDAOImpl.class);
	public static SessionFactory sessionFactory;

	public RoleDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Role t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(Role t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(Role t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public Role findByPK(long id) {
		Role role = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		role = (Role) session.get(Role.class, id);
		tx.commit();
		return role;
	}

	public Role findRoleByName(String rolename) {
		Role role = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("rolename", rolename)).uniqueResult();
		tx.commit();
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		List<Role> lRoles = new ArrayList<Role>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lRoles.addAll(session.createCriteria(OrderInWork.class).list());
		tx.commit();
		return lRoles;
	}

}
