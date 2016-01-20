package com.nixsolutions.studentgrade.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {
	public static Logger LOG = LogManager.getLogger(ConnectionManager.class.getName());
	private static Properties properties = new Properties();
	private static JdbcConnectionPool cp;

	public static Connection getConnection() {
		if (cp == null) {
			try (FileInputStream fis = new FileInputStream(
					ConnectionManager.class.getClassLoader().getResource("jdbc.properties").getFile())) {
				properties.load(fis);
			} catch (IOException ex) {
				LOG.error(ex.getMessage());
			}
		}

		try {
			String dbLocation = ConnectionManager.class.getClassLoader().getResource("sqllab.h2.db").getFile();
            String url = "jdbc:h2:file:" + dbLocation.replaceAll("%20", " ").replaceAll(".h2.db", "").replaceAll("/(\\w:)", "$1") + ";FILE_LOCK=NO";
            cp = JdbcConnectionPool.create(url,
                    properties.getProperty("USER"), properties.getProperty("PASSWORD"));
			/*cp = JdbcConnectionPool.create(properties.getProperty("HOST"), properties.getProperty("USER"),
					properties.getProperty("PASSWORD"));*/
			return cp.getConnection();
		} catch (SQLException e) {
			LOG.error(e);
			throw new RuntimeException(e);
		}
	}
}