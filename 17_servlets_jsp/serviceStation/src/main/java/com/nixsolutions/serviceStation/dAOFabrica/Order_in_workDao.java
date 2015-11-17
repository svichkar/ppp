package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Order_in_work;

public interface Order_in_workDao extends DBTables {

	/**
	 * get all orders
	 */
	public List<Order_in_work> getAllOrders();

	/**
	 * get order by sqllab.car 
	 * 
	 * @param vin_number
	 *            vin_number
	 * @return Order_in_work object
	 */
	public Order_in_work getOrderInWorkByCar(String vin_number);

	/**
	 * get order by customer
	 * 
	 * @param last_name
	 *            sqllab.customer last_name
	 * @param first_name
	 *            sqllab.customer first_name *
	 * @return Order_in_work object
	 */
	public Order_in_work getOrderInWorkByCustomer(String last_name, String first_name);

	/**
	 * get order by order id
	 * 
	 * @param order_id
	 * @return Order_in_work object
	 */
	public Order_in_work getOrderByID(Integer order_id);

	/**
	 * create new order
	 * 
	 * @param  car_id
	 *             car_id
	 * @param description
	 *            order description
	 */
	public void createNewOrder(Integer  car_id, String description);

	/**
	 * change order status
	 */
	public void changeOrderStatusByOrderID(Integer order_id, Integer order_status_id);
}
