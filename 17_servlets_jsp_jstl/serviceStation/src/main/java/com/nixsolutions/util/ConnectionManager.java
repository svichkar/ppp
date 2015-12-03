/**
 * 
 */
package com.nixsolutions.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 * @author mixeyes
 *
 */
public class ConnectionManager {
	private final static Logger logger = LogManager.getLogger();
	private static JdbcConnectionPool connPool = null;
	private static Properties properties;

	public static Connection getConnection() throws ClassNotFoundException {
		try {
			if (connPool == null)
				initPool();
			return connPool.getConnection();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	private static void initPool() throws SQLException, ClassNotFoundException {
		properties = getProperties();
		Class.forName("org.h2.Driver");
		/*
		String dbLocation = new ConnectionManager().getClass().getClassLoader().getResource("sqllab.h2.db")
					.getFile();
		String dbPath = "jdbc:h2:file:" + dbLocation.substring(1, dbLocation.length()-6);
		*/ 
		
		connPool = JdbcConnectionPool.create(properties.getProperty("h2URL"), properties.getProperty("h2Login"),
				properties.getProperty("h2Password"));
	}

	public static Properties getProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String propsLocation = new ConnectionManager().getClass().getClassLoader().getResource("h2db.properties")
					.getFile();
			input = new FileInputStream(propsLocation);
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

	// public void closeConnection() throws SQLException {
	// // add application code here
	// logger.trace("close connection to db");
	// conn.close();
	//
	// }

}
