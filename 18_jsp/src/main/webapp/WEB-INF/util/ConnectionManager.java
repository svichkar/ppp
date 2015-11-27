package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {

	private static Properties prop;
	private static JdbcConnectionPool connectionPool;
	private static String filename = "jdbc.properties";
	static final Logger LOG = LogManager.getLogger(ConnectionManager.class);

	public static Connection getConnection() {
		BasicConfigurator.configure();
		InputStream input = null;
		try {
			if (connectionPool == null) {
				prop = new Properties();
				
				//input = new FileInputStream(filename);
				ClassLoader classLoader = ConnectionManager.class.getClassLoader();
				input = new FileInputStream(
						classLoader.getResource("").getPath().toString().replace("%20", " ") + "jdbc.properties");
				
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
