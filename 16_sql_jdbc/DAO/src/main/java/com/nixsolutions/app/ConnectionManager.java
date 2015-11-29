package com.nixsolutions.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {

	private final static Logger LOG = LogManager
			.getLogger(ConnectionManager.class);
	private static JdbcConnectionPool h2pool;
	private static Properties props;

	public ConnectionManager() throws Exception {

	}

	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		if (h2pool != null) {
			return h2pool.getConnection();
		} else {
			props = new Properties();
			// get path to properties file
			@SuppressWarnings("rawtypes")
			Class cls = Class.forName("org.h2.Driver");
			ClassLoader cloader = cls.getClassLoader();
			String fileWithSettings = cloader.getResource("jdbc.properties")
					.getFile();

			try (FileInputStream fs = new FileInputStream(fileWithSettings)) {
				props.load(fs);
			} catch (IOException ex) {
				LOG.error(ex, ex);
			}

			h2pool = JdbcConnectionPool.create(
					props.getProperty("CONNECTION_STRING"),
					props.getProperty("USER_ID"),
					props.getProperty("USER_PASSWORD"));
			h2pool.setMaxConnections(30);
			return h2pool.getConnection();
		}
	}

}
