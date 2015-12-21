package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.hibernate.entity.OrderStatus;
import com.nixsolutions.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

	@Autowired
	private OrderStatusDAO orderStatusDao;

	public List<OrderStatus> getAllOrderStatuses() {
		return orderStatusDao.getAll();
	}

	public OrderStatus getOrderStatusById(int id) {
		return orderStatusDao.getByPK(id);
	}
}
