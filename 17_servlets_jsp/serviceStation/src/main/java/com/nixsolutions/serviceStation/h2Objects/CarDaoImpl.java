/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.CarDao;

import com.nixsolutions.serviceStation.dbObjects.Car;

/**
 * @author mixeyes
 *
 */
public class CarDaoImpl implements CarDao {

	private final static Logger logger = LogManager.getLogger();
	private Connection dbConn;

	public CarDaoImpl(Connection connection) {
		this.dbConn = connection;
	}

	/**
	 * get All Car from db
	 * 
	 * @return list of the car
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CarDao#getAllCar()
	 */
	public List<Car> getAllCar() {
		List<Car> cars = new ArrayList<Car>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM sqllab.car \"");
			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM sqllab.car ");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.car objects");
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
			logger.trace("Send query \"SELECT * FROM sqllab.car WHERE vin_number=?\"");

			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM sqllab.car WHERE vin_number=?;");
			stmt.setString(1, vinNumber);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				return new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("car_description"), set.getInt("customer_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/*
	 * get Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarByVINNumber(java.
	 * lang.String)
	 */
	public Car getCarByID(Integer car_id) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM sqllab.car WHERE vin_number=?\"");

			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM sqllab.car WHERE car_id=?;");
			stmt.setInt(1, car_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				return new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("car_description"), set.getInt("customer_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/*
	 * get sqllab.car by sqllab.customer full name or sqllab.customer last name
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarsByCustomerName(
	 * java.lang.String)
	 */
	public List<Car> getCarsByCustomerName(String last_name, String first_name) {

		String query = "SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id FROM sqllab.customer WHERE  last_name =? AND first_name=?);";
		List<Car> cars = new ArrayList<Car>();
		try {
			logger.trace(
					"Send query \"SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id FROM sqllab.customer WHERE first_name ='Alex' AND last_name ='Alkov');\"");
			// SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id
			// FROM
			// sqllab.customer WHERE first_name ='Alex' AND last_name ='Alkov');
			PreparedStatement stmt = dbConn.prepareStatement(query);
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				cars.add(new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("car_description"), set.getInt("customer_id")));
			}
			stmt.close();
			return cars;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/*
	 * create new sqllab.car for existing customer
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createNewCar(String model, String vin_number, String description, Integer customer_id) {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"INSERT INTO sqllab.car (car_model  ,vin_number, customer_id )VALUES('AUDI','1234567890qwertyu',1);\"");
			PreparedStatement stmt = dbConn
					.prepareStatement("INSERT INTO sqllab.car (car_model  ,vin_number, car_description, customer_id )"
							+ "VALUES(?,?, ?,?);");

			stmt.setString(1, model);
			stmt.setString(2, vin_number);
			stmt.setString(3, description);
			stmt.setInt(4, customer_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.car was created");
			else
				logger.debug("New sqllab.car was not created");
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
			logger.trace("Send query \"DELETE FROM sqllab.car WHERE vin_number=?\"");

			PreparedStatement stmt = dbConn.prepareStatement("DELETE FROM sqllab.car WHERE vin_number=?;");
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

	public void updateCarByVinNumber(String model, String car_description, String vin_number, Integer customer_id) {
		String query = // "UPDATE sqllab.car SET model=? ,vin_number=?,
						// description =? WHERE CUSTOMER_ID =(SELECT customer_id
						// FROM sqllab.customer WHERE last_name =? AND
						// first_name =?);";
		"UPDATE sqllab.car SET car_model=?, car_description =?, customer_id =? where vin_number =?;";
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"" + query + ";\"");
			// SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id
			// FROM
			// sqllab.customer WHERE first_name ='Alex' AND last_name ='Alkov');
			PreparedStatement stmt = dbConn.prepareStatement(query);
			stmt.setString(1, model);
			stmt.setString(2, car_description);
			stmt.setInt(3, customer_id);
			stmt.setString(4, vin_number);

			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.car was updated");
			else
				logger.debug("New sqllab.car was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"CREATE TABLE sqllab.car (  car_id INT IDENTITY, model VARCHAR(128) NOT NULL, vin_number VARCHAR(17) NOT NULL UNIQUE, description VARCHAR(256));\"");

			dbConn.setAutoCommit(false);
			Statement stmt = dbConn.createStatement();
			stmt.execute("CREATE TABLE sqllab.car( " + "car_id INT IDENTITY, " + "car_model VARCHAR(128) NOT NULL, "
					+ "vin_number VARCHAR(17) NOT NULL UNIQUE, " + "car_description VARCHAR(256));");
			stmt.execute("ALTER TABLE sqllab.car " + "ADD COLUMN customer_id INT NOT NULL;");
			stmt.execute("ALTER TABLE sqllab.car "
					+ "ADD FOREIGN KEY(customer_id ) REFERENCES sqllab.customer (customer_id );");
			dbConn.commit();
			logger.trace("Table sqllab.car was created");
			stmt.close();
			dbConn.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DROP TABLE sqllab.car ;\"");

			PreparedStatement stmt = dbConn.prepareStatement("DROP TABLE sqllab.car ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.car was deleted");
			else
				logger.debug("table sqllab.car was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
