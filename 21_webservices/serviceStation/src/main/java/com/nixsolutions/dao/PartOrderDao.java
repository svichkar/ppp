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
public interface PartOrderDao {

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
	 * @param order_id
	 *            order_id
	 * @throws SQLException
	 */
	List<PartOrder> getPartsByOrderId(Integer order_id);

	/**
	 * delete Part From Order
	 * 
	 * @param order_id
	 * @param part_id
	 * @return
	 * @throws SQLException
	 */
	void deletePartFromOrder(PartOrder partOrder);

	void saveOrUpdatePartOrder(PartOrder partOrder);

	PartOrder getPartByOrderId(Integer order_id, Integer part_id);
}
