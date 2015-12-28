package com.nixsolutions.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {
	public static Logger LOG = LogManager.getLogger(ConnectionManager.class.getName());
	private static Properties properties = new Properties();
	private static JdbcConnectionPool cp;

	public static Connection getConnection() {
		if (cp == null) {
			try (FileInputStream fis = new FileInputStream(
					ConnectionManager.class.getClassLoader().getResource("jdbc.properties").getFile())) {
				properties.load(fis);
			} catch (IOException ex) {
				LOG.error(ex.getMessage());
			}
		}

		try {
			cp = JdbcConnectionPool.create(properties.getProperty("HOST"), properties.getProperty("USER"),
					properties.getProperty("PASSWORD"));
			return cp.getConnection();
		} catch (SQLException e) {
			LOG.error(e);
			throw new RuntimeException(e);
		}
	}
}