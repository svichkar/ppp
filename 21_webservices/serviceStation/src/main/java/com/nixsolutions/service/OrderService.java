package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.OrderInWork;

public interface OrderService {

	/**
	 * get all orders
	 * 
	 * @throws SQLException
	 */
	List<OrderInWork> getAllOrders();

	/**
	 * get all orders
	 * 
	 * @throws SQLException
	 */
	List<OrderInWork> getOrdersByUserName(String userName);

	/**
	 * get order by sqllab.car
	 * 
	 * @param reg_number
	 *            reg_number
	 * @return Order_in_work object
	 * @throws SQLException
	 */
	OrderInWork getOrderInWorkByCar(String regNumber);

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
	OrderInWork getOrderInWorkByCustomer(String lastName, String firstName);

	/**
	 * get order by order id
	 * 
	 * @param orderId
	 * @return Order_in_work object
	 * @throws SQLException
	 */
	OrderInWork getOrderByID(Integer orderId);

	/**
	 * get order by order id
	 * 
	 * @param orderId
	 * @return Order_in_work object
	 * @throws SQLException
	 */
	OrderInWork getOrderByID(String orderId);

	/**
	 * create new order
	 * 
	 * @param car_id
	 *            car_id
	 * @param description
	 *            order description
	 * @throws SQLException
	 */
	void createNewOrder(OrderInWork orderInWork);

	void createNewOrder(String regNumber, String description);

	/**
	 * change order status
	 * 
	 * @throws SQLException
	 */
	void changeOrderStatusByOrderID(String orderId, String orderStatusId);

	/**
	 * @param orderId
	 * @param description
	 */
	void updateOrderDescriptionByID(String orderId, String description);

	boolean deleteOrderByID(Integer orderId);

	boolean deleteOrderByID(String orderId);

}
