package com.nixsolutions.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class CustomConnectionManager {
	
	public static Logger LOG = LogManager.getLogger(CustomConnectionManager.class.getName());
	private static JdbcConnectionPool h2ConnPool;
	
	public static Connection getConnection() throws SQLException {
		if (h2ConnPool == null) {
			Properties props = new Properties();
			String propsLocation = CustomConnectionManager.class.getClassLoader().getResource("jdbc.properties").getFile();
			try (FileInputStream fis = new FileInputStream(propsLocation)) {
				props.load(fis);
			} catch (IOException ex) {
				LOG.error(ex.getMessage());
			}
			h2ConnPool = JdbcConnectionPool.create(props.getProperty("DB_DRIVER"),
					props.getProperty("DB_USER"), props.getProperty("DB_PASSWORD"));
			h2ConnPool.setMaxConnections(20);
			h2ConnPool.setLoginTimeout(90);
		}
		return h2ConnPool.getConnection();
	}
}
