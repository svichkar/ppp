package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.bean.OrderBean;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.User;

public interface OrderService {

	List<OrderInWork> getAllOrders();

	List<OrderBean> getAllOrdersAsBeans();

	OrderInWork getOrderById(long id);

	List<OrderInWork> getOrdersByUser(User user);

	List<OrderBean> getOrdersAsBeansByUser(User user);

	List<OrderInWork> getOrdersByCustomer(Customer customer);

	List<OrderInWork> getOrdersByCar(Car car);

	void addOrder(OrderInWork order);

	void updateOrder(OrderInWork order);

	void deleteOrder(OrderInWork order);
}
