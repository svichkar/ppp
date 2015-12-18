package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

	public RoleDaoImpl() {
	}

	public void create(Role role) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(role);
		transaction.commit();
	}

	public void update(Role role) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(role);
		transaction.commit();
	}

	public void delete(Role role) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(role);
		transaction.commit();
	}

	public Role getByRoleId(int roleId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Role role = (Role) session.get(Role.class, roleId);
		transaction.commit();
		return role;
	}

	public Role getByRoleName(String roleName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Role.class).add(Restrictions.eq("roleName", roleName));
		Role role = (Role) c.uniqueResult();
		transaction.commit();
		return role;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Role> toReturn = session.createCriteria(Role.class).list();
		transaction.commit();
		return toReturn;
	}
}