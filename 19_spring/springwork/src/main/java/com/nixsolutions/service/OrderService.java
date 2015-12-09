package com.nixsolutions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.bean.OrderBean;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.User;

@Service
public class OrderService {

	@Autowired
	private OrderInWorkDAO orderDao;

	public List<OrderInWork> getAllOrders() {
		return orderDao.getAll();
	}

	public List<OrderBean> getAllOrdersAsBeans() {
		return processAsBeans(getAllOrders());
	}
	
	public List<OrderInWork> getAllOrdersByUser(User user) {
		return orderDao.getOrdersByUser(user);
	}
	
	public List<OrderBean> getAllOrdersAsBeansByUser(User user) {
		return processAsBeans(getAllOrdersByUser(user));
	}
	
	public List<OrderInWork> getOrdersByCustomer(Customer customer) {
		return orderDao.getOrdersByCustomer(customer);
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
