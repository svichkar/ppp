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

public class DropTables {

	public static Logger LOG = LogManager.getLogger(DropTables.class.getName());

	public static void main(String[] args) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException exc) {
			LOG.error(exc.getMessage());
		}
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(
				DropTables.class.getClassLoader().getResource("jdbc.properties").getFile())) {
			properties.load(fis);
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
		}
		try (Connection conn = DriverManager.getConnection(properties.getProperty("HOST"),
				properties.getProperty("USER"), properties.getProperty("PASSWORD"))) {
			try (Statement stmt = conn.createStatement()) {
				LOG.info("DB connection is created");
				LOG.info("Statement is created");
				stmt.execute("DROP TABLE student_group;");
				LOG.info("Table student_group is dropped");
				stmt.execute("DROP TABLE status;");
				LOG.info("Table status is dropped");
				stmt.execute("DROP TABLE term;");
				LOG.info("Table term is dropped");
				stmt.execute("DROP TABLE subject;");
				LOG.info("Table subject is dropped");
				stmt.execute("DROP TABLE grade;");
				LOG.info("Table grade is dropped");
				stmt.execute("DROP TABLE student;");
				LOG.info("Table student is dropped");
				stmt.execute("DROP TABLE journal;");
				LOG.info("Table journal is dropped");
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

}
