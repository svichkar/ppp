package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.entities.OrderStatus;
import com.nixsolutions.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

	@Autowired
	private OrderStatusDAO orderStatusDaoImpl;

	@Override
	public OrderStatus getOrderStatusById(long id) {
		return orderStatusDaoImpl.findByPK(id);
	}

	@Override
	public List<OrderStatus> getAllOrderStatus() {
		return orderStatusDaoImpl.getAll();
	}

	@Override
	public void addOrderStatus(OrderStatus orderStatus) {
		orderStatusDaoImpl.create(orderStatus);
	}

	@Override
	public void updateOrderStatus(OrderStatus orderStatus) {
		orderStatusDaoImpl.update(orderStatus);
	}

	@Override
	public void deleteOrderStatus(OrderStatus orderStatus) {
		orderStatusDaoImpl.delete(orderStatus);
	}

}
