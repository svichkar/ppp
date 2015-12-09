package com.nixsolutions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.bean.CarBean;
import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;

@Service
public class CarService {

	@Autowired
	private CarDAO carDao;
	
	public List<Car> getAllCars() {
		return carDao.getAll();
	}
	
	public List<CarBean> getAllCarsAsBeans() {
		return processAsBeans(getAllCars());
	}
	
	public List<Car> getCarsByCustomer(Customer customer) {
		return carDao.getCarsByCustomer(customer);
	}
	
	public void addCar(Car car) {
		carDao.createFrom(car);
	}
	
	public void updateCar(Car car) {
		carDao.update(car);
	}
	
	public void deleteCar(Car car) {
		carDao.delete(car);
	}
	
	private List<CarBean> processAsBeans(List<Car> carList) {
		List<CarBean> resultList = new ArrayList<>();
		for (Car item : carList) {
			CarBean cb = new CarBean();
			cb.setCarId(item.getCarId());
			cb.setCarModel(item.getModel());
			cb.setCarVin(item.getVin());
			cb.setCarDescription(item.getDescription());
			Customer c = item.getCustomer();
			cb.setCustomerName(c.getFirstName() + " " + c.getLastName());
			resultList.add(cb);
		}
		return resultList;
	}
}
