/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.CarDao;
import com.nixsolutions.entity.Car;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class CarDaoImpl implements CarDao {

	private final static Logger logger = LogManager.getLogger();

	/**
	 * get All Car from db
	 * 
	 * @return list of the car
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see com.nixsolutions.dao.CarDao#getAllCar()
	 */
	@Override
	public List<Car> getAllCar() throws SQLException {
		Connection connection = null;
		List<Car> cars = new ArrayList<Car>();
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"SELECT * FROM sqllab.car \"");
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.car;");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				cars.add(new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id")));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
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
	@Override
	public Car getCarByVINNumber(String vinNumber) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"SELECT * FROM sqllab.car WHERE vin_number=?\"");

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.car WHERE vin_number=?;");
			stmt.setString(1, vinNumber);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				return new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id"));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
		return null;
	}

	/*
	 * get Car By reg-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarByVINNumber(java.
	 * lang.String)
	 */
	@Override
	public Car getCarByRegNumber(String regNumber) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"SELECT * FROM sqllab.car WHERE vin_number=?\"");

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.car WHERE reg_number=?;");
			stmt.setString(1, regNumber);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				return new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id"));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
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
	@Override
	public Car getCarByID(Integer car_id) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"SELECT * FROM sqllab.car WHERE vin_number=?\"");

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.car WHERE car_id=?;");
			stmt.setInt(1, car_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				return new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id"));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
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
	@Override
	public List<Car> getCarsByCustomerName(String last_name, String first_name) throws SQLException {

		String query = "SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id FROM sqllab.customer WHERE  last_name =? AND first_name=?);";
		List<Car> cars = new ArrayList<Car>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace(
					"Send query \"SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id FROM sqllab.customer WHERE first_name ='Alex' AND last_name ='Alkov');\"");
			// SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id
			// FROM
			// sqllab.customer WHERE first_name ='Alex' AND last_name ='Alkov');
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				cars.add(new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id")));
			}
			stmt.close();
			return cars;
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
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
	@Override
	public void createNewCar(String model, String vin_number, String description, String reg_number,
			Integer customer_id) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace(
					"Send query \"INSERT INTO sqllab.car (car_model  ,vin_number, customer_id )VALUES('AUDI','1234567890qwertyu',1);\"");
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO sqllab.car (car_model  ,vin_number, car_description,reg_number, customer_id )"
							+ "VALUES(?,?,?,?,?);");

			stmt.setString(1, model);
			stmt.setString(2, vin_number);
			stmt.setString(3, description);
			stmt.setString(4, reg_number);
			stmt.setInt(5, customer_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.car was created");
			else
				logger.debug("New sqllab.car was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * delete Car By VIN-Number
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#deleteCarByVINNumber(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteCarByVINNumber(String vinNumber) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"DELETE FROM sqllab.car WHERE vin_number=?\"");

			PreparedStatement stmt = connection.prepareStatement("DELETE FROM sqllab.car WHERE vin_number=?;");
			stmt.setString(1, vinNumber);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("car was deleted");
			else
				logger.debug("car was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.CarDao#updateCarByVinNumber(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void updateCarByVinNumber(String model, String car_description, String vin_number, String reg_number,
			Integer customer_id) throws SQLException {
		String query = // "UPDATE sqllab.car SET model=? ,vin_number=?,
						// description =? WHERE CUSTOMER_ID =(SELECT customer_id
						// FROM sqllab.customer WHERE last_name =? AND
						// first_name =?);";
		"UPDATE sqllab.car SET car_model=?, car_description =?, customer_id =?, reg_number=? where vin_number =?;";
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"" + query + ";\"");
			// SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id
			// FROM
			// sqllab.customer WHERE first_name ='Alex' AND last_name ='Alkov');
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, model);
			stmt.setString(2, car_description);
			stmt.setInt(3, customer_id);
			stmt.setString(4, reg_number);
			stmt.setString(5, vin_number);

			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.car was updated");
			else
				logger.debug("New sqllab.car was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace(
					"Send query \"CREATE TABLE sqllab.car (  car_id INT IDENTITY, model VARCHAR(128) NOT NULL, vin_number VARCHAR(17) NOT NULL UNIQUE, description VARCHAR(256));\"");

			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			stmt.execute("CREATE TABLE sqllab.car( " + "car_id INT IDENTITY, " + "car_model VARCHAR(128) NOT NULL, "
					+ "vin_number VARCHAR(17) NOT NULL UNIQUE, " + "reg_number VARCHAR(17) NOT NULL UNIQUE, "
					+ "car_description VARCHAR(256));");
			stmt.execute("ALTER TABLE sqllab.car " + "ADD COLUMN customer_id INT NOT NULL;");
			stmt.execute("ALTER TABLE sqllab.car "
					+ "ADD FOREIGN KEY(customer_id ) REFERENCES sqllab.customer (customer_id );");
			connection.commit();
			logger.trace("Table sqllab.car was created");
			stmt.close();
			connection.setAutoCommit(true);
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.DBTables#deleteTableWithAllData()
	 */
	@Override
	public void deleteTableWithAllData() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"DROP TABLE sqllab.car ;\"");

			PreparedStatement stmt = connection.prepareStatement("DROP TABLE sqllab.car ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.car was deleted");
			else
				logger.debug("table sqllab.car was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

}
