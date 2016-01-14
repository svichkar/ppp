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
	public OrderInWork getOrderInWorkByCar(String regNumber) {
		return orderDao.getOrderInWorkByCar(regNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#getOrderInWorkByCustomer(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public OrderInWork getOrderInWorkByCustomer(String lastName, String firstName) {
		return orderDao.getOrderInWorkByCustomer(lastName, firstName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#getOrderByID(java.lang.Integer)
	 */
	@Override
	public OrderInWork getOrderByID(Integer orderId) {
		return orderDao.getOrderByID(orderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#getOrderByID(java.lang.Integer)
	 */
	@Override
	public OrderInWork getOrderByID(String orderId) {
		return orderDao.getOrderByID(Integer.parseInt(orderId));
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
		orderInWork.setOrderStatus(orderStatus.getStatusByID(1));
		orderDao.createNewOrder(orderInWork);
	}

	@Override
	public void createNewOrder(String regNumber, String description) {
		OrderInWork order = new OrderInWork();
		order.setCar(carDao.getCarByRegNumber(regNumber));
		order.setOrderDescription(description);
		order.setDatetimeStart(new Date());
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
	public void changeOrderStatusByOrderID(String orderId, String orderStatusId) {
		OrderInWork orderInWork = getOrderByID(orderId);
		orderInWork.setOrderStatus(orderStatus.getStatusByID(Integer.decode(orderStatusId)));
		if (orderStatusId.equals("3"))
			orderInWork.setDatetimeFinish(new Date());
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
	public void updateOrderDescriptionByID(String orderId, String description) {
		OrderInWork orderInWork = getOrderByID(orderId);
		orderInWork.setOrderDescription(description);
		orderDao.updateOrder(orderInWork);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#deleteOrderByID(java.lang.Integer)
	 */
	@Override
	public boolean deleteOrderByID(Integer orderId) {
		return orderDao.deleteOrderByID(orderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderService#deleteOrderByID(java.lang.Integer)
	 */
	@Override
	public boolean deleteOrderByID(String orderId) {
		return deleteOrderByID(Integer.decode(orderId));
	}

	@Override
	public List<OrderInWork> getOrdersByUserName(String userName) {
		return orderDao.getOrdersByUserName(userName);
	}

}
