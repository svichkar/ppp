/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.CarDao;
import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Car;

/**
 * @author mixeyes
 *
 */
public class CarDaoImpl implements CarDao {

	private final static Logger logger = LogManager.getLogger(CarDaoImpl.class);
	private Connection dbConn;
	
	public CarDaoImpl(Connection connection) {
		this.dbConn = connection;
	}

	
	/*
	 * get All Car from db
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CarDao#getAllCar()
	 */
	public List<Car> getAllCar() {
		List<Car> cars = new ArrayList<Car>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM car\"");
			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM car");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the car objects");
			while (set.next()) {
				cars.add(new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("car_description"), set.getInt("customer_id")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return cars;
	}

	/*
	 * get Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarByVINNumber(java.
	 * lang.String)
	 */
	public Car getCarByVINNumber(String vinNumber) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM car WHERE vin_number='?'\"");

			PreparedStatement stmt = dbConn
					.prepareStatement("SELECT * FROM car WHERE vin_number='?';");
			stmt.setString(1, vinNumber);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the car objects");
			while (set.next()) {
				return new Car(set.getInt("car_id"), set.getString("model"), set.getString("vin_number"),
						set.getString("description"), set.getInt("customer_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/*
	 * get car by customer full name or customer last name
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarsByCustomerName(
	 * java.lang.String)
	 */
	public List<Car> getCarsByCustomerName(String customerName) {
		String[] name = customerName.split(" ");
		String query;
		if (name.length == 2)
			query = "SELECT * FROM car WHERE customer_id =(SELECT customer_id FROM customer WHERE  last_name ='?' AND first_name='?');";
		else
			query = "SELECT * FROM car WHERE customer_id =(SELECT customer_id FROM customer WHERE last_name ='?');";
		List<Car> cars = new ArrayList<Car>();
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"SELECT * FROM car WHERE customer_id =(SELECT customer_id FROM customer WHERE first_name ='Alex' AND last_name ='Alkov');\"");
			// SELECT * FROM car WHERE customer_id =(SELECT customer_id FROM
			// customer WHERE first_name ='Alex' AND last_name ='Alkov');
			PreparedStatement stmt = dbConn.prepareStatement(query);
			stmt.setString(1, name[0]);
			if (name.length == 2)
				stmt.setString(2, name[1]);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the car objects");
			while (set.next()) {
				cars.add(new Car(set.getInt("car_id"), set.getString("model"), set.getString("vin_number"),
						set.getString("description"), set.getInt("customer_id")));
			}
			stmt.close();
			return cars;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/*
	 * create new car for existing customer
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createNewCar(String model, String vin_number, String description, String proprietor) {
		String[] name = proprietor.split(" ");
		String query;
		if (name.length == 2)
			query = "INSERT INTO car (model  ,vin_number, description, customer_id )VALUES('?','?', '?',("
					+ "SELECT customer_id FROM customer WHERE  last_name ='?' AND first_name='?'));";
		else
			query = "INSERT INTO car (model  ,vin_number, description, customer_id )"
					+ "VALUES('?','?', '?',("
					+ "SELECT customer_id FROM customer WHERE  last_name ='?'));";
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"INSERT INTO car (model  ,vin_number, customer_id )VALUES('AUDI','1234567890qwertyu',1);\"");

			PreparedStatement stmt = dbConn.prepareStatement(query);
			stmt.setString(1, model);
			stmt.setString(2, vin_number);
			stmt.setString(3, description);
			stmt.setString(4, name[0]);
			if (name.length == 2)
				stmt.setString(5, name[1]);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New car was created");
			else
				logger.debug("New car was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/*
	 * delete Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#deleteCarByVINNumber(
	 * java.lang.String, java.lang.String)
	 */
	public void deleteCarByVINNumber(String vinNumber) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DELETE FROM car WHERE vin_number=?\"");

			PreparedStatement stmt = dbConn.prepareStatement("DELETE FROM car WHERE vin_number='?';");
			stmt.setString(1, vinNumber);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("car was deleted");
			else
				logger.debug("car was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updateCarByCustomerName(String model, String vinNumber, String description, String customerName) {
		String[] name = customerName.split(" ");
		String query;
		if (name.length == 2)
			query = "UPDATE car SET model=?  ,vin_number=?, description =? WHERE CUSTOMER_ID =(SELECT customer_id FROM customer WHERE last_name ='?' AND first_name ='?');";
		else
			query = "UPDATE car SET model=?  ,vin_number=?, description =? WHERE CUSTOMER_ID =(SELECT customer_id FROM customer WHERE last_name ='?');";
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"SELECT * FROM car WHERE customer_id =(SELECT customer_id FROM customer WHERE first_name ='Alex' AND last_name ='Alkov');\"");
			// SELECT * FROM car WHERE customer_id =(SELECT customer_id FROM
			// customer WHERE first_name ='Alex' AND last_name ='Alkov');
			PreparedStatement stmt = dbConn.prepareStatement(query);
			stmt.setString(1, model);
			stmt.setString(2, vinNumber);
			stmt.setString(3, description);
			stmt.setString(4, name[0]);
			if (name.length == 2)
				stmt.setString(5, name[1]);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New car was created");
			else
				logger.debug("New car was not created");
			logger.trace("Generate list of the car objects");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"CREATE TABLE car( car_id INT IDENTITY, model VARCHAR(128) NOT NULL, vin_number VARCHAR(17) NOT NULL UNIQUE, description VARCHAR(256));\"");

			PreparedStatement stmt = dbConn.prepareStatement(
					"CREATE TABLE car( car_id INT IDENTITY, model VARCHAR(128) NOT NULL, vin_number VARCHAR(17) NOT NULL UNIQUE, description VARCHAR(256));");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table car was created");
			else
				logger.debug("Table car was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DROP TABLE car ;\"");

			PreparedStatement stmt = dbConn.prepareStatement("DROP TABLE car ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table car was deleted");
			else
				logger.debug("table car was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
