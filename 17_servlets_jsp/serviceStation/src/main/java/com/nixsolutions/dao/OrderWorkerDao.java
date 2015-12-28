/**
 * 
 */
package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.OrderWorker;

/**
 * @author mixeyes
 *
 */
public interface OrderWorkerDao extends DBTables {
	/**
	 * assign Worker To Order
	 * 
	 * @param orderId
	 * @param worker_id
	 * @
	 */
	public void assignWorkerToOrder(Integer orderId, Integer worker_id);

	/**
	 * change Status
	 * 
	 * @param orderId
	 * @param worker_id
	 * @param isCompleted
	 * @
	 */
	public void changeStatus(Integer orderId, Integer worker_id, boolean isCompleted);

	/**
	 * get all of the assignations
	 * 
	 * @return list of the assignations @
	 */
	public List<OrderWorker> getAll();
}
