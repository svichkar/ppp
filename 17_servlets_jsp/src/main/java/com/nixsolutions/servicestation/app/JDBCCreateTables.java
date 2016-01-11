package com.nixsolutions.servicestation.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class JDBCCreateTables {
    public static Logger LOGGER = LogManager.getLogger(JDBCCreateTables.class.getName());

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Properties properties = new Properties();
        String propertiesLocation = JDBCCreateTables.class.getClassLoader().getResource("jdbc.properties").getFile();
        try (FileInputStream inStream = new FileInputStream(propertiesLocation)) {
            properties.load(inStream);
        } catch (IOException e) {
            LOGGER.error("Not able to load jdbc.properties file " + e);
        }
        try (Connection connection = DriverManager.getConnection(properties.getProperty("JDBC_URL"),
                properties.getProperty("USER"), properties.getProperty("PASSWORD"))){
            Statement statement = connection.createStatement();
            statement.addBatch("CREATE TABLE role (" +
                    "role_id INT PRIMARY KEY," +
                    "role_name VARCHAR(256) NOT NULL);");
            statement.addBatch("CREATE TABLE user (" +
                    "user_id BIGINT PRIMARY KEY," +
                    "login VARCHAR(256) NOT NULL," +
                    "password VARCHAR(256) NOT NULL,"+
                    "role_id BIGINT,"+
                    "FOREIGN KEY (role_id) REFERENCES role (role_id));");
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
