package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderWorker;

public interface OrderWorkerService {
	/**
	 * assign Worker To Order
	 * 
	 * @param orderId
	 * @param workerId
	 * @throws SQLException
	 */
	void assignWorkerToOrder(Integer orderId, Integer workerId);

	/**
	 * assign Worker To Order
	 * 
	 * @param orderId
	 * @param workerId
	 * @throws SQLException
	 */
	void assignWorkerToOrder(String orderId, String workerId);

	/**
	 * change Status
	 * 
	 * @param orderId
	 * @param workerId
	 * @param isCompleted
	 * @throws SQLException
	 */
	void changeStatus(Integer orderId, Integer workerId, boolean isCompleted);

	/**
	 * change Status
	 * 
	 * @param orderId
	 * @param workerId
	 * @param isCompleted
	 * @throws SQLException
	 */
	void changeStatus(String orderId, String workerId, boolean isCompleted);

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
	 * @param orderId
	 * @param workerId
	 * @return
	 */
	OrderInWork getActiveOrderByWorkerID(Integer workerId);

	/**
	 * getWorkerByOrderID
	 * 
	 * @param orderId
	 * @return
	 */
	List<OrderWorker> getWorkersByOrderID(Integer orderId);

	/**
	 * getWorkerByOrderID
	 * 
	 * @param orderId
	 * @return
	 */
	List<OrderWorker> getWorkersByOrderID(String orderId);

}
