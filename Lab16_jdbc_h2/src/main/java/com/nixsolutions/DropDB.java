package com.nixsolutions;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DropDB {
	private final static Logger LOG = LogManager.getLogger(DropDB.class.getName());
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.h2.Driver");
		FileInputStream inputStream = null;
		Properties property = new Properties();
		try {
			inputStream = new FileInputStream("jdbc.properties");
			property.load(inputStream);
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					LOG.error(ex.getMessage());
				}
			}
		}
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(property.getProperty("DB_URL"), property.getProperty("DB_login"),
					property.getProperty("DB_password"));
			stmt = conn.createStatement();
			stmt.addBatch("DROP TABLE student;");
			stmt.addBatch("DROP TABLE student_group;");
			stmt.addBatch("DROP TABLE term;");
			stmt.addBatch("DROP TABLE status;");
			stmt.addBatch("DROP TABLE subject;");
			stmt.addBatch("DROP TABLE rate;");
			stmt.addBatch("DROP TABLE journal;");
			//execute scripts
			stmt.executeBatch();			
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException ex) {
				LOG.error(ex.getMessage());
			}
		}
		System.out.println("All tables are deleted");
	}

}
