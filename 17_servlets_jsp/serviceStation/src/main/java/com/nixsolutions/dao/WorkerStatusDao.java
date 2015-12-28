/**
 * 
 */
package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.WorkerStatus;

/**
 * @author mixeyes
 *
 */
public interface WorkerStatusDao extends DBTables {

	/**
	 * get all statuses
	 * 
	 * @return list of the statuses @
	 */
	public List<WorkerStatus> getAllWorker_statuses();

	/**
	 * create new status
	 * 
	 * @param statusName
	 *            status Name @
	 */
	public void createNewStatus(String statusName);

	/**
	 * delete status
	 * 
	 * @param statusName
	 *            status Name @
	 */
	public void deleteStatusByName(String statusName);

	/**
	 * getWorkerStatus
	 * 
	 * @param worker_status_id
	 * @return @
	 */
	public WorkerStatus getWorkerStatus(Integer worker_status_id);
}
