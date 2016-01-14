package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.WorkerStatus;

public interface WorkerStatusService {

	/**
	 * get all statuses
	 * 
	 * @return list of the statuses
	 * @throws SQLException
	 */
	List<WorkerStatus> getAllWorker_statuses();

	/**
	 * create new status
	 * 
	 * @param statusName
	 *            status Name
	 * @throws SQLException
	 */
	void createNewStatus(String statusName);

	/**
	 * delete status
	 * 
	 * @param statusName
	 *            status Name
	 * @return
	 * @throws SQLException
	 */
	boolean deleteStatusByName(String statusName);

	/**
	 * getWorkerStatus
	 * 
	 * @param worker_status_id
	 * @return
	 * @throws SQLException
	 */
	WorkerStatus getWorkerStatus(Integer workerStatusId);

	WorkerStatus getWorkerStatus(String workerStatusName);

}
