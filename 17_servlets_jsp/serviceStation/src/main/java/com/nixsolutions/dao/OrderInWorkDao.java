package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.OrderInWork;

public interface OrderInWorkDao extends DBTables {

	/**
	 * get all orders
	 * @ 
	 */
	public List<OrderInWork> getAllOrders() ;

	/**
	 * get order by sqllab.car 
	 * 
	 * @param regNumber
	 *            regNumber
	 * @return Order_in_work object
	 * @ 
	 */
	public OrderInWork getOrderInWorkByCar(String regNumber) ;

	/**
	 * get order by customer
	 * 
	 * @param lastName
	 *            sqllab.customer lastName
	 * @param firstName
	 *            sqllab.customer firstName *
	 * @return Order_in_work object
	 * @ 
	 */
	public OrderInWork getOrderInWorkByCustomer(String lastName, String firstName) ;

	/**
	 * get order by order id
	 * 
	 * @param orderId
	 * @return Order_in_work object
	 * @ 
	 */
	public OrderInWork getOrderByID(Integer orderId) ;

	/**
	 * create new order
	 * 
	 * @param  carId
	 *             carId
	 * @param description
	 *            order description
	 * @ 
	 */
	public void createNewOrder(Integer  carId, String description) ;

	/**
	 * change order status
	 * @ 
	 */
	public void changeOrderStatusByOrderID(Integer orderId, Integer orderStatusId) ;
}
