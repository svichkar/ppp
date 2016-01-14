package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.OrderStatus;

public interface OrderStatusService {
	/**
	 * \ createNewStatus
	 * 
	 * @throws SQLException
	 */
	 void createNewStatus(String status);

	/**
	 * getAllStatus
	 * 
	 * @throws SQLException
	 */
	 List<OrderStatus> getAllStatus();

	/**
	 * deleteStatusByName
	 * @return 
	 * 
	 * @throws SQLException
	 */
	 boolean deleteStatusByID(Integer orderStatusId);

	/**
	 * getStatusByID
	 * 
	 * @param status_id
	 * @return
	 * @throws SQLException
	 */
	 OrderStatus getStatusByID(Integer statusId);
}
