package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CarDao;
import com.nixsolutions.entity.Car;
import com.nixsolutions.service.CarService;

@Service
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
	public Car getCarByID(Integer car_id) {
		return carDao.getCarByID(car_id);
	}

	@Override
	public Car getCarByID(String car_id) {
		return getCarByID(Integer.decode(car_id));
	}

	@Override
	public List<Car> getCarsByCustomerName(String last_name, String first_name) {
		return carDao.getCarsByCustomerName(last_name, first_name);
	}

	@Override
	public void updateCarByID(Car car) {
		carDao.updateCarByID(car);
	}

	@Override
	public boolean deleteCar(Car car) {
		carDao.deleteCar(car);
		if (carDao.getCarByID(car.getCar_id()) == null)
			return true;
		else
			return false;
	}

}
