package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.CustomerDao;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */

public class CustomerDaoImpl implements CustomerDao {

	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"SELECT * FROM customer\"");
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.customer;");
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.customer objects");
			while (set.next()) {
				customers.add(new Customer(set.getInt("customerId"), set.getString("first_name"),
						set.getString("last_name"), set.getString("phone"), set.getInt("user_id")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return customers;
	}

	@Override
	public List<String> getPhoneNumberByLastName(String lastName) {
		List<String> phones = new ArrayList<String>();
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"SELECT phone FROM sqllab.customer WHERE lastName=?\"");
			PreparedStatement stmt = connection
					.prepareStatement("SELECT phone FROM sqllab.customer WHERE lastName=?;");
			stmt.setString(1, lastName);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the phone objects");
			while (set.next()) {
				phones.add(set.getString("phone"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Customer getCustomerByUserID(Integer userId) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"SELECT * FROM sqllab.customer WHERE userId=?\"");
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.customer WHERE user_id=?");
			stmt.setInt(1, userId);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.customer objects");
			while (set.next()) {
				return new Customer(set.getInt("customer_id"), set.getString("first_name"), set.getString("last_name"),
						set.getString("phone"), set.getInt("user_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public Customer getCustomerByPhoneNumber(String phone) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"SELECT * FROM sqllab.customer WHERE phone=?\"");
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.customer WHERE phone=?");
			stmt.setString(1, phone);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate the customer object");
			while (set.next()) {
				return new Customer(set.getInt("customerId"), set.getString("first_name"), set.getString("last_name"),
						set.getString("phone"), set.getInt("user_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void createNewCustomer(String lastName, String firstName, String phoneNumber, Integer userId) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace(
					"Send query \"INSERT INTO sqllab.customer (first_name ,last_name ,phone, userId)VALUES(?,?,?,?);\"");

			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO sqllab.customer (last_name, first_name ,phone,user_id)VALUES(?,?,?,?);");
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			stmt.setString(3, phoneNumber);
			stmt.setInt(4, userId);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.customer was created");
			else
				LOGGER.debug("New sqllab.customer was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		String query = "UPDATE sqllab.customer SET firstName=?, lastName =?, phone =? WHERE customerId =?;";
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"" + query + ";\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getPhone());
			stmt.setInt(4, customer.getCustomerId());

			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("sqllab.customer was updated");
			else
				LOGGER.debug("sqllab.customer was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteCustomer(String lastName, String firstName) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"DELETE FROM sqllab.customer WHERE lastName=? AND firstName=?;\"");

			PreparedStatement stmt = connection
					.prepareStatement("DELETE FROM sqllab.customer WHERE lastName=? AND firstName=?;");
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("customer was deleted");
			else
				LOGGER.debug("customer was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "CREATE TABLE sqllab.customer( " + "customerId INT IDENTITY, "
					+ "first_name VARCHAR(128) NOT NULL, " + "last_name VARCHAR(128) NOT NULL, " + "phone VARCHAR(32),"
					+ "user_id INT NOT NULL," + "FOREIGN KEY (user_id) REFERENCES  sqllab.user(user_id))";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				LOGGER.trace("Table sqllab.customer was created");
			else
				LOGGER.debug("Table sqllab.customer was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"DROP TABLE sqllab.customer ;\"");

			PreparedStatement stmt = connection.prepareStatement("DROP TABLE sqllab.customer ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				LOGGER.trace(" table sqllab.customer was deleted");
			else
				LOGGER.debug("table sqllab.customer was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
