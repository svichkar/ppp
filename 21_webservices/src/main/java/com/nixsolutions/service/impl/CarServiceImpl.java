package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dto.CarCustomer;
import com.nixsolutions.entities.Car;
import com.nixsolutions.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarDAO carDaoImpl;

	public List<Car> getAllCars() {
		return carDaoImpl.getAll();
	}

	public List<CarCustomer> getAllCarCustomers() {
		List<CarCustomer> carCustomers = new ArrayList<>();
		List<Car> cars = new ArrayList<>();
		cars.addAll(carDaoImpl.getAll());
		for (Car car : cars) {
			CarCustomer carCustomer = new CarCustomer();
			carCustomer.setModel(car.getModel());
			carCustomer.setVin(car.getVin());
			carCustomer.setDescription(car.getDescription());
			carCustomer.setCustomerId(car.getCustomer().getCustomerId());
			carCustomer.setFname(car.getCustomer().getFname());
			carCustomer.setLname(car.getCustomer().getLname());
			carCustomer.setId(car.getCarId());
			carCustomers.add(carCustomer);
		}
		return carCustomers;
	}

	public Car getCarById(long id) {
		return carDaoImpl.findByPK(id);
	}

	public CarCustomer getCarCustomer(long id) {
		CarCustomer carCustomer = null;
		Car car = carDaoImpl.findByPK(id);
		if (car != null) {
			carCustomer = new CarCustomer();
			carCustomer.setModel(car.getModel());
			carCustomer.setVin(car.getVin());
			carCustomer.setDescription(car.getDescription());
			carCustomer.setCustomerId(car.getCustomer().getCustomerId());
			carCustomer.setFname(car.getCustomer().getFname());
			carCustomer.setLname(car.getCustomer().getLname());
			carCustomer.setId(car.getCarId());
		}
		return carCustomer;
	}

	public void addCar(Car car) {
		carDaoImpl.create(car);

	}

	public void updateCar(Car car) {
		carDaoImpl.update(car);
	}

	public void deleteCar(Car car) {
		carDaoImpl.delete(car);
	}

	@Override
	public Car getCarByVin(String vin) {
		Car car = carDaoImpl.findByVin(vin);
		return car;
	}
}
