package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.CarCustomer;
import com.nixsolutions.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarDAO carDaoImpl;

	public CarServiceImpl() {

	}

	@Override
	public List<Car> getAllCars() {
		return carDaoImpl.getAll();
	}

	@Override
	public List<CarCustomer> getAllCarCustomers() {
		List<CarCustomer> carCustomers = new ArrayList<>();
		List<Car> cars = new ArrayList<>();
		cars.addAll(carDaoImpl.getAll());
		for (Car car : cars) {
			CarCustomer carCustomer = new CarCustomer();
			carCustomer.setModel(car.getModel());
			carCustomer.setVin(car.getVin());
			carCustomer.setDescription(car.getDescription());
			carCustomer.setCustomer_id(car.getCustomer().getCustomerId());
			carCustomer.setF_name(car.getCustomer().getF_name());
			carCustomer.setL_name(car.getCustomer().getL_name());
			carCustomer.setId(car.getCarId());
			carCustomers.add(carCustomer);
		}
		return carCustomers;
	}

	@Override
	public Car getCarById(long id) {
		return carDaoImpl.findByPK(id);
	}

	@Override
	public CarCustomer getCarCustomer(long id) {
		CarCustomer carCustomer = null;
		Car car = carDaoImpl.findByPK(id);
		if (car != null) {
			carCustomer = new CarCustomer();
			carCustomer.setModel(car.getModel());
			carCustomer.setVin(car.getVin());
			carCustomer.setDescription(car.getDescription());
			carCustomer.setCustomer_id(car.getCustomer().getCustomerId());
			carCustomer.setF_name(car.getCustomer().getF_name());
			carCustomer.setL_name(car.getCustomer().getL_name());
			carCustomer.setId(car.getCarId());
		}
		return carCustomer;
	}

	@Override
	public void addCar(Car car) {
		carDaoImpl.create(car);

	}

	@Override
	public void updateCar(Car car) {
		carDaoImpl.update(car);
	}

	@Override
	public void deleteCar(Car car) {
		carDaoImpl.delete(car);

	}

}
