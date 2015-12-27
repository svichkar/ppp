package database;

import java.io.IOException;
import java.io.InputStream;
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
			Properties property = new Properties();
			property = new CustomConnectionManager().getProperties();
			connectionPool = JdbcConnectionPool.create(new CustomConnectionManager().getDBURL(), property.getProperty("DB_login"),
					property.getProperty("DB_password"));
			connectionPool.setMaxConnections(10);
			connectionPool.setLoginTimeout(60);
		}
		return connectionPool.getConnection();
	}
	
	private Properties getProperties(){
		InputStream inputStream = this.getClass().getResourceAsStream("/jdbc.properties");
		Properties property = new Properties();
		try {		
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
	
	private String getDBURL(){		
		Properties property = new Properties();
		property = new CustomConnectionManager().getProperties();
		String DBName = property.getProperty("DB_URL");		
		String DBLocation = "jdbc:h2:file:" + this.getClass().getClassLoader().getResource("jdbc.properties").getPath().toString().replace("%20", " ").replace("jdbc.properties", DBName.split("jdbc:h2:file:/")[1]);	
		return DBLocation;
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
