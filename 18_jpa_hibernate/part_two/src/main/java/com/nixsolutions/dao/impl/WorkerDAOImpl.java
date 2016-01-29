package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Worker;
import com.nixsolutions.error.CustomDataException;

public class WorkerDAOImpl implements WorkerDAO<Worker> {

	private final static Logger LOG = LogManager.getLogger(WorkerDAOImpl.class);
	public static SessionFactory sessionFactory;

	public WorkerDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Worker t) {
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
	public void update(Worker t) {
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
	public void delete(Worker t) {
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
	public Worker findByPK(long id) {
		Worker worker = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			worker = (Worker) session.get(Worker.class, id);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return worker;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Worker> getAll() {
		List<Worker> workers = new ArrayList<Worker>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			workers.addAll(session.createCriteria(Worker.class)
					.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list());
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return workers;
	}

}
