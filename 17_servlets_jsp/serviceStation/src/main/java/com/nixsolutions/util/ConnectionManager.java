package com.nixsolutions.util;

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
	private final static Logger LOGGER = LogManager.getLogger();
	private static JdbcConnectionPool connPool = null;
	private static Properties properties;

	public static Connection getConnection(){
		try {
			if (connPool == null)
				initPool();
			return connPool.getConnection();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	private static void initPool() {
		properties = getProperties();
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
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
			input = ConnectionManager.class.getResourceAsStream("/h2db.properties");
			prop.load(input);
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(), new IOException(ex));
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), new IOException(e));
				}
			}
		}
		return prop;
	}

	// public void closeConnection()  {
	// // add application code here
	// LOGGER.trace("close connection to db");
	// conn.close();
	//
	// }

}
