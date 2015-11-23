/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.PartOrder;

/**
 * @author mixeyes
 *
 */
public interface PartOrderDao extends DBTables {

	/**
	 * getAllParts
	 * 
	 * @return list of the all parts
	 * @throws SQLException 
	 */
	public List<PartOrder> getAllParts() throws SQLException;

	/**
	 * get Parts By Order Id
	 * 
	 * @param order_id
	 *            order_id
	 * @throws SQLException 
	 */
	public List<PartOrder> getPartsByOrderId(Integer order_id) throws SQLException;

	/**
	 * set sqllab.part to order
	 * 
	 * @param order_id
	 * @param part_id
	 * @throws SQLException 
	 */
	public void setPartToOrder(Integer order_id, Integer part_id, Integer amount) throws SQLException;

	/**
	 * delete Part From Order
	 * 
	 * @param order_id
	 * @param part_id
	 * @throws SQLException 
	 */
	public void deletePartFromOrder(Integer order_id, Integer part_id) throws SQLException;
}
