package com.nixsolutions.servicestation.util;

import org.h2.jdbcx.JdbcConnectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class CustomConnectionManager {
    private static JdbcConnectionPool cPool;

    public static Connection getConnection() throws SQLException, IOException {
        if (cPool == null) {
            Properties properties = new Properties();
            String propertiesLocation = CustomConnectionManager.class.getClassLoader().getResource("jdbc.properties").getFile();
            FileInputStream inStream = new FileInputStream(propertiesLocation);
            properties.load(inStream);
            cPool = JdbcConnectionPool.create(properties.getProperty("JDBC_URL"),
                    properties.getProperty("USER"), properties.getProperty("PASSWORD"));
            cPool.setMaxConnections(10);
            cPool.setLoginTimeout(60);
        }
        return cPool.getConnection();
    }
    public static void closeConnection(){
        cPool.dispose();
    }
}
