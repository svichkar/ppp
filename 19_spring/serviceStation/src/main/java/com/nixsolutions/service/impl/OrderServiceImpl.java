/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CarDao;
import com.nixsolutions.dao.OrderInWorkDao;
import com.nixsolutions.dao.OrderStatusDao;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.service.OrderService;

/**
 * @author mixeyes
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderInWorkDao orderDao;
	@Autowired
	private OrderStatusDao orderStatus;
	@Autowired
	private CarDao carDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.OrderService#getAllOrders()
	 */
	@Override
	public List<OrderInWork> getAllOrders() {
		return orderDao.getAllOrders();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.OrderService#getOrderInWorkByCar(java.lang.
	 * String)
	 */
	@Override
	public OrderInWork getOrderInWorkByCar(String reg_number) {
		return orderDao.getOrderInWorkByCar(reg_number);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#getOrderInWorkByCustomer(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public OrderInWork getOrderInWorkByCustomer(String last_name, String first_name) {
		return orderDao.getOrderInWorkByCustomer(last_name, first_name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#getOrderByID(java.lang.Integer)
	 */
	@Override
	public OrderInWork getOrderByID(Integer order_id) {
		return orderDao.getOrderByID(order_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#getOrderByID(java.lang.Integer)
	 */
	@Override
	public OrderInWork getOrderByID(String order_id) {
		return orderDao.getOrderByID(Integer.parseInt(order_id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#createNewOrder(com.nixsolutions.
	 * entity.OrderInWork)
	 */
	@Override
	public void createNewOrder(OrderInWork orderInWork) {
		orderInWork.setOrder_status(orderStatus.getStatusByID(1));
		orderDao.createNewOrder(orderInWork);
	}

	@Override
	public void createNewOrder(String regNumber, String description) {
		OrderInWork order = new OrderInWork();
		order.setCar(carDao.getCarByRegNumber(regNumber));
		order.setOrder_description(description);
		order.setDatetime_start(new Date());
		createNewOrder(order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#changeOrderStatusByOrderID(java.
	 * lang.Integer, java.lang.Integer)
	 */
	@Override
	public void changeOrderStatusByOrderID(String order_id, String order_status_id) {
		OrderInWork orderInWork = getOrderByID(order_id);
		orderInWork.setOrder_status(orderStatus.getStatusByID(Integer.decode(order_status_id)));
		if (order_status_id.equals("3"))
			orderInWork.setDatetime_finish(new Date());
		orderDao.updateOrder(orderInWork);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#updateOrderDescriptionByID(java.
	 * lang.Integer, java.lang.String)
	 */
	@Override
	public void updateOrderDescriptionByID(String order_id, String description) {
		OrderInWork orderInWork = getOrderByID(order_id);
		orderInWork.setOrder_description(description);
		orderDao.updateOrder(orderInWork);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#deleteOrderByID(java.lang.Integer)
	 */
	@Override
	public boolean deleteOrderByID(Integer order_id) {
		return orderDao.deleteOrderByID(order_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#deleteOrderByID(java.lang.Integer)
	 */
	@Override
	public boolean deleteOrderByID(String order_id) {
		return deleteOrderByID(Integer.decode(order_id));
	}

	@Override
	public List<OrderInWork> getOrdersByUserName(String userName) {
		return orderDao.getOrdersByUserName(userName);
	}

}
