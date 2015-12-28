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

	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public List<Car> getAllCar() {
		List<Car> carList = new ArrayList<Car>();
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"SELECT * FROM sqllab.car \"");
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.car;");
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				carList.add(new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return carList;
	}

	@Override
	public Car getCarByVINNumber(String vinNumber) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"SELECT * FROM sqllab.car WHERE vinNumber=?\"");

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.car WHERE vin_number=?;");
			stmt.setString(1, vinNumber);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				return new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Car getCarByRegNumber(String regNumber) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"SELECT * FROM sqllab.car WHERE vinNumber=?\"");

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.car WHERE reg_number=?;");
			stmt.setString(1, regNumber);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				return new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Car getCarByID(Integer carId) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"SELECT * FROM sqllab.car WHERE vinNumber=?\"");

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.car WHERE car_id=?;");
			stmt.setInt(1, carId);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				return new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<Car> getCarsByCustomerName(String lastName, String firstName) {
		String query = "SELECT * FROM sqllab.car WHERE customer_id =("
				+ "SELECT customer_id FROM sqllab.customer WHERE  last_name =? AND first_name=?);";
		List<Car> cars = new ArrayList<Car>();
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"" + query + "\"");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.car objects");
			while (set.next()) {
				cars.add(new Car(set.getInt("car_id"), set.getString("car_model"), set.getString("vin_number"),
						set.getString("reg_number"), set.getString("car_description"), set.getInt("customer_id")));
			}
			stmt.close();
			return cars;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void createNewCar(String model, String vinNumber, String description, String regNumber, Integer customerId) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace(
					"Send query \"INSERT INTO sqllab.car (car_model  ,vin_number, customerId )VALUES('AUDI','1234567890qwertyu',1);\"");
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO sqllab.car (car_model  ,vin_number, car_description,reg_number, customer_id )"
							+ "VALUES(?,?,?,?,?);");

			stmt.setString(1, model);
			stmt.setString(2, vinNumber);
			stmt.setString(3, description);
			stmt.setString(4, regNumber);
			stmt.setInt(5, customerId);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.car was created");
			else
				LOGGER.debug("New sqllab.car was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteCarByVINNumber(String vinNumber) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"DELETE FROM sqllab.car WHERE vinNumber=?\"");

			PreparedStatement stmt = connection.prepareStatement("DELETE FROM sqllab.car WHERE vinNumber=?;");
			stmt.setString(1, vinNumber);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("car was deleted");
			else
				LOGGER.debug("car was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void updateCarByVinNumber(String model, String carDescription, String vinNumber, String regNumber,
			Integer customerId) {
		String query = "UPDATE sqllab.car SET carModel=?, carDescription =?, customerId =?,"
				+ " regNumber=? where vinNumber =?;";
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"" + query + ";\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, model);
			stmt.setString(2, carDescription);
			stmt.setInt(3, customerId);
			stmt.setString(4, regNumber);
			stmt.setString(5, vinNumber);

			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.car was updated");
			else
				LOGGER.debug("New sqllab.car was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace(
					"Send query \"CREATE TABLE sqllab.car (  carId INT IDENTITY, model VARCHAR(128) NOT NULL, vinNumber VARCHAR(17) NOT NULL UNIQUE, description VARCHAR(256));\"");

			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			stmt.execute("CREATE TABLE sqllab.car( " + "carId INT IDENTITY, " + "carModel VARCHAR(128) NOT NULL, "
					+ "vinNumber VARCHAR(17) NOT NULL UNIQUE, " + "reg_number VARCHAR(17) NOT NULL UNIQUE, "
					+ "car_description VARCHAR(256));");
			stmt.execute("ALTER TABLE sqllab.car " + "ADD COLUMN customerId INT NOT NULL;");
			stmt.execute("ALTER TABLE sqllab.car "
					+ "ADD FOREIGN KEY(customerId ) REFERENCES sqllab.customer (customerId );");
			connection.commit();
			LOGGER.trace("Table sqllab.car was created");
			stmt.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"DROP TABLE sqllab.car ;\"");

			PreparedStatement stmt = connection.prepareStatement("DROP TABLE sqllab.car ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				LOGGER.trace(" table sqllab.car was deleted");
			else
				LOGGER.debug("table sqllab.car was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
