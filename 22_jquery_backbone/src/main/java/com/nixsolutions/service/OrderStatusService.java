package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.OrderStatus;

public interface OrderStatusService {

	OrderStatus getOrderStatusById(long id);

	List<OrderStatus> getAllOrderStatus();

	void addOrderStatus(OrderStatus orderStatus);

	void updateOrderStatus(OrderStatus orderStatus);

	void deleteOrderStatus(OrderStatus orderStatus);
}
