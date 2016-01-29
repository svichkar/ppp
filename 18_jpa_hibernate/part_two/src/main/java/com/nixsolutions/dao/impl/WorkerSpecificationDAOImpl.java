package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.WorkerSpecificationDAO;
import com.nixsolutions.entities.WorkerSpecification;
import com.nixsolutions.error.CustomDataException;

public class WorkerSpecificationDAOImpl implements WorkerSpecificationDAO<WorkerSpecification> {

	private final static Logger LOG = LogManager.getLogger(WorkerSpecificationDAOImpl.class);
	public static SessionFactory sessionFactory;

	public WorkerSpecificationDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(WorkerSpecification t) {
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
	public void update(WorkerSpecification t) {
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
	public void delete(WorkerSpecification t) {
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
	public WorkerSpecification findByPK(long id) {
		WorkerSpecification ws = new WorkerSpecification();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			ws = (WorkerSpecification) session.get(WorkerSpecification.class, id);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return ws;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerSpecification> getAll() {
		List<WorkerSpecification> workerSpecifications = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			workerSpecifications.addAll(session.createCriteria(WorkerSpecification.class).list());
			tx.commit();

		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return workerSpecifications;
	}

}
