package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.CustomerDao;

import com.nixsolutions.serviceStation.dbObjects.Customer;

/**
 * @author mixeyes
 *
 */

public class CustomerDaoImpl implements CustomerDao {

	private final static Logger logger = LogManager.getLogger();
	private Connection dbConn;

	public CustomerDaoImpl(Connection connection) {
		this.dbConn = connection;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#getAllCustomers()
	 */
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM customer\"");
			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM sqllab.customer;");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.customer objects");
			while (set.next()) {
				customers.add(new Customer(set.getInt("customer_id"), set.getString("first_name"),
						set.getString("last_name"), set.getString("phone"),set.getInt("user_id")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return customers;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#getPhoneNumberByLastName(java.lang.String)
	 */
	public List<String> getPhoneNumberByLastName(String lastName) {
		List<String> phones = new ArrayList<String>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT phone FROM sqllab.customer WHERE last_name=?\"");
			PreparedStatement stmt = dbConn.prepareStatement("SELECT phone FROM sqllab.customer WHERE last_name=?;");
			stmt.setString(1, lastName);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the phone objects");
			while (set.next()) {
				phones.add(set.getString("phone"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#getPhoneNumberByLastName(java.lang.String)
	 */
	public Customer getCustomerByUserID(Integer user_id) {
		try {
			logger.trace("Send query \"SELECT * FROM sqllab.customer WHERE phone=?\"");
			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM sqllab.customer WHERE user_id=?");
			stmt.setInt(1, user_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.customer objects");
			while (set.next()) {
				return new Customer(set.getInt("customer_id"), set.getString("first_name"), set.getString("last_name"),
						set.getString("phone"),set.getInt("user_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#getCustomerByPhoneNumber(java.lang.String)
	 */
	public Customer getCustomerByPhoneNumber(String phone) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM sqllab.customer WHERE phone=?\"");
			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM sqllab.customer WHERE phone=?");
			stmt.setString(1, phone);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the customers objects");
			logger.trace("Generate list of the sqllab.customer objects");
			while (set.next()) {
				return new Customer(set.getInt("customer_id"), set.getString("first_name"), set.getString("last_name"),
						set.getString("phone"),set.getInt("user_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#createNewCustomer(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createNewCustomer(String lastName, String firstName, String phoneNumber) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"INSERT INTO sqllab.customer (first_name ,last_name ,phone)VALUES(?,?,?);\"");

			PreparedStatement stmt = dbConn
					.prepareStatement("INSERT INTO sqllab.customer (last_name, first_name ,phone)VALUES(?,?,?);");
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			stmt.setString(3, phoneNumber);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.customer was created");
			else
				logger.debug("New sqllab.customer was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#updateCustomer(com.nixsolutions.serviceStation.dbObjects.Customer)
	 */
	public void updateCustomer(Customer customer) {
		String query = // "UPDATE sqllab.car SET model=? ,vin_number=?,
						// description =? WHERE CUSTOMER_ID =(SELECT customer_id
						// FROM sqllab.customer WHERE last_name =? AND
						// first_name =?);";
		"UPDATE sqllab.customer SET first_name=?, last_name =?, phone =? WHERE customer_id =?;";
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"" + query + ";\"");
			// SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id
			// FROM
			// sqllab.customer WHERE first_name ='Alex' AND last_name ='Alkov');
			PreparedStatement stmt = dbConn.prepareStatement(query);
			stmt.setString(1, customer.getFirst_name());
			stmt.setString(2, customer.getLast_name());
			stmt.setString(3, customer.getPhone());
			stmt.setInt(4, customer.getCustomer_id());

			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("sqllab.customer was updated");
			else
				logger.debug("sqllab.customer was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	
	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#deleteCustomer(java.lang.String, java.lang.String)
	 */
	public void deleteCustomer(String lastName, String firstName) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DELETE FROM sqllab.customer WHERE last_name=? AND first_name=?;\"");

			PreparedStatement stmt = dbConn
					.prepareStatement("DELETE FROM sqllab.customer WHERE last_name=? AND first_name=?;");
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("customer was deleted");
			else
				logger.debug("customer was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"CREATE TABLE customer( customer_id INT IDENTITY,first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL,phone VARCHAR(32));\"");

			PreparedStatement stmt = dbConn.prepareStatement(
					"CREATE TABLE sqllab.customer( " + "customer_id INT IDENTITY, "
							+ "first_name VARCHAR(128) NOT NULL, " + "last_name VARCHAR(128) NOT NULL, "
							+ "phone VARCHAR(32));");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("Table sqllab.customer was created");
			else
				logger.debug("Table sqllab.customer was not created");
				stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData()
	 */
	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DROP TABLE sqllab.customer ;\"");

			PreparedStatement stmt = dbConn.prepareStatement("DROP TABLE sqllab.customer ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.customer was deleted");
			else
				logger.debug("table sqllab.customer was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
