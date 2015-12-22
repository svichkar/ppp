package com.nixsolutions.app;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.jdbcx.JdbcConnectionPool;

public class H2ConnManager {
	private static JdbcConnectionPool pool;
	private String url;
	private String name;
	private String pswd;
	
	private H2ConnManager() {
	}

	public Connection getConnection() throws SQLException {
		if (pool == null) {
			setProperties();
			pool = JdbcConnectionPool.create(url, name, pswd);
		}		
		Connection connect = pool.getConnection();
		return connect;
	}
	
	private void setProperties(){
		InputStream input = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(input);
			url = prop.getProperty("host");
			name = prop.getProperty("login");
			pswd = prop.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
