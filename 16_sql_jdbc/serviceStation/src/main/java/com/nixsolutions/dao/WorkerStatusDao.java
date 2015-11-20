/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
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
	 * @return list of the statuses
	 * @throws SQLException
	 */
	public List<WorkerStatus> getAllWorker_statuses() throws SQLException;

	/**
	 * create new status
	 * 
	 * @param statusName
	 *            status Name
	 * @throws SQLException
	 */
	public void createNewStatus(String statusName) throws SQLException;

	/**
	 * delete status
	 * 
	 * @param statusName
	 *            status Name
	 * @throws SQLException
	 */
	public void deleteStatusByName(String statusName) throws SQLException;

	/**
	 * getWorkerStatus
	 * 
	 * @param worker_status_id
	 * @return
	 * @throws SQLException 
	 */
	public WorkerStatus getWorkerStatus(Integer worker_status_id) throws SQLException;
}
