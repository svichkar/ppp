package com.nixsolutions.app;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteTables {
	public static final Logger LOG = LogManager.getLogger();
	private String url;
	private String name;
	private String pswd;

	public static void main(String[] args) throws SQLException {
		DeleteTables delete = new DeleteTables();
		delete.setProperties();
		delete.deleteTables();
	}

	public void setProperties() {
		InputStream input = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(input);
			url = prop.getProperty("host");
			name = prop.getProperty("login");
			pswd = prop.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteTables() {
		try (Connection conn = DriverManager.getConnection(url, name, pswd);
				Statement statem = conn.createStatement()) {
			Class.forName("org.h2.Driver");

			statem.executeUpdate("DROP TABLE author");
			statem.executeUpdate("DROP TABLE cell");
			statem.executeUpdate("DROP TABLE category");
			statem.executeUpdate("DROP TABLE client");
			statem.executeUpdate("DROP TABLE book");
			statem.executeUpdate("DROP TABLE author_book");
			statem.executeUpdate("DROP TABLE rent_journal");

			LOG.info("all the tables were deleted");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
