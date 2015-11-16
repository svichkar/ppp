/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Order_worker;

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
	
	/**
	 * get all of the assignations
	 * @return list of the assignations
	 */
	public List<Order_worker> getAll();
}
