package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Status;
import com.nixsolutions.error.CustomDataException;

public class StatusDAOImpl implements StatusDAO<Status> {

	private final static Logger LOG = LogManager.getLogger(StatusDAOImpl.class);
	public static SessionFactory sessionFactory;

	public StatusDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Status t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void update(Status t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void delete(Status t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public Status findByPK(long id) {
		Status status = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			status = (Status) session.get(Status.class, id);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getAll() {
		List<Status> statuses = new ArrayList<Status>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			statuses.addAll(session.createCriteria(Status.class).list());
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return statuses;
	}
}
