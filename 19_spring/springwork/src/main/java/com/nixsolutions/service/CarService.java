package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.bean.CarBean;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;

public interface CarService {

	List<Car> getAllCars();

	List<CarBean> getAllCarsAsBeans();

	List<Car> getCarsByCustomer(Customer customer);

	Car getCarById(int carId);

	void addCar(Car car);

	void updateCar(Car car);

	void deleteCar(Car car);
}
