package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.studentgrade.dao.RoleDAO;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.util.HibernateUtil;

public class RoleDAOImpl implements RoleDAO {
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(role);
		transaction.commit();
	}

	@Override
	public void updateRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(role);
		transaction.commit();
	}

	@Override
	public void deleteRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(role);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
		} 
	}

	@Override
	public Role findRoleById(Long roleId) {
		Session session = sessionFactory.getCurrentSession();
		Role role = null;
		Transaction transaction = session.beginTransaction();
		role = (Role) session.get(Role.class, roleId);
		transaction.commit();
		return role;
	}

	@Override
	public Role findRoleByName(String roleName) {
		Session session = sessionFactory.getCurrentSession();
		Role role = null;
		Transaction transaction = session.beginTransaction();
		role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("roleName", roleName)).uniqueResult();
		transaction.commit();
		return role;
	}

	@Override
	public List<Role> findAllRoles() {
		Session session = sessionFactory.getCurrentSession();
		List<Role> roles = new ArrayList<Role>();
		Transaction transaction = session.beginTransaction();
		roles = session.createCriteria(Role.class).list();
		transaction.commit();
		return roles;
	}

}
