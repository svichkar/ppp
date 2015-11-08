/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}
