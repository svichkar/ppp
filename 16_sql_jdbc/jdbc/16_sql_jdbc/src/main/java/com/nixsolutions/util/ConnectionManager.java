package com.nixsolutions.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {

	private static Properties prop;
	private static JdbcConnectionPool connectionPool;
	private static String filename = "jdbc.properties";
	static final Logger LOG = LogManager.getLogger(ConnectionManager.class);

	public static Connection getInstance() {
		InputStream input = null;
		try {
			if (connectionPool == null) {
				prop = new Properties();

				input = new FileInputStream(filename);
				prop.load(input);

				connectionPool = JdbcConnectionPool.create(
						prop.getProperty("HOST"), prop.getProperty("LOGIN"),
						prop.getProperty("PASSWORD"));

			}
		} catch (IOException e) {
			LOG.error(e, e);
		}
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {

			LOG.error(e, e);
		}
		return null;
	}

}
