package com.nixsolutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.hibernate.entity.OrderPart;

@Service
public class OrderPartService {
	
	@Autowired
	private OrderPartDAO orderPartDao;
	
	public List<OrderPart> getOrderPartsByOrderId(long orderId) {
		return orderPartDao.getByOrderId(orderId);
	}
	
	public void addOrderPart(OrderPart orderPart) {
		orderPartDao.createFrom(orderPart);
	}
	
	public void updateOrderPart(OrderPart orderPart) {
		orderPartDao.update(orderPart);
	}
	
	public void deleteOrderPart(OrderPart orderPart) {
		orderPartDao.delete(orderPart);
	}
}
