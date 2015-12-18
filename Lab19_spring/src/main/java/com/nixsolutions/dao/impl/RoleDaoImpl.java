package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

	private static Logger LOG = LogManager.getLogger(RoleDaoImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	public RoleDaoImpl() {
	}

	public void create(Role role) {
		try {
			sessionFactory.getCurrentSession().save(role);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	public void update(Role role) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(role);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	public void delete(Role role) {

		try {
			sessionFactory.getCurrentSession().delete(role);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	public Role getByRoleId(int roleId) {
		Role role = new Role();
		try {
			role = (Role) sessionFactory.getCurrentSession().get(Role.class, roleId);
		} catch (Exception ex) {
			LOG.error(ex);
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	public Role getByRoleName(String roleName) {
		Role role = new Role();
		try {
			Criteria c = sessionFactory.getCurrentSession().createCriteria(Role.class);
			c.add(Restrictions.eq("roleName", roleName));
			List<Role> list = c.list();
			role = (Role) list.get(0);
		} catch (Exception ex) {
			LOG.error(ex);
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		List<Role> toReturn = new ArrayList<Role>();
		try {
			toReturn.addAll(sessionFactory.getCurrentSession().createCriteria(Role.class).list());
		} catch (Exception ex) {
			LOG.error(ex);
		}
		return toReturn;
	}
}