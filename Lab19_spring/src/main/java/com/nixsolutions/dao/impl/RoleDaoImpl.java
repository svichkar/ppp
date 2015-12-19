package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	public RoleDaoImpl() {
	}

	public void create(Role role) {
		sessionFactory.getCurrentSession().save(role);
	}

	public void update(Role role) {
		sessionFactory.getCurrentSession().saveOrUpdate(role);
	}

	public void delete(Role role) {
		sessionFactory.getCurrentSession().delete(role);
	}

	public Role getByRoleId(int roleId) {
		return (Role) sessionFactory.getCurrentSession().get(Role.class, roleId);
	}

	public Role getByRoleName(String roleName) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Role.class);
		c.add(Restrictions.eq("roleName", roleName));
		return (Role) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Role.class).list();
	}
}