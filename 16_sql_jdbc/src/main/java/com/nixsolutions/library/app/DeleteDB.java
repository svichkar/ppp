package com.nixsolutions.library.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by kozlovskij on 12/21/2015.
 */
public class DeleteDB {
    public static Logger LOGGER = LogManager.getLogger(CreateDB.class.getName());

    public static void main(String[] args) {
        LOGGER.entry();
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        }
        Properties properties = new Properties();
        String propertieLocation = CreateDB.class.getClassLoader().getResource("jdbc.properties").getFile();
        try (FileInputStream fileInputStream = new FileInputStream(propertieLocation)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getProperty("DB_URL"),
                    properties.getProperty("DB_USER"), properties.getProperty("DB_PASSWORD"));
            Statement statement = connection.createStatement();
            statement.addBatch("DROP TABLE author ;");
            statement.addBatch("DROP TABLE author_book;");
            statement.addBatch("DROP TABLE rent_journal;");
            statement.addBatch("DROP TABLE book;");
            statement.addBatch("DROP TABLE client;");
            statement.addBatch("DROP TABLE category;");
            statement.addBatch("DROP TABLE cell;");
            statement.addBatch("DROP TABLE user;");
            statement.addBatch("DROP TABLE role;");

            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getErrorCode() + "\t" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
        }
        LOGGER.exit();
    }
}
