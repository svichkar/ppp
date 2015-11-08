package com.nixsolutions.serviceStation.h2Objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.DbConnector;
import com.nixsolutions.serviceStation.dAOFabrica.CustomerDao;
import com.nixsolutions.serviceStation.dbObjects.Customer;

public class CustomerDaoImpl implements CustomerDao {

	private final static Logger logger = LogManager.getLogger(CustomerDaoImpl.class);
	private DbConnector dbConnector;

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"SELECT * FROM customer\"");
			PreparedStatement stmt = dbConnector.getConnection().prepareStatement("SELECT * FROM customer");
			ResultSet set = stmt.executeQuery();
			dbConnector.closeConnection();
			logger.trace("Generate list of the customer objects");
			while (set.next()) {
				customers.add(new Customer(set.getInt("customer_id"), set.getString("first_name"),
						set.getString("last_name"), set.getString("phone")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return customers;
	}

	public List<String> getPhoneNumberByLastName(String lastName) {
		List<String> phones = new ArrayList<String>();
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"SELECT phone FROM customer WHERE last_name='?'\"");
			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("SELECT phone FROM customer WHERE last_name='?'");
			stmt.setString(1, lastName);
			ResultSet set = stmt.executeQuery();
			dbConnector.closeConnection();
			logger.trace("Generate list of the phone objects");
			while (set.next()) {
				phones.add(set.getString("phone"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public Customer getCustomerByPhoneNumber(String phone) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"SELECT * FROM customer WHERE phone='?'\"");
			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("SELECT * FROM customer WHERE phone='?'");
			stmt.setString(1, phone);
			ResultSet set = stmt.executeQuery();
			dbConnector.closeConnection();
			logger.trace("Generate list of the customers objects");
			logger.trace("Generate list of the customer objects");
			while (set.next()) {
				return new Customer(set.getInt("customer_id"), set.getString("first_name"), set.getString("last_name"),
						set.getString("phone"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void createNewCustomer(String lastName, String firstName, String phoneNumber) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"INSERT INTO customer (first_name ,last_name ,phone)VALUES('?','?','?');\"");

			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("INSERT INTO customer (last_name, first_name ,phone)VALUES('?','?','?');");
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			stmt.setString(3, phoneNumber);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New customer was created");
			else
				logger.debug("New customer was not created");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void deleteCustomer(String lastName, String firstName) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"DELETE FROM customer WHERE last_name='?' AND first_name='?';\"");

			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("DELETE FROM customer WHERE last_name='?' AND first_name='?';");
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("customer was deleted");
			else
				logger.debug("customer was not deleted");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"CREATE TABLE customer( customer_id INT IDENTITY,first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL,phone VARCHAR(32));\"");

			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"CREATE TABLE customer( customer_id INT IDENTITY,first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL,phone VARCHAR(32));");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table customer was created");
			else
				logger.debug("Table customer was not created");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"DROP TABLE customer ;\"");

			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("DROP TABLE customer ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table customer was deleted");
			else
				logger.debug("table customer was not deleted");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}	}

	
}
