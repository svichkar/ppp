/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.WorkerDao;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author Михаил
 *
 */
public class WorkerDaoImpl implements WorkerDao {
	private final static Logger logger = LogManager.getLogger();

	/*
	 * get All Workers
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getAllWorkers()
	 */
	@Override
	public List<Worker> getAllWorkers() {
		List<Worker> workers = new ArrayList<Worker>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			workers = session.createCriteria(Worker.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return workers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#
	 * getAllWorkersBySpecialization(java.lang.String)
	 */
	@Override
	public List<Worker> getAllWorkersBySpecialization(String specialization) {
		List<Worker> workers = new ArrayList<Worker>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			workers = session.createCriteria(Worker.class)
					.add(Restrictions.eq("specialization.specialization_name", specialization)).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return workers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getWorkerByLastName(
	 * java.lang.String)
	 */
	@Override
	public Worker getWorker(String last_name, String first_name) {
		Worker worker = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			worker = (Worker) session.createCriteria(Worker.class).add(Restrictions.eq("lastName", last_name))
					.add(Restrictions.eq("firstName", first_name)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return worker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getWorker(java.lang.
	 * Integer)
	 */
	@Override
	public Worker getWorker(Integer user_id) {
		Worker worker = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			worker = (Worker) session.createCriteria(Worker.class).add(Restrictions.eq("user.user_id", user_id))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return worker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#
	 * getWorkerStatusByLastName(java.lang.String)
	 */
	@Override
	public String getWorkerStatus(String last_name, String first_name) {
		Worker worker = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			worker = (Worker) session.createCriteria(Worker.class).add(Restrictions.eq("last_name", last_name))
					.add(Restrictions.eq("first_name", first_name)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return worker.getWorker_status().getWorker_status_name();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#createWorker(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createWorker(Worker worker) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(worker);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.WorkerDao#updateWorker(com.nixsolutions.entity.
	 * Worker)
	 */
	@Override
	public void updateWorker(Worker worker) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(worker);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#deleteWorker(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteWorker(String last_name, String first_name) {
		Worker worker = getWorker(last_name, first_name);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(worker);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}finally {
			if(session.isOpen())
				session.close();
		}
		worker = getWorker(last_name, first_name);
		if (worker == null)
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getWorker(java.lang.
	 * Integer)
	 */
	@Override
	public Worker getWorkerByID(Integer worker_id) {
		Worker worker = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			worker = (Worker) session.createCriteria(Worker.class).add(Restrictions.eq("workerId", worker_id))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return worker;
	}

}
