package com.nixsolutions.servicestation.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class JDBCDeleteTables {
    public static Logger LOGGER = LogManager.getLogger(JDBCCreateTables.class.getName());

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Properties properties = new Properties();
        String propertiesLocation = JDBCDeleteTables.class.getClassLoader().getResource("jdbc.properties").getFile();
        try (FileInputStream inStream = new FileInputStream(propertiesLocation)) {
            properties.load(inStream);
        } catch (IOException e) {
            LOGGER.error("Not able to load jdbc.properties file " + e);
        }
        try (Connection connection = DriverManager.getConnection(properties.getProperty("JDBC_URL"),
                properties.getProperty("USER"), properties.getProperty("PASSWORD"));
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'PUBLIC'");
            while(resultSet.next()){
                statement.addBatch("DROP TABLE " + resultSet.getString("table_name"));
            }
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
