package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.bean.OrderBean;
import com.nixsolutions.hibernate.entity.OrderInWork;

public interface OrderService {

	List<OrderInWork> getAllOrders();

	List<OrderBean> getAllOrdersAsBeans();

	OrderInWork getOrderById(long id);

	List<OrderInWork> getOrdersByUserId(int userId);

	List<OrderBean> getOrdersAsBeansByUserId(int userId);

	List<OrderInWork> getOrdersByCustomerId(int customerId);

	List<OrderInWork> getOrdersByCarId(int carId);

	void addOrder(OrderInWork order);

	void updateOrder(OrderInWork order);

	void deleteOrder(OrderInWork order);
}
