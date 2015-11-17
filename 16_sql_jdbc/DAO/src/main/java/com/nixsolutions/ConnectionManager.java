package com.nixsolutions;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {
	
	private final static Logger LOG = LogManager.getLogger(ConnectionManager.class);
	private JdbcConnectionPool h2pool;
	private Properties props;

	public ConnectionManager() throws Exception {
		try {
			props = new Properties();
			// get path to properties file
			ClassLoader cloader = getClass().getClassLoader();
			String fileWithSettings = cloader.getResource("jdbc.properties")
					.getFile();

			try (FileInputStream fs = new FileInputStream(fileWithSettings)) {
				props.load(fs);
			} catch (IOException ex) {
				LOG.debug(ex, ex);
			}
			
			h2pool = JdbcConnectionPool.create(
					props.getProperty("CONNECTION_STRING"),
					props.getProperty("USER_ID"),
					props.getProperty("USER_PASSWORD"));
			h2pool.setMaxConnections(10);
		} catch (Exception e) {
			throw new Exception();
		}

	}

	public Connection getConnection() throws SQLException {
		
		if (h2pool != null)
		{
			return h2pool.getConnection();
		}
		
		return null;
	}
}
