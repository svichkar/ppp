package database.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {

	private final static Logger LOG = LogManager.getLogger(ConnectionManager.class.getName());
	private static JdbcConnectionPool connectionPool;

	public static Connection getConnection() throws SQLException {
		if (connectionPool == null) {
			Properties property = new Properties();
			property = new ConnectionManager().getProperties();
			connectionPool = JdbcConnectionPool.create(new ConnectionManager().getDBURL(),
					property.getProperty("DB_login"), property.getProperty("DB_password"));
			connectionPool.setMaxConnections(30);
			connectionPool.setLoginTimeout(10);
		}
		return connectionPool.getConnection();
	}

	private Properties getProperties() {
		String propsLocation = this.getClass().getClassLoader().getResource("jdbc.properties").getFile();
		FileInputStream inputStream = null;
		Properties property = new Properties();
		try {
			inputStream = new FileInputStream(propsLocation);
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
		return property;
	}

	private String getDBURL() {
		Properties property = new Properties();
		property = new ConnectionManager().getProperties();
		String DBName = property.getProperty("DB_URL");
		String DBLocation = "";
		if (DBName.contains("file")){
		DBLocation = "jdbc:h2:file:" + this.getClass().getClassLoader().getResource("jdbc.properties").getPath()
				.toString().replace("%20", " ").replace("jdbc.properties", DBName.split("jdbc:h2:file:/")[1]);}
		else {
			DBLocation = DBName;
		}
		return DBLocation;
	}

	public static void releaseConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				LOG.error(ex.getMessage());
			}
		} else {
			LOG.info("Connection is null");
		}
	}

	public static void closeAllConnections() {
		if (connectionPool != null) {
			connectionPool.dispose();
			connectionPool = null;
		}
	}
}
