package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.WorkerSpecializationDAO;
import com.nixsolutions.hibernate.entity.WorkerSpecialization;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class WorkerSpecializationDAOImpl implements WorkerSpecializationDAO {

	public static Logger LOG = LogManager.getLogger(WorkerSpecializationDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public WorkerSpecialization createFrom(WorkerSpecialization entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		WorkerSpecialization workerSpecialization = null;
		try {
			session.saveOrUpdate("workerSpecialization", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		try {
			workerSpecialization = (WorkerSpecialization) session.get(WorkerSpecialization.class, entity.getSpecializationId());
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return workerSpecialization;
	}

	@Override
	public void update(WorkerSpecialization entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("workerSpecialization", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(WorkerSpecialization entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("workerSpecialization", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public WorkerSpecialization getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		WorkerSpecialization workerSpecialization = null;
		Transaction tx = session.beginTransaction();
		try {
			workerSpecialization = (WorkerSpecialization) session.byId(WorkerSpecialization.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return workerSpecialization;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerSpecialization> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<WorkerSpecialization> workerSpecializationList = null;
		Transaction tx = session.beginTransaction();
		try {
			workerSpecializationList = session.createCriteria(WorkerSpecialization.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return workerSpecializationList;
	}

}
