package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Role> getAllRoles() {
		LOG.entry();
		List<Role> roles = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Role.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			roles = criteria.list();
		return LOG.exit(roles);
	}

	@Override
	public Role getRoleById(Long roleId) {
		LOG.entry(roleId);
		Role role = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Role.class)
					.add(Restrictions.eq("roleId", roleId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			role = (Role) criteria.uniqueResult();
		return LOG.exit(role);
	}

	@Override
	public Role getRoleByName(String name) {
		LOG.entry(name);
		Role role = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Role.class).add(Restrictions.eq("name", name))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			role = (Role) criteria.uniqueResult();
		return LOG.exit(role);
	}

	@Override
	public void createRole(Role role) {
		LOG.entry(role);
		Session session = sessionFactory.getCurrentSession();
			session.save(role);
	}

	@Override
	public void updateRole(Role role) {
		LOG.entry(role);
		Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(role);
	}

	@Override
	public void deleteRole(Role role) {
		LOG.entry(role);
		Session session = sessionFactory.getCurrentSession();
			session.delete(role);
	}
}
