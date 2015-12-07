package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.hibernate.entity.Status;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class StatusDAOImpl implements StatusDAO {

	public static Logger LOG = LogManager.getLogger(StatusDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Status entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("status", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void update(Status entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("status", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Status entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("status", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public Status getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Status status = null;
		Transaction tx = session.beginTransaction();
		try {
			status = (Status) session.byId(Status.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Status> statusList = null;
		Transaction tx = session.beginTransaction();
		try {
			statusList = session.createCriteria(Status.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return statusList;
	}
}
