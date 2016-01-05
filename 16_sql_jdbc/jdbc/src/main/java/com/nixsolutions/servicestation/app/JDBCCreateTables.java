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
            statement.addBatch("CREATE TABLE car_type (" +
                    "car_type_id INT IDENTITY," +
                    "brand VARCHAR(256) NOT NULL," +
                    "model_name VARCHAR(256) NOT NULL);");
            statement.addBatch("CREATE TABLE client (" +
                    "client_id BIGINT IDENTITY," +
                    "first_name VARCHAR(256) NOT NULL," +
                    "last_name VARCHAR(256) NOT NULL);");
            statement.addBatch("CREATE TABLE employee_category (" +
                    "employee_category_id TINYINT IDENTITY," +
                    "name VARCHAR(256) NOT NULL" +
                    ");");
            statement.addBatch("CREATE TABLE car (" +
                    "car_id BIGINT PRIMARY KEY," +
                    "serial_id VARCHAR_IGNORECASE(256) NOT NULL," +
                    "car_type_id INT," +
                    "client_id BIGINT," +
                    "FOREIGN KEY (car_type_id) REFERENCES car_type (car_type_id)," +
                    "FOREIGN KEY (client_id) REFERENCES client (client_id));");
            statement.addBatch("CREATE TABLE employee (" +
                    "employee_id INT IDENTITY," +
                    "first_name VARCHAR(256) NOT NULL," +
                    "last_name VARCHAR(256) NOT NULL," +
                    "employee_category_id TINYINT," +
                    "FOREIGN KEY (employee_category_id) REFERENCES employee_category (employee_category_id));");
            statement.addBatch("CREATE TABLE car_order (" +
                    "car_order_id BIGINT IDENTITY," +
                    "car_id BIGINT," +
                    "car_order_status_id TINYINT," +
                    "start_date TIMESTAMP NOT NULL," +
                    "end_date TIMESTAMP,);");
            statement.addBatch("ALTER TABLE car_order " +
                    "ADD FOREIGN KEY (car_id)" +
                    "REFERENCES car (car_id);");
            statement.addBatch("CREATE TABLE car_order_status (" +
                    "car_order_status_id TINYINT IDENTITY," +
                    "name VARCHAR(100) NOT NULL,);");
            statement.addBatch("ALTER TABLE car_order " +
                    "ADD FOREIGN KEY (car_order_status_id)" +
                    "REFERENCES car_order_status (car_order_status_id);");
            statement.addBatch("CREATE TABLE employee_car_order (" +
                    "identifier INT PRIMARY KEY,"+
                    "employee_id INT," +
                    "car_order_id BIGINT," +
                    "FOREIGN KEY (employee_id) REFERENCES employee (employee_id)," +
                    "FOREIGN KEY (car_order_id) REFERENCES car_order (car_order_id));");
            statement.addBatch("ALTER TABLE car_type " +
                    "ADD CONSTRAINT unique_brand_model UNIQUE(brand, model_name);");
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
