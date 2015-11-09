/**
 * 
 */
package com.nixsolutions.serviceStation.dbCommon;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author mixeyes
 *
 */
public class DbConnector {
	private final static Logger logger = LogManager.getLogger(DbConnector.class);
	private Connection conn;
	private Properties properties;

	public DbConnector() throws SQLException, ClassNotFoundException {
		this.properties = getProperties();
		Class.forName("org.h2.Driver");

		conn = DriverManager.getConnection(properties.getProperty("h2URL"), properties.getProperty("h2Login"),
				properties.getProperty("h2Password"));
	}

	public Connection getConnection() {
		return conn;
	}

	private Properties getProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			logger.debug("Try to read \"h2db.properties\" file");
			input = new FileInputStream("h2db.properties");

			// load a properties file
			prop.load(input);

		} catch (IOException ex) {
			logger.error(ex.getMessage(), new IOException(ex));
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), new IOException(e));
				}
			}
		}
		return prop;
	}

	public void closeConnection() throws SQLException {
		// add application code here
		logger.trace("close connection to db");
		conn.close();

	}

	/** create All Tables */
	public void createAllTables() {
		try {
			Statement stmt = conn.createStatement();
			stmt.addBatch("SET SCHEMA sqllab;");
			// create car table
			stmt.addBatch(
					"CREATE TABLE car( car_id INT IDENTITY, model VARCHAR(128) NOT NULL, vin_number VARCHAR(17) NOT NULL UNIQUE, description VARCHAR(256));");
			stmt.addBatch(
					"CREATE TABLE customer( customer_id INT IDENTITY,first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL,phone VARCHAR(32));");
			stmt.addBatch("CREATE TABLE status( status_id INT IDENTITY, status_name VARCHAR(128) NOT NULL);");
			stmt.addBatch(
					"CREATE TABLE worker( worker_id INT IDENTITY, specialization_id INT NOT NULL,FOREIGN KEY (specialization_id) REFERENCES  worker_specialization(specialization_id),first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL);");
			stmt.addBatch(
					"CREATE TABLE worker_specialization( specialization_id INT IDENTITY,specialization_name VARCHAR(256) NOT NULL);");
			stmt.addBatch(
					"CREATE TABLE worker_status( worker_id INT NOT NULL,FOREIGN KEY (worker_id) REFERENCES  worker(worker_id), status_id INT NOT NULL,FOREIGN KEY (status_id) REFERENCES  status(status_id));");
			stmt.addBatch(
					"CREATE TABLE order_in_work( order_id INT IDENTITY, description VARCHAR(512) NOT NULL, datetime_start TIMESTAMP NOT NULL, datetime_finish TIMESTAMP);");
			stmt.addBatch(
					"CREATE TABLE order_status( order_status_id INT IDENTITY, order_status_name VARCHAR(128) NOT NULL);");
			stmt.addBatch(
					"CREATE TABLE part( part_id INT IDENTITY, part_name VARCHAR(128) NOT NULL, vendor VARCHAR(128) NOT NULL, amount TINYINT);");
			stmt.addBatch(
					"CREATE TABLE part_order( order_id INT NOT NULL,FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), part_id INT NOT NULL,FOREIGN KEY (part_id) REFERENCES  part(part_id), amount TINYINT);");
			stmt.addBatch(
					"CREATE TABLE order_worker( order_id INT NOT NULL,FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), worker_id INT IDENTITY, FOREIGN KEY (worker_id) REFERENCES  worker(worker_id), isCompleted BOOLEAN NOT NULL DEFAULT false);");

			stmt.addBatch("ALTER TABLE order_in_work ADD COLUMN order_status_id INT NOT NULL;");
			stmt.addBatch(
					"ALTER TABLE order_in_work ADD FOREIGN KEY(order_status_id ) REFERENCES order_status (order_status_id );");
			stmt.addBatch("ALTER TABLE order_in_work ADD COLUMN car_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE order_in_work ADD FOREIGN KEY(car_id ) REFERENCES car (car_id );");
			stmt.addBatch("ALTER TABLE car ADD COLUMN customer_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE car ADD FOREIGN KEY(customer_id ) REFERENCES car (customer_id );");
			stmt.addBatch("CREATE INDEX order_idx ON order_in_work(order_id);");
			stmt.addBatch("CREATE INDEX vendorx ON part(vendor);");
			stmt.addBatch("CREATE INDEX vin_numberx ON car(vin_number);");
			stmt.addBatch("CREATE INDEX last_namex ON customer(last_name);");
			stmt.addBatch("CREATE INDEX phonex ON customer(phone);");
			stmt.executeBatch();
			stmt.close();
		} catch (SQLException ex) {
			logger.error("SQL Error code: " + ex.getErrorCode() + ". Details: " + ex.getMessage());
		}

	}

	public void deleteAllTablesWithAllData() {
		try {
			logger.trace(
					"Send query \"DROP TABLE car ,customer ,order_in_work ,order_in_work ,part ,part_order ,status ,worker ,worker_specialization ,worker_status ,order_status ,order_worker;\"");

			PreparedStatement stmt = conn.prepareStatement(
					"DROP TABLE car ,customer ,order_in_work ,order_in_work ,part ,part_order ,status ,worker ,worker_specialization ,worker_status ,order_status ,order_worker;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("All tables was deleted");
			else
				logger.debug("All tables was not deleted");
			closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
