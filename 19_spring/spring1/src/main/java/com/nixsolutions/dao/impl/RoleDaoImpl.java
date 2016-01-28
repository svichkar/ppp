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

import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class RoleDaoImpl implements RoleDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Role> getAllRoles() {
		LOG.entry();
		List<Role> roles = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Role.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			roles = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(roles);
	}

	@Override
	public Role getRoleById(Long roleId) {
		LOG.entry(roleId);
		Role role = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Role.class)
					.add(Restrictions.eq("roleId", roleId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			role = (Role) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(role);
	}

	@Override
	public Role getRoleByName(String name) {
		LOG.entry(name);
		Role role = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Role.class).add(Restrictions.eq("name", name))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			role = (Role) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(role);
	}

	@Override
	public void createRole(Role role) {
		LOG.entry(role);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.save(role);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}

	@Override
	public void updateRole(Role role) {
		LOG.entry(role);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.saveOrUpdate(role);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}

	@Override
	public void deleteRole(Role role) {
		LOG.entry(role);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.delete(role);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}
}
