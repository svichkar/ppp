package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.entity.Worker;

public interface OrderInWorkDAO extends GenericDAO<OrderInWork> {
	
	public List<OrderInWork> getOrdersByUser(User user);
	
	public List<OrderInWork> getOrdersByCar(Car car);
	
	public List<OrderInWork> getOrdersByPart(Part part);
	
	public List<OrderInWork> getOrdersByWorker(Worker worker);
	
	public List<OrderInWork> getOrdersByCustomer(Customer customer);
}
