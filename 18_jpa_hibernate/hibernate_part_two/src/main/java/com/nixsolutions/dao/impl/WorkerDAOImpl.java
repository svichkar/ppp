package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.hibernate.entity.Worker;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class WorkerDAOImpl implements WorkerDAO {

	public static Logger LOG = LogManager.getLogger(WorkerDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Worker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("worker", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void update(Worker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("worker", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Worker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("worker", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public Worker getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Worker worker = null;
		Transaction tx = session.beginTransaction();
		try {
			worker = (Worker) session.byId(Worker.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return worker;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Worker> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Worker> workerList = null;
		Transaction tx = session.beginTransaction();
		try {
			workerList = session.createCriteria(Worker.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return workerList;
	}

}
