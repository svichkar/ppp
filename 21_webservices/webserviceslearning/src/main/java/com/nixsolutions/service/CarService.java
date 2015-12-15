package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.bean.CarBean;
import com.nixsolutions.hibernate.entity.Car;

public interface CarService {

	List<Car> getAllCars();

	List<CarBean> getAllCarsAsBeans();

	List<Car> getCarsByCustomerId(int customerId);

	Car getCarById(int carId);

	void addCar(Car car);

	void updateCar(Car car);

	void deleteCar(Car car);
}
