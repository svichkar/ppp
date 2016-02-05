package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.OrderStatus;

public interface OrderStatusService {

	public OrderStatus getOrderStatusById(long id);

	public List<OrderStatus> getAllOrderStatus();

	public void addOrderStatus(OrderStatus orderStatus);

	public void updateOrderStatus(OrderStatus orderStatus);

	public void deleteOrderStatus(OrderStatus orderStatus);
}
