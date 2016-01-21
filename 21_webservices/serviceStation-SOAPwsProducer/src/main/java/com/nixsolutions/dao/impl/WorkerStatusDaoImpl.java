/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.WorkerStatusDao;
import com.nixsolutions.entity.WorkerStatus;

/**
 * @author mixeyes
 *
 */
@Repository("workerStatusDao")
@Transactional
public class WorkerStatusDaoImpl implements WorkerStatusDao {
	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.WorkerStatusDao#getAllWorker_statuses()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerStatus> getAllWorker_statuses() {
		List<WorkerStatus> workerStatus = new ArrayList<WorkerStatus>();
		try {
			workerStatus = sessionFactory.getCurrentSession().createCriteria(WorkerStatus.class).list();
		} catch (Exception ex) {
			logger.error(ex);
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
	public WorkerStatus getWorkerStatus(Integer workerStatusId) {
		WorkerStatus status = null;
		try {
			status = (WorkerStatus) sessionFactory.getCurrentSession().createCriteria(WorkerStatus.class)
					.add(Restrictions.eq("workerStatusId", workerStatusId)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return status;
	}

	@Override
	public WorkerStatus getWorkerStatus(String workerStatusName) {
		WorkerStatus status = null;
		try {
			status = (WorkerStatus) sessionFactory.getCurrentSession().createCriteria(WorkerStatus.class)
					.add(Restrictions.eq("workerStatusName", workerStatusName)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
		status.setWorkerStatusName(statusName);
		try {
			sessionFactory.getCurrentSession().save(status);
		} catch (Exception ex) {
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
				.filter(x -> x.getWorkerStatusName().equalsIgnoreCase(statusName)).collect(Collectors.toList())
				.get(0);
		Integer id = status.getWorkerStatusId();
		try {
			sessionFactory.getCurrentSession().delete(status);
		} catch (Exception ex) {
			logger.error(ex);
		}
		status = getWorkerStatus(id);
		return status == null;
	}
}
