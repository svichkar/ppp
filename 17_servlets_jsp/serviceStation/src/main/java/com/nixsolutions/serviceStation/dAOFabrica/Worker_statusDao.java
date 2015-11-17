/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Worker_status;

/**
 * @author mixeyes
 *
 */
public interface Worker_statusDao extends DBTables {

	/**
	 * get all statuses
	 * @return list of the statuses
	 */
	public List<Worker_status> getAllWorker_statuses();

	/**
	 * create new status
	 * 
	 * @param statusName
	 *            status Name
	 */
	public void createNewStatus(String statusName);

	/**
	 * delete status
	 * 
	 * @param statusName
	 *            status Name
	 */
	public void deleteStatusByName(String statusName);

	/**
	 * getWorkerStatus
	 * @param worker_status_id
	 * @return
	 */
	public Worker_status getWorkerStatus(Integer worker_status_id);
}
