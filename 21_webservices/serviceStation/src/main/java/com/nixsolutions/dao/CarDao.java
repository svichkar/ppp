/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.Car;

/**
 * @author mixeyes
 *
 */
public interface CarDao {
	List<Car> getAllCar();

	/*
	 * get Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarByVINNumber(java.
	 * lang.String)
	 */
	Car getCarByVINNumber(String vinNumber);

	/*
	 * create new sqllab.car for existing customer
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	void createNewCar(Car car);

	/*
	 * delete Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#deleteCarByVINNumber(
	 * java.lang.String, java.lang.String)
	 */
	boolean deleteCarByVINNumber(String vinNumber);

	/*
	 * delete Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#deleteCarByVINNumber(
	 * java.lang.String, java.lang.String)
	 */
	void deleteCar(Car car);

	/**
	 * getCarByRegNumber
	 * 
	 * @param regNumber
	 * @return
	 * @throws SQLException
	 */
	Car getCarByRegNumber(String regNumber);

	/**
	 * getCarByID
	 * 
	 * @param car_id
	 * @return
	 * @throws SQLException
	 */
	Car getCarByID(Integer car_id);

	/**
	 * getCarsByCustomerName
	 * 
	 * @param last_name
	 * @param first_name
	 * @return
	 * @throws SQLException
	 */
	List<Car> getCarsByCustomerName(String last_name, String first_name);

	void updateCarByID(Car car);

}
