package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.OrderStatus;

public interface OrderStatusDao extends DBTables {
	/**
	 * \ createNewStatus @
	 */
	public void createNewStatus(String status);

	/**
	 * getAllStatus @
	 */
	public List<OrderStatus> getAllStatus();

	/**
	 * deleteStatusByName
	 * 
	 * @
	 */
	public void deleteStatusByName(String status);

	/**
	 * getStatusByID
	 * 
	 * @param status_id
	 * @return @
	 */
	public OrderStatus getStatusByID(Integer status_id);
}
