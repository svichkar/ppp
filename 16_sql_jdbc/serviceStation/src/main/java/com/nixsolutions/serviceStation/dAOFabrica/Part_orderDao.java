/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Part_order;

/**
 * @author mixeyes
 *
 */
public interface Part_orderDao extends DBTables {
	/**
	 * get Parts By Order Id
	 * 
	 * @param order_id
	 *            order_id
	 */
	public List<Part_order> getPartsByOrderId(Integer order_id);

	/**
	 * set sqllab.part to order
	 * 
	 * @param order_id
	 * @param part_id
	 */
	public void setPartToOrder(Integer order_id, Integer part_id, Integer amount);

	/**
	 * delete Part From Order
	 * 
	 * @param order_id
	 * @param part_id
	 */
	public void deletePartFromOrder(Integer order_id, Integer part_id);
}
