package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.dto.CarCustomer;
import com.nixsolutions.entities.Car;

public interface CarService {

	List<Car> getAllCars();

	List<CarCustomer> getAllCarCustomers();

	Car getCarById(long id);
	
	Car getCarByVin(String vin);

	CarCustomer getCarCustomer(long id);

	void addCar(Car car);

	void updateCar(Car car);

	void deleteCar(Car car);

}
