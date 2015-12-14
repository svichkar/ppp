package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.hibernate.entity.OrderStatus;

public interface OrderStatusService {

	List<OrderStatus> getAllOrderStatuses();

	OrderStatus getOrderStatusById(int id);
}
