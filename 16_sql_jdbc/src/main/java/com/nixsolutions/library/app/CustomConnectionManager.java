package com.nixsolutions.library.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by kozlovskij on 12/21/2015.
 */
public class CustomConnectionManager {
    public static Logger LOGGER = LogManager.getLogger(CustomConnectionManager.class.getName());
    private static JdbcConnectionPool h2ConnectionPool;
    public static Connection getConnection () throws SQLException {
        if (h2ConnectionPool == null) {
            Properties properties = new Properties();
            String propertyLocation = CreateDB.class.getClassLoader().getResource("jdbc.properties").getFile();
            try (FileInputStream fileInputStream = new FileInputStream(propertyLocation)) {
                properties.load(fileInputStream);
            } catch (IOException e) {
                LOGGER.error(e);
            }
            h2ConnectionPool = JdbcConnectionPool.create(properties.getProperty("DB_DRIVER"),
                    properties.getProperty("DB_USER"), properties.getProperty("DB_PASSWORD"));
            h2ConnectionPool.setMaxConnections(50);
            h2ConnectionPool.setLoginTimeout(50);
        }
        return h2ConnectionPool.getConnection();
    }
}
