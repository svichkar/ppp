package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Car;

public interface CarService {

	/**
	 * @return cars collection
	 */
	public List<Car> getAllCar();

	/**
	 * @param vinNumber
	 * @return car object with vinNumber
	 */
	public Car getCarByVINNumber(String vinNumber);

	/**
	 * create new car
	 * @param car
	 */
	public void createNewCar(Car car);

	/**
	 * delete car with vin number
	 * @param vinNumber
	 * @return is Deleted
	 */
	public boolean deleteCarByVINNumber(String vinNumber);

	/**
	 * delete car with vin number
	 * @param vinNumber
	 * @return is Deleted
	 */
	public boolean deleteCar(Car car);

	/**
	 * @param regNumber
	 * @return car object with reg number
	 */
	public Car getCarByRegNumber(String regNumber);

	/**
	 * @param car_id
	 * @return car object by ID
	 */
	public Car getCarByID(Integer car_id);

	/**
	 * @param car_id
	 * @return car object by ID
	 */
	public Car getCarByID(String car_id);

	/**
	 * @param last_name
	 * @param first_name
	 * @return collection of the car 
	 */
	public List<Car> getCarsByCustomerName(String last_name, String first_name);

	/**
	 * update car object By id
	 * @param car
	 */
	public void updateCarByID(Car car);

}
