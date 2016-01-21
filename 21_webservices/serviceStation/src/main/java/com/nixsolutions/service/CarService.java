package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.service.rest.entity.Cars;

public interface CarService {

	/**
	 * @return cars collection
	 */
	List<Car> getAllCar();
	
	/**
	 * @return cars collection
	 */
	Cars getAllCarRest();

	/**
	 * @param vinNumber
	 * @return car object with vinNumber
	 */
	Car getCarByVINNumber(String vinNumber);

	/**
	 * create new car
	 * 
	 * @param car
	 */
	void createNewCar(Car car);

	/**
	 * delete car with vin number
	 * 
	 * @param vinNumber
	 * @return is Deleted
	 */
	boolean deleteCarByVINNumber(String vinNumber);

	/**
	 * delete car with vin number
	 * 
	 * @param vinNumber
	 * @return is Deleted
	 */
	boolean deleteCar(Car car);

	/**
	 * @param regNumber
	 * @return car object with reg number
	 */
	Car getCarByRegNumber(String regNumber);

	/**
	 * @param carId
	 * @return car object by ID
	 */
	Car getCarByID(Integer carId);

	/**
	 * @param car_id
	 * @return car object by ID
	 */
	Car getCarByID(String carId);

	/**
	 * @param last_name
	 * @param first_name
	 * @return collection of the car
	 */
	List<Car> getCarsByCustomerName(String lastName, String firstName);

	/**
	 * update car object By id
	 * 
	 * @param car
	 */
	void updateCarByID(Car car);

	void createNewCar(String carModel, String regNumber, String vinNumber, Customer customerByID);

	void updateCar(String carModel, String regNumber, String vinNumber, String carDescription, Customer customerByID);

}
