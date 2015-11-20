/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
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
	 * @param order_id
	 * @param worker_id
	 * @throws SQLException 
	 */
	public void assignWorkerToOrder(Integer order_id, Integer worker_id) throws SQLException;

	/**
	 * change Status
	 * 
	 * @param order_id
	 * @param worker_id
	 * @param isCompleted
	 * @throws SQLException
	 */
	public void changeStatus(Integer order_id, Integer worker_id, boolean isCompleted) throws SQLException;
	
	/**
	 * get all of the assignations
	 * @return list of the assignations
	 * @throws SQLException 
	 */
	public List<OrderWorker> getAll() throws SQLException;
}
