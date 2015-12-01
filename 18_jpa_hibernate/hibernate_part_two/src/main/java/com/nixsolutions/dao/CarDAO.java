package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;

public interface CarDAO extends GenericDAO<Car> {
	
	public List<Car> getCarsByCustomer(Customer customer);
}
