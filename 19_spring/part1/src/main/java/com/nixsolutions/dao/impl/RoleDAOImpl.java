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

import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.entities.Role;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO<Role> {

	private final static Logger LOG = LogManager.getLogger(RoleDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public void create(Role t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(Role t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(Role t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public Role findByPK(long id) {
		return (Role) sessionFactory.getCurrentSession().get(Role.class, id);
	}

	@Override
	public Role findRoleByName(String rolename) {
		return (Role) sessionFactory.getCurrentSession().createCriteria(Role.class)
				.add(Restrictions.eq("rolename", rolename)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		List<Role> roles = new ArrayList<Role>();
		roles.addAll(sessionFactory.getCurrentSession().createCriteria(Role.class).list());
		return roles;
	}

}
