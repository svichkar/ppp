package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class RoleDaoHibernate implements RoleDAO {

	public static Logger LOG = LogManager.getLogger(RoleDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Role entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(Role entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(Role entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public Role getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = null;
		Transaction tx = session.beginTransaction();
		role = (Role) session.get(Role.class, id);
		tx.commit();
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Role> roleList = null;
		Transaction tx = session.beginTransaction();
		roleList = session.createCriteria(Role.class).list();
		tx.commit();
		return roleList;
	}
}
