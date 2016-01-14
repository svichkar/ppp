package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.PartOrder;

public interface PartOrderService {

	/**
	 * getAllParts
	 * 
	 * @return list of the all parts
	 * @throws SQLException
	 */
	List<PartOrder> getAllParts();

	/**
	 * get Parts By Order Id
	 * 
	 * @param orderId
	 *            orderId
	 * @throws SQLException
	 */
	List<PartOrder> getPartsByOrderId(Integer orderId);

	/**
	 * get Parts By Order Id
	 * 
	 * @param orderId
	 *            orderId
	 * @throws SQLException
	 */
	List<PartOrder> getPartsByOrderId(String orderId);

	/**
	 * set sqllab.part to order
	 * 
	 * @param orderId
	 * @param partId
	 * @throws SQLException
	 */
	void setPartToOrder(Integer orderId, Integer partId, Integer amount);

	/**
	 * set sqllab.part to order
	 * 
	 * @param orderId
	 * @param partId
	 * @throws SQLException
	 */
	void setPartToOrder(String orderId, String partId, String amount);

	/**
	 * delete Part From Order
	 * 
	 * @param orderId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	void deletePartFromOrder(String orderId, String partId);

	void updatePartOrder(Integer orderId, Integer partId, Integer amount);

	PartOrder getPartByOrderId(Integer orderId, Integer partId);

}
