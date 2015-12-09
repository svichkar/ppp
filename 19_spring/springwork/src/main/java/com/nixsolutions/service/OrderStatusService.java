package com.nixsolutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.hibernate.entity.OrderStatus;

@Service
public class OrderStatusService {

	@Autowired
	private OrderStatusDAO orderStatusDao;
	
	public List<OrderStatus> getAllOrderStatuses() {
		return orderStatusDao.getAll();
	}
	
	public OrderStatus getOrderStatusById(int id) {
		return orderStatusDao.getByPK(id);
	}
}
