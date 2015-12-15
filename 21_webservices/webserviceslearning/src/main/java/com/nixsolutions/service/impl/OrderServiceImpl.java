package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.bean.OrderBean;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderInWorkDAO orderDao;

	public List<OrderInWork> getAllOrders() {
		return orderDao.getAll();
	}

	public List<OrderBean> getAllOrdersAsBeans() {
		return processAsBeans(getAllOrders());
	}

	public OrderInWork getOrderById(long id) {
		return orderDao.getByPK(id);
	}

	public List<OrderInWork> getOrdersByUserId(int userId) {
		return orderDao.getOrdersByUserId(userId);
	}

	public List<OrderBean> getOrdersAsBeansByUserId(int userId) {
		return processAsBeans(getOrdersByUserId(userId));
	}

	public List<OrderInWork> getOrdersByCustomerId(int customerId) {
		return orderDao.getOrdersByCustomerId(customerId);
	}

	public List<OrderInWork> getOrdersByCarId(int carId) {
		return orderDao.getOrdersByCarId(carId);
	}

	public void addOrder(OrderInWork order) {
		orderDao.createFrom(order);
	}

	public void updateOrder(OrderInWork order) {
		orderDao.update(order);
	}

	public void deleteOrder(OrderInWork order) {
		orderDao.delete(order);
	}

	private List<OrderBean> processAsBeans(List<OrderInWork> orderList) {
		List<OrderBean> resultList = new ArrayList<>();
		for (OrderInWork item : orderList) {
			OrderBean ob = new OrderBean();
			ob.setOrderId(item.getOrderId());
			ob.setOrderStatus(item.getOrderStatus().getOrderStatusName());
			ob.setDescription(item.getDescription());
			Car c = item.getCar();
			ob.setCarVin(c.getVin());
			ob.setCarModel(c.getModel());
			ob.setCarDescription(c.getDescription());
			ob.setTimestampStart(item.getTimestampStart());
			ob.setTimestampFinish(item.getTimestampFinish());
			resultList.add(ob);
		}
		return resultList;
	}
}
