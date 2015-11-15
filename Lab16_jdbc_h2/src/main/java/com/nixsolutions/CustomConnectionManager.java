package com.nixsolutions;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class CustomConnectionManager {
	private final static Logger LOG = LogManager.getLogger(CustomConnectionManager.class.getName());
	private static JdbcConnectionPool connectionPool;
	
	public static Connection getConnection() throws SQLException{
		if (connectionPool == null){
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
			connectionPool = JdbcConnectionPool.create(property.getProperty("DB_URL"), property.getProperty("DB_login"),
					property.getProperty("DB_password"));
			connectionPool.setMaxConnections(10);
			connectionPool.setLoginTimeout(60);
		}
		return connectionPool.getConnection();
	}
	
	public static void releaseConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}		
	}
	public static void closeAllConnections() {
		if (connectionPool != null){
			connectionPool.dispose();
		}
	}
}
