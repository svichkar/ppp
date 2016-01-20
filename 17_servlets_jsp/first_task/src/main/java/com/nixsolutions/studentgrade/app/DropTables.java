package com.nixsolutions.studentgrade.app;

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

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Properties properties = new Properties();
		Connection conn = null;
		Statement stmt = null;
		try (FileInputStream fis = new FileInputStream(
				DropTables.class.getClassLoader().getResource("jdbc.properties").getFile())) {
			properties.load(fis);
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
		}
		try {
			conn = DriverManager.getConnection(properties.getProperty("HOST"), properties.getProperty("USER"),
					properties.getProperty("PASSWORD"));
			LOG.info("DB connection is created");
			stmt = conn.createStatement();
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
			stmt.execute("DROP TABLE role;");
			LOG.info("Table role is dropped");
			stmt.execute("DROP TABLE user;");
			LOG.info("Table journal is user");
		} catch (SQLException e) {
			LOG.error(e);
			throw new RuntimeException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					LOG.info("Statement is closed");
				} catch (SQLException e) {
					LOG.error(e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
					LOG.info("DB connection is closed");
				} catch (SQLException e) {
					LOG.error(e);
				}
			}
		}

	}

}
