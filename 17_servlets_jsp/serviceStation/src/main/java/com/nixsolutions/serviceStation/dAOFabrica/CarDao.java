/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Car;

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
	 * get sqllab.car by sqllab.customer full name or sqllab.customer last name
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarsByCustomerName(
	 * java.lang.String)
	 */
	public List<Car> getCarsByCustomerName(String last_name, String first_name);

	/**
	 * for user who have one sqllab.car
	 */
	public void updateCarByVinNumber(String model, String car_description, String vin_number, Integer customer_id);

	/*
	 * create new sqllab.car for existing customer
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createNewCar(String model, String vin_number, String description, Integer customer_id);

	/*
	 * delete Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#deleteCarByVINNumber(
	 * java.lang.String, java.lang.String)
	 */
	public void deleteCarByVINNumber(String vinNumber);
	
	/**get car by ID*/
	public Car getCarByID(Integer car_id);

}
