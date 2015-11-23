package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;

public interface CarDAO extends GenericDAO<Car> {
	
	public List<Car> getCarsByCustomer(Customer customer);
}
