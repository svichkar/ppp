package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.CarCustomer;

public interface CarService {

	public List<Car> getAllCars();

	public List<CarCustomer> getAllCarCustomers();

	public Car getCarById(long id);

	public CarCustomer getCarCustomer(long id);

	public void addCar(Car car);

	public void updateCar(Car car);

	public void deleteCar(Car car);

}
