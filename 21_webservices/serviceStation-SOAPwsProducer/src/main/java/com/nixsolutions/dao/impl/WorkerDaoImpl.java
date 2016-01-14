/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.WorkerDao;
import com.nixsolutions.entity.Worker;

/**
 * @author Михаил
 *
 */
@Repository("workerDao")
@Transactional
public class WorkerDaoImpl implements WorkerDao {
	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * get All Workers
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getAllWorkers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Worker> getAllWorkers() {
		List<Worker> workers = new ArrayList<Worker>();
		try {
			workers = sessionFactory.getCurrentSession().createCriteria(Worker.class).list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return workers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#
	 * getAllWorkersBySpecialization(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Worker> getAllWorkersBySpecialization(String specialization) {
		List<Worker> workers = new ArrayList<Worker>();
		try {
			workers = sessionFactory.getCurrentSession().createCriteria(Worker.class)
					.add(Restrictions.eq("specialization.specialization_name", specialization)).list();
		} catch (Exception ex) {
			logger.error(ex);
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
	public Worker getWorker(String lastName, String firstName) {
		Worker worker = null;
		try {
			worker = (Worker) sessionFactory.getCurrentSession().createCriteria(Worker.class)
					.add(Restrictions.eq("last_name", lastName)).add(Restrictions.eq("first_name", firstName))
					.uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
	public Worker getWorker(Integer userId) {
		Worker worker = null;
		try {
			worker = (Worker) sessionFactory.getCurrentSession().createCriteria(Worker.class)
					.add(Restrictions.eq("user.user_id", userId)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
	public String getWorkerStatus(String lastName, String firstName) {
		Worker worker = null;
		try {
			worker = (Worker) sessionFactory.getCurrentSession().createCriteria(Worker.class)
					.add(Restrictions.eq("last_name", lastName)).add(Restrictions.eq("first_name", firstName))
					.uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return worker.getWorkerStatus().getWorkerStatusName();
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
		try {
			sessionFactory.getCurrentSession().save(worker);
		} catch (Exception ex) {
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
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(worker);
		} catch (Exception ex) {
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
	public boolean deleteWorker(String lastName, String firstName) {
		Worker worker = getWorker(lastName, firstName);
		try {
			sessionFactory.getCurrentSession().delete(worker);
		} catch (Exception ex) {
			logger.error(ex);
		}
		worker = getWorker(lastName, firstName);
		return worker == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#deleteWorker(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void deleteWorker(Worker worker) {
		try {
			sessionFactory.getCurrentSession().delete(worker);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getWorker(java.lang.
	 * Integer)
	 */
	@Override
	public Worker getWorkerByID(Integer workerId) {
		Worker worker = null;
		try {
			worker = (Worker) sessionFactory.getCurrentSession().createCriteria(Worker.class)
					.add(Restrictions.eq("worker_id", workerId)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return worker;
	}

}
