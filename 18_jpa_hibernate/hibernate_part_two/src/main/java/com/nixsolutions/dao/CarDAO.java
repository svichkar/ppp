package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.Car;

public interface CarDAO extends GenericDAO<Car> {
	
	List<Car> getCarsByCustomerId(int customerId);
}
