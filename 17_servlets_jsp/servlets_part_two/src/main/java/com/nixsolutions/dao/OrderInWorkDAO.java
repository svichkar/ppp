package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;

public interface OrderInWorkDAO extends GenericDAO<OrderInWork> {
	
	public List<OrderInWork> getOrdersByUser(User user);
	
	public List<OrderInWork> getOrdersByCar(Car car);
	
	public List<OrderInWork> getOrdersByPart(Part part);
	
	public List<OrderInWork> getOrdersByWorker(Worker worker);
	
	public List<OrderInWork> getOrdersByCustomer(Customer customer);
}
