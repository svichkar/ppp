/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.WorkerStatusDao;
import com.nixsolutions.entity.WorkerStatus;
import com.nixsolutions.service.WorkerStatusService;

/**
 * @author mixeyes
 *
 */
@Service
public class WorkerStatusServiceImpl implements WorkerStatusService {

	@Autowired
	private WorkerStatusDao statusDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.WorkerStatusService#getAllWorker_statuses()
	 */
	@Override
	public List<WorkerStatus> getAllWorker_statuses() {
		return statusDao.getAllWorker_statuses();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerStatusService#createNewStatus(java.lang.
	 * String)
	 */
	@Override
	public void createNewStatus(String statusName) {
		statusDao.createNewStatus(statusName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerStatusService#deleteStatusByName(java.lang
	 * .String)
	 */
	@Override
	public boolean deleteStatusByName(String statusName) {
		return statusDao.deleteStatusByName(statusName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerStatusService#getWorkerStatus(java.lang.
	 * Integer)
	 */
	@Override
	public WorkerStatus getWorkerStatus(Integer worker_status_id) {
		return statusDao.getWorkerStatus(worker_status_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerStatusService#getWorkerStatus(java.lang.
	 * String)
	 */
	@Override
	public WorkerStatus getWorkerStatus(String worker_status_name) {
		return statusDao.getWorkerStatus(worker_status_name);
	}

}
