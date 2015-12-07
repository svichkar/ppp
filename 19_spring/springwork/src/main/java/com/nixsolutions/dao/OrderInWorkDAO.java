package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.User;

public interface OrderInWorkDAO extends GenericDAO<OrderInWork> {
	
	public List<OrderInWork> getOrdersByUser(User user);
	
	public List<OrderInWork> getOrdersByCar(Car car);
	
	public List<OrderInWork> getOrdersByCustomer(Customer customer);
	
	public OrderInWork getByPK(long id);
}
