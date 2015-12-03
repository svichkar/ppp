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
public interface CarDao extends DBTables {
	public List<Car> getAllCar();

	/*
	 * get Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarByVINNumber(java.
	 * lang.String)
	 */
	public Car getCarByVINNumber(String vinNumber) ;

	/*
	 * create new sqllab.car for existing customer
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createNewCar(String model, String vin_number, String description, String reg_number,
			Integer customer_id) ;

	/*
	 * delete Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#deleteCarByVINNumber(
	 * java.lang.String, java.lang.String)
	 */
	public boolean deleteCarByVINNumber(String vinNumber) ;

	/**
	 * getCarByRegNumber
	 * 
	 * @param regNumber
	 * @return
	 * @throws SQLException
	 */
	public Car getCarByRegNumber(String regNumber);

	/**
	 * getCarByID
	 * 
	 * @param car_id
	 * @return
	 * @throws SQLException
	 */
	public Car getCarByID(Integer car_id) ;

	/**
	 * getCarsByCustomerName
	 * 
	 * @param last_name
	 * @param first_name
	 * @return
	 * @throws SQLException
	 */
	public List<Car> getCarsByCustomerName(String last_name, String first_name);

	/**
	 * for user who have one sqllab.car
	 */
	public void updateCarByVinNumber(String model, String car_description, String vin_number, String reg_number,
			Integer customer_id);

	public void updateCarByID(String model, String car_description, String vin_number, String reg_number, Integer customer_id,
			Integer car_id);

}
