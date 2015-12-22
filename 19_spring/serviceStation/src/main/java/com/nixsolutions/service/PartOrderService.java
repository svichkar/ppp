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
	public List<PartOrder> getAllParts();

	/**
	 * get Parts By Order Id
	 * 
	 * @param order_id
	 *            order_id
	 * @throws SQLException
	 */
	public List<PartOrder> getPartsByOrderId(Integer order_id);

	/**
	 * get Parts By Order Id
	 * 
	 * @param order_id
	 *            order_id
	 * @throws SQLException
	 */
	public List<PartOrder> getPartsByOrderId(String order_id);

	/**
	 * set sqllab.part to order
	 * 
	 * @param order_id
	 * @param part_id
	 * @throws SQLException
	 */
	public void setPartToOrder(Integer order_id, Integer part_id, Integer amount);

	/**
	 * set sqllab.part to order
	 * 
	 * @param order_id
	 * @param part_id
	 * @throws SQLException
	 */
	public void setPartToOrder(String order_id, String part_id, String amount);

	/**
	 * delete Part From Order
	 * 
	 * @param order_id
	 * @param part_id
	 * @return
	 * @throws SQLException
	 */
	public void deletePartFromOrder(String order_id, String part_id);

	public void updatePartOrder(Integer order_id, Integer part_id, Integer amount);

	public PartOrder getPartByOrderId(Integer order_id, Integer part_id);

}
