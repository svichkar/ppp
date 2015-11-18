package com.nixsolutions.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBDeletion {
	
	public static Logger LOG = LogManager.getLogger(DBCreation.class.getName());

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Properties props = new Properties();
		String propsLocation = DBDeletion.class.getClassLoader().getResource("jdbc.properties").getFile();
		try (FileInputStream fis = new FileInputStream(propsLocation)) {
			props.load(fis);
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
		}
		try {
			Connection conn = DriverManager.getConnection(props.getProperty("DB_DRIVER"), 
					props.getProperty("DB_USER"), props.getProperty("DB_PASSWORD"));
			Statement stmt = conn.createStatement();
			stmt.addBatch("SET SCHEMA sqllab;");
			stmt.addBatch("DROP TABLE order_part;");
			stmt.addBatch("DROP TABLE order_worker;");
			stmt.addBatch("DROP TABLE order_in_work;");
			stmt.addBatch("DROP TABLE order_status;");
			stmt.addBatch("DROP TABLE car;");
			stmt.addBatch("DROP TABLE customer;");
			stmt.addBatch("DROP TABLE worker;");
			stmt.addBatch("DROP TABLE status;");
			stmt.addBatch("DROP TABLE part;");
			stmt.addBatch("DROP TABLE worker_specialization;");
			stmt.executeBatch();
			stmt.close();
		} catch (SQLException ex) {
			LOG.error("SQL Error code: " + ex.getErrorCode() + ". Details: " + ex.getMessage());
		}
	}

}
