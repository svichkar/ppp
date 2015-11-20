package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.OrderInWork;

public interface OrderInWorkDao extends DBTables {

	/**
	 * get all orders
	 * @throws SQLException 
	 */
	public List<OrderInWork> getAllOrders() throws SQLException;

	/**
	 * get order by sqllab.car 
	 * 
	 * @param reg_number
	 *            reg_number
	 * @return Order_in_work object
	 * @throws SQLException 
	 */
	public OrderInWork getOrderInWorkByCar(String reg_number) throws SQLException;

	/**
	 * get order by customer
	 * 
	 * @param last_name
	 *            sqllab.customer last_name
	 * @param first_name
	 *            sqllab.customer first_name *
	 * @return Order_in_work object
	 * @throws SQLException 
	 */
	public OrderInWork getOrderInWorkByCustomer(String last_name, String first_name) throws SQLException;

	/**
	 * get order by order id
	 * 
	 * @param order_id
	 * @return Order_in_work object
	 * @throws SQLException 
	 */
	public OrderInWork getOrderByID(Integer order_id) throws SQLException;

	/**
	 * create new order
	 * 
	 * @param  car_id
	 *             car_id
	 * @param description
	 *            order description
	 * @throws SQLException 
	 */
	public void createNewOrder(Integer  car_id, String description) throws SQLException;

	/**
	 * change order status
	 * @throws SQLException 
	 */
	public void changeOrderStatusByOrderID(Integer order_id, Integer order_status_id) throws SQLException;
}
