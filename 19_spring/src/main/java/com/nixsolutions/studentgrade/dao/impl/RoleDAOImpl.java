package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.studentgrade.dao.RoleDAO;
import com.nixsolutions.studentgrade.entity.Role;

@Repository("roleDao")
public class RoleDAOImpl implements RoleDAO {

	@Autowired
    SessionFactory sessionFactory;

	@Override
	public void createRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(role);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(role);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(role);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Role findRoleById(Long roleId) {
		Session session = sessionFactory.getCurrentSession();
		Role role = null;
		try {
			role = (Role) session.get(Role.class, roleId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return role;
	}

	@Override
	public Role findRoleByName(String roleName) {
		Session session = sessionFactory.getCurrentSession();
		Role role = null;
		try {
			role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("roleName", roleName)).uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAllRoles() {
		Session session = sessionFactory.getCurrentSession();
		List<Role> roles = new ArrayList<Role>();
		try {
			roles = session.createCriteria(Role.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return roles;
	}

}
