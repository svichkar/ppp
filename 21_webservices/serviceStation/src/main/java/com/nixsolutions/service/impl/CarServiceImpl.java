package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CarDao;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.rest.entity.Cars;

//@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carDao;

	@Override
	public List<Car> getAllCar() {
		return carDao.getAllCar();
	}

	@Override
	public Car getCarByVINNumber(String vinNumber) {
		return carDao.getCarByVINNumber(vinNumber);
	}

	@Override
	public void createNewCar(Car car) {
		carDao.createNewCar(car);
	}

	@Override
	public boolean deleteCarByVINNumber(String vinNumber) {
		return carDao.deleteCarByVINNumber(vinNumber);
	}

	@Override
	public Car getCarByRegNumber(String regNumber) {
		return carDao.getCarByRegNumber(regNumber);
	}

	@Override
	public Car getCarByID(Integer carId) {
		return carDao.getCarByID(carId);
	}

	@Override
	public Car getCarByID(String carId) {
		return getCarByID(Integer.decode(carId));
	}

	@Override
	public List<Car> getCarsByCustomerName(String lastName, String firstName) {
		return carDao.getCarsByCustomerName(lastName, firstName);
	}

	@Override
	public void updateCarByID(Car car) {
		carDao.updateCarByID(car);
	}

	@Override
	public boolean deleteCar(Car car) {
		carDao.deleteCar(car);
		return carDao.getCarByID(car.getCarId()) == null;
	}

	@Override
	public void createNewCar(String carModel, String regNumber, String vinNumber, Customer customerByID) {
		Car car = new Car();
		car.setCarModel(carModel);
		car.setCustomer(customerByID);
		car.setRegNumber(regNumber);
		car.setVinNumber(vinNumber);
		createNewCar(car);
	}

	@Override
	public void updateCar(String carModel, String regNumber, String vinNumber, String carDescription,
			Customer customerByID) {
		Car car = getCarByRegNumber(regNumber);
		car.setCarModel(carModel);
		car.setCustomer(customerByID);
		car.setRegNumber(regNumber);
		car.setVinNumber(vinNumber);
		car.setCarDescription(carDescription);
		updateCarByID(car);

	}

	@Override
	public Cars getAllCarRest() {
		// use only for rest services
		return null;
	}

}
