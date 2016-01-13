package com.nixsolutions.servicestation.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class CustomConnectionManager {
    private static JdbcConnectionPool cPool;
    public static Logger LOGGER = LogManager.getLogger(CustomConnectionManager.class.getName());

    public static Connection getConnection() throws SQLException {
        if (cPool == null) {
            Properties properties = new Properties();
            String propertiesLocation = CustomConnectionManager.class.getClassLoader().getResource("jdbc.properties").getFile();
            FileInputStream inStream = null;
            try {
                inStream = new FileInputStream(propertiesLocation);
                properties.load(inStream);
                /*String dbLocation = CustomConnectionManager.class.getClassLoader().getResource("sqllab.h2.db").getFile();
                String url = "jdbc:h2:file:" +
                dbLocation.replaceAll("%20", " ").replaceAll(".h2.db", "").replaceAll("/(\\w:)", "$1") + ";FILE_LOCK=NO";
                cPool = JdbcConnectionPool.create(url, properties.getProperty("USER"), properties.getProperty("PASSWORD"));*/
                cPool = JdbcConnectionPool.create(properties.getProperty("JDBC_URL"),
                        properties.getProperty("USER"), properties.getProperty("PASSWORD"));
                cPool.setMaxConnections(10);
                cPool.setLoginTimeout(60);
            } catch (IOException e) {
                LOGGER.error(e);
            }

        }
        return cPool.getConnection();
    }

    public static void closeConnection() {
        if (cPool != null) {
            cPool.dispose();
        }
    }
}
