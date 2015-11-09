package com.nixsolutions.sql_jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBCreation {

	public static Logger LOG = LogManager.getLogger(DBCreation.class.getName());

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Properties props = new Properties();
		String projDir = System.getProperty("user.dir");
		String curSeparator = File.separator;
		String propsLocation = projDir + curSeparator + "src" + curSeparator + "main" + 
		curSeparator + "resources" + curSeparator + "jdbc.properties";
		try (FileInputStream fis = new FileInputStream(propsLocation)) {
			props.load(fis);
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
		}
		try {
			Connection conn = DriverManager.getConnection(props.getProperty("DB_DRIVER"), 
					props.getProperty("DB_USER"), props.getProperty("DB_PASSWORD"));
			Statement stmt = conn.createStatement();
			stmt.addBatch("CREATE TABLE customer (" +
						"customer_id INT IDENTITY," +
						"first_name VARCHAR (25) NOT NULL," +
						"last_name VARCHAR (25) NOT NULL," +
						"phone VARCHAR (25));");
			stmt.addBatch("CREATE TABLE part (" +
						"part_id BIGINT IDENTITY," +
						"part_name VARCHAR (250) NOT NULL," +
						"vendor VARCHAR (100) NOT NULL," +
						"amount BIGINT NOT NULL);");
			stmt.addBatch("CREATE TABLE status (" +
						"status_id INT IDENTITY," +
						"status_name VARCHAR (100) NOT NULL);");
			stmt.addBatch("CREATE TABLE worker_specialization (" +
						"specialization_id INT IDENTITY," +
						"specialization_name VARCHAR (100) NOT NULL);");
			stmt.addBatch("CREATE TABLE order_status (" +
						"order_status_id INT IDENTITY," +
						"order_status_name VARCHAR (50) NOT NULL);");
			stmt.addBatch("CREATE TABLE car (" +
						"car_id INT IDENTITY," +
						"model VARCHAR (100) NOT NULL," +
						"vin VARCHAR (17) NOT NULL," +
						"description VARCHAR (200)," +
						"customer_id INT REFERENCES customer(customer_id));");
			stmt.addBatch("CREATE TABLE worker (" +
						"worker_id INT IDENTITY," +
						"first_name VARCHAR (25) NOT NULL," +
						"last_name VARCHAR (25) NOT NULL," +
						"specialization_id INT REFERENCES worker_specialization(specialization_id));");
			stmt.addBatch("CREATE TABLE worker_status (" +
						"worker_id INT REFERENCES worker(worker_id)," +
						"status_id INT REFERENCES status(status_id));");
			stmt.addBatch("CREATE TABLE order_in_work (" +
						"order_id BIGINT IDENTITY," +
						"order_status_id INT REFERENCES order_status(order_status_id)," +
						"description VARCHAR (255) NOT NULL," +
						"car_id INT REFERENCES car(car_id)," +
						"timestamp_start TIMESTAMP NOT NULL," +
						"timestamp_finish TIMESTAMP);");
			stmt.addBatch("CREATE TABLE order_worker (" +
						"order_id BIGINT REFERENCES order_in_work(order_id)," +
						"worker_id INT NOT NULL," +
						"is_completed VARCHAR (1) NOT NULL);");
			stmt.addBatch("ALTER TABLE order_worker " +
						"ADD FOREIGN KEY (worker_id)" +
						"REFERENCES worker(worker_id);");
			stmt.addBatch("CREATE TABLE order_part (" +
						"order_id BIGINT REFERENCES order_in_work(order_id)," +
						"part_id BIGINT NOT NULL," +
						"used_amount BIGINT NOT NULL);");
			stmt.addBatch("ALTER TABLE order_part " +
						"ADD FOREIGN KEY (part_id)" +
						"REFERENCES part(part_id);");
			stmt.addBatch("ALTER TABLE worker_status " +
						"ADD CONSTRAINT worker_unique UNIQUE (worker_id);");
			stmt.addBatch("ALTER TABLE order_worker " +
						"ADD CONSTRAINT order_worker_unique UNIQUE (order_id, worker_id);");
			stmt.addBatch("ALTER TABLE order_part " +
						"ADD CONSTRAINT order_part_unique UNIQUE (order_id, part_id);");
			stmt.executeBatch();
			stmt.close();
		} catch (SQLException ex) {
			LOG.error("SQL Error code: " + ex.getErrorCode() + ". Details: " + ex.getMessage());
		}
	}

}
