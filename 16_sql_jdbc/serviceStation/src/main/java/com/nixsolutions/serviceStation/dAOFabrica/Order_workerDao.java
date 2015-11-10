/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import com.nixsolutions.serviceStation.dbCommon.DBTables;

/**
 * @author mixeyes
 *
 */
public interface Order_workerDao extends DBTables {
	/**
	 * assign Worker To Order
	 * 
	 * @param order_id
	 * @param worker_id
	 */
	public void assignWorkerToOrder(Integer order_id, Integer worker_id);

	/**
	 * change Status
	 * 
	 * @param order_id
	 * @param worker_id
	 * @param isCompleted
	 */
	public void changeStatus(Integer order_id, Integer worker_id, boolean isCompleted);
}
