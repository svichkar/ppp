/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;

/**
 * @author Михаил
 *
 */
public class CreateDB {
	private final static Logger logger = LogManager.getLogger();

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		Properties properties = DbConnector.getProperties();

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(properties.getProperty("h2URL"), properties.getProperty("h2Login"),
					properties.getProperty("h2Password"));
			Statement stmt = conn.createStatement();
			stmt.addBatch("CREATE SCHEMA sqllab;");
			stmt.addBatch("CREATE TABLE sqllab.worker_specialization( " + "specialization_id INT IDENTITY,"
					+ "specialization_name VARCHAR(256) NOT NULL);");
			stmt.addBatch("CREATE TABLE sqllab.worker_status( " + "worker_status_id INT IDENTITY, "
					+ "worker_status_name VARCHAR(128) NOT NULL); ");
			stmt.addBatch("CREATE TABLE sqllab.worker( " + "worker_id INT IDENTITY, " + "specialization_id INT NOT NULL,"
					+ "FOREIGN KEY (specialization_id) REFERENCES  worker_specialization(specialization_id),"
					+ "first_name VARCHAR(128) NOT NULL," + "last_name VARCHAR(128) NOT NULL, "
					+ "worker_status_id INT NOT NULL,FOREIGN KEY (worker_status_id) REFERENCES  worker_status(worker_status_id),);");
			stmt.addBatch("CREATE TABLE sqllab.order_in_work( " + "order_id INT IDENTITY, "
					+ "order_description VARCHAR(512) NOT NULL, " + "datetime_start TIMESTAMP NOT NULL, "
					+ "datetime_finish TIMESTAMP);");
			stmt.addBatch("CREATE TABLE sqllab.order_status( " + "order_status_id INT IDENTITY, "
					+ "order_status_name VARCHAR(128) NOT NULL);");
			stmt.addBatch("CREATE TABLE sqllab.part( " + "part_id INT IDENTITY, " + "part_name VARCHAR(128) NOT NULL, "
					+ "vendor VARCHAR(128) NOT NULL, " + "amount TINYINT);");
			stmt.addBatch("CREATE TABLE sqllab.part_order( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "part_id INT NOT NULL,"
					+ "FOREIGN KEY (part_id) REFERENCES  part(part_id), amount TINYINT);");
			// create sqllab.car table
			stmt.addBatch("CREATE TABLE sqllab.car( " + "car_id INT IDENTITY, " + "car_model VARCHAR(128) NOT NULL, "
					+ "vin_number VARCHAR(17) NOT NULL UNIQUE, " + "car_description VARCHAR(256));");
			stmt.addBatch(
					"CREATE TABLE sqllab.customer( " + "customer_id INT IDENTITY, " + "first_name VARCHAR(128) NOT NULL, "
							+ "last_name VARCHAR(128) NOT NULL, " + "phone VARCHAR(32));");
			stmt.addBatch("CREATE TABLE sqllab.order_worker ( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "worker_id INT IDENTITY, "
					+ "FOREIGN KEY (worker_id) REFERENCES  sqllab.worker (worker_id), "
					+ "isCompleted BOOLEAN NOT NULL DEFAULT false);");

			stmt.addBatch("ALTER TABLE sqllab.order_in_work " + "ADD COLUMN order_status_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE sqllab.order_in_work "
					+ "ADD FOREIGN KEY(order_status_id ) REFERENCES sqllab.order_status (order_status_id );");
			stmt.addBatch("ALTER TABLE sqllab.order_in_work " + "ADD COLUMN  car_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE sqllab.order_in_work " + "ADD FOREIGN KEY(car_id ) REFERENCES sqllab.car (car_id );");
			stmt.addBatch("ALTER TABLE sqllab.car " + "ADD COLUMN customer_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE sqllab.car " + "ADD FOREIGN KEY(customer_id ) REFERENCES sqllab.customer (customer_id );");

			stmt.addBatch("CREATE INDEX order_idx ON sqllab.order_in_work(order_id);");
			stmt.addBatch("CREATE INDEX vendorx ON sqllab.part(vendor);");
			stmt.addBatch("CREATE INDEX vin_numberx ON sqllab.car(vin_number);");
			stmt.addBatch("CREATE INDEX last_namex ON sqllab.customer(last_name);");
			stmt.addBatch("CREATE INDEX phonex ON sqllab.customer(phone);");
			stmt.executeBatch();
			stmt.close();
			logger.trace("db was created");

		} catch (SQLException ex) {
			logger.error("SQL Error code: " + ex.getErrorCode() + ". Details: " + ex.getMessage());
		} finally {
			conn.close();
		}

	}

}
