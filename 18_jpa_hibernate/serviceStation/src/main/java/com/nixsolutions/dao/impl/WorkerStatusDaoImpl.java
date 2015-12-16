/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.WorkerStatusDao;
import com.nixsolutions.entity.WorkerStatus;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author mixeyes
 *
 */
public class WorkerStatusDaoImpl implements WorkerStatusDao {
	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.WorkerStatusDao#getAllWorker_statuses()
	 */
	@Override
	public List<WorkerStatus> getAllWorker_statuses() {
		List<WorkerStatus> workerStatus = new ArrayList<WorkerStatus>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			workerStatus = session.createCriteria(WorkerStatus.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return workerStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.WorkerStatusDao#getWorkerStatus(java.lang.Integer)
	 */
	@Override
	public WorkerStatus getWorkerStatus(Integer worker_status_id) {
		WorkerStatus status = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			status = (WorkerStatus) session.createCriteria(WorkerStatus.class)
					.add(Restrictions.eq("workerStatusId", worker_status_id)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return status;
	}

	@Override
	public WorkerStatus getWorkerStatus(String worker_status_name) {
		WorkerStatus status = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			status = (WorkerStatus) session.createCriteria(WorkerStatus.class)
					.add(Restrictions.eq("workerStatusName", worker_status_name)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.WorkerStatusDao#createNewStatus(java.lang.String)
	 */
	@Override
	public void createNewStatus(String statusName) {
		WorkerStatus status = new WorkerStatus();
		status.setWorker_status_name(statusName);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(status);
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
	 * com.nixsolutions.dao.WorkerStatusDao#deleteStatusByName(java.lang.String)
	 */
	@Override
	public boolean deleteStatusByName(String statusName) {
		WorkerStatus status = getAllWorker_statuses().stream()
				.filter(x -> x.getWorker_status_name().equalsIgnoreCase(statusName)).collect(Collectors.toList())
				.get(0);
		Integer id = status.getWorker_status_id();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(status);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
		status = getWorkerStatus(id);
		if (status == null)
			return true;
		else
			return false;
	}
}
