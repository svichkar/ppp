/**
 * 
 */
package com.nixsolutions.dao;

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
	 * @ 
	 */
	public List<PartOrder> getAllParts() ;

	/**
	 * get Parts By Order Id
	 * 
	 * @param orderId
	 *            orderId
	 * @ 
	 */
	public List<PartOrder> getPartsByOrderId(Integer orderId) ;

	/**
	 * set sqllab.part to order
	 * 
	 * @param orderId
	 * @param part_id
	 * @ 
	 */
	public void setPartToOrder(Integer orderId, Integer part_id, Integer amount) ;

	/**
	 * delete Part From Order
	 * 
	 * @param orderId
	 * @param part_id
	 * @ 
	 */
	public void deletePartFromOrder(Integer orderId, Integer part_id) ;
}
