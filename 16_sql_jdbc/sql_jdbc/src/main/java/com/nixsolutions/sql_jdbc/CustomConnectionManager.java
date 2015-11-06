package com.nixsolutions.sql_jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.jdbcx.JdbcConnectionPool;

public class CustomConnectionManager {
	
	private static JdbcConnectionPool h2ConnPool;
	
	public static Connection getConnection(String user, String password) 
			throws SQLException, IOException {
		if (h2ConnPool == null) {
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream("jdbc.properties");
			props.load(fis);
			h2ConnPool = JdbcConnectionPool.create(props.getProperty("DB_DRIVER"),
					props.getProperty("DB_USER"), props.getProperty("DB_PASSWORD"));
			h2ConnPool.setMaxConnections(20);
			h2ConnPool.setLoginTimeout(90);
		}
		return h2ConnPool.getConnection(user, password);
	}

}
