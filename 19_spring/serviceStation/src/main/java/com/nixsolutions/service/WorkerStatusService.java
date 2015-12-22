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
	public List<WorkerStatus> getAllWorker_statuses();

	/**
	 * create new status
	 * 
	 * @param statusName
	 *            status Name
	 * @throws SQLException
	 */
	public void createNewStatus(String statusName);

	/**
	 * delete status
	 * 
	 * @param statusName
	 *            status Name
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteStatusByName(String statusName);

	/**
	 * getWorkerStatus
	 * 
	 * @param worker_status_id
	 * @return
	 * @throws SQLException
	 */
	public WorkerStatus getWorkerStatus(Integer worker_status_id);

	public WorkerStatus getWorkerStatus(String worker_status_name);

}
