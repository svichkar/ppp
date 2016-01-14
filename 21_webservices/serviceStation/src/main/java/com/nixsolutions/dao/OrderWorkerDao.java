/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderWorker;

/**
 * @author mixeyes
 *
 */
public interface OrderWorkerDao {

	/**
	 * @param orderWorker
	 */
	void saveOrUpdateOrderWorker(OrderWorker orderWorker);

	/**
	 * change Status
	 * 
	 * @param order_id
	 * @param worker_id
	 * @param isCompleted
	 * @throws SQLException
	 */
	OrderWorker getOrderWorker(Integer order_id, Integer worker_id);

	/**
	 * get all of the assignations
	 * 
	 * @return list of the assignations
	 * @throws SQLException
	 */
	List<OrderWorker> getAll();

	/**
	 * getActiveOrderByWorkerID
	 * 
	 * @param order_id
	 * @param worker_id
	 * @return
	 */
	OrderInWork getActiveOrderByWorkerID(Integer worker_id);

	/**
	 * getWorkerByOrderID
	 * 
	 * @param order_id
	 * @return
	 */
	List<OrderWorker> getWorkersByOrderID(Integer order_id);
}
