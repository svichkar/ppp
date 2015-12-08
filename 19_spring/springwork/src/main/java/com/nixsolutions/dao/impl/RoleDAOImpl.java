package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.hibernate.entity.Role;

@Repository("roleDAO")
@Transactional
public class RoleDAOImpl implements RoleDAO {

	public static Logger LOG = LogManager.getLogger(RoleDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(Role entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("role", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(Role entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("role", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Role entity) {
		try {
			sessionFactory.getCurrentSession().delete("role", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public Role getByPK(int id) {
		try {
			return (Role) sessionFactory.getCurrentSession().byId(Role.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Role.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}
}
