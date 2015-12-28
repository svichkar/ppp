/**
 * 
 */
package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Car;

/**
 * @author mixeyes
 *
 */
public interface CarDao extends DBTables {
	public List<Car> getAllCar();

	/*
	 * get Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarByVINNumber(java.
	 * lang.String)
	 */
	public Car getCarByVINNumber(String vinNumber);

	/*
	 * create new sqllab.car for existing customer
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createNewCar(String model, String vinNumber, String description, String regNumber,
			Integer customerId);

	/*
	 * delete Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#deleteCarByVINNumber(
	 * java.lang.String, java.lang.String)
	 */
	public void deleteCarByVINNumber(String vinNumber);

	/**
	 * getCarByRegNumber
	 * 
	 * @param regNumber
	 * @return @
	 */
	public Car getCarByRegNumber(String regNumber);

	/**
	 * getCarByID
	 * 
	 * @param carId
	 * @return @
	 */
	public Car getCarByID(Integer carId);

	/**
	 * getCarsByCustomerName
	 * 
	 * @param lastName
	 * @param firstName
	 * @return @
	 */
	public List<Car> getCarsByCustomerName(String lastName, String firstName);

	/**
	 * for user who have one sqllab.car
	 */
	public void updateCarByVinNumber(String model, String carDescription, String vinNumber, String regNumber,
			Integer customerId);

}
