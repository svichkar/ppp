package com.nixsolutions.app;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class H2ConnManager {
	public static final Logger LOG = LogManager.getLogger();
	private static JdbcConnectionPool pool;
	private static String url = "jdbc:h2:tcp://localhost/~/sqllab";
	private static String name= "sa";
	private static String pswd = "";

	private H2ConnManager() {
	}

	public static Connection getConnection() throws SQLException {
		if (pool == null) {
			//setProperties();
			pool = JdbcConnectionPool.create(url, name, pswd);
		}
		Connection connect = pool.getConnection();
		return connect;
	}

	private static void setProperties() {
		Properties prop = new Properties();
		InputStream input = prop.getClass().getClassLoader()
				.getResourceAsStream("jdbc.properties");
		
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
