package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.OrderStatus;

public interface OrderStatusDao {
	/**
	 * \ createNewStatus
	 * 
	 * @throws SQLException
	 */
	public void createNewStatus(String status);

	/**
	 * getAllStatus
	 * 
	 * @throws SQLException
	 */
	public List<OrderStatus> getAllStatus();

	/**
	 * deleteStatusByName
	 * @return 
	 * 
	 * @throws SQLException
	 */
	public boolean deleteStatusByID(Integer order_status_id);

	/**
	 * getStatusByID
	 * 
	 * @param status_id
	 * @return
	 * @throws SQLException
	 */
	public OrderStatus getStatusByID(Integer status_id);
}
