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
public class CreateDB {
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
            statement.addBatch("CREATE TABLE author (" +
                    "author_id IDENTITY, " +
                    "first_name VARCHAR (256) NOT NULL,);");
            statement.addBatch("ALTER TABLE author " +
                    "ADD COLUMN last_name " +
                    "VARCHAR (256) NOT NULL;");
            statement.addBatch("CREATE TABLE category (" +
                    "category_id IDENTITY, " +
                    "name VARCHAR (256) NOT NULL,);");
            statement.addBatch("CREATE TABLE cell (" +
                    "cell_id IDENTITY, " +
                    "name VARCHAR (256) NOT NULL, );");
            statement.addBatch("CREATE TABLE client (" +
                    "client_id IDENTITY, " +
                    "first_name VARCHAR (256) NOT NULL, " +
                    "last_name VARCHAR (256) NOT NULL, " +
                    "phone VARCHAR (256), " +
                    "email VARCHAR (256),);");
            statement.addBatch("CREATE TABLE book (" +
                    "book_id IDENTITY, " +
                    "name VARCHAR (256) NOT NULL, " +
                    "cell_id BIGINT, " +
                    "category_id BIGINT, " +
                    "FOREIGN KEY (cell_id) REFERENCES cell (cell_id), " +
                    "FOREIGN KEY (category_id) REFERENCES category (category_id),);");
            statement.addBatch("CREATE TABLE author_book (" +
                    "id IDENTITY, " +
                    "author_id BIGINT, " +
                    "book_id BIGINT, " +
                    "FOREIGN KEY (book_id) REFERENCES book (book_id), " +
                    "FOREIGN KEY (author_id) REFERENCES author (author_id),);");
            statement.addBatch("CREATE TABLE rent_journal (" +
                    "ticket_id IDENTITY, " +
                    "book_id BIGINT, " +
                    "client_id BIGINT, " +
                    "rent_date DATE NOT NULL, " +
                    "expired_date DATE NOT NULL, " +
                    "return_date DATE, " +
                    "FOREIGN KEY (book_id) REFERENCES book (book_id), " +
                    "FOREIGN KEY (client_id) REFERENCES client (client_id),);");
            statement.addBatch("CREATE TABLE role (" +
                    "role_id IDENTITY," +
                    "name VARCHAR (256) NOT NULL," +
                    ");");
            statement.addBatch("CREATE TABLE user (" +
                    "user_id IDENTITY," +
                    "login VARCHAR (256) NOT NULL," +
                    "password VARCHAR (256) NOT NULL," +
                    "role_id BIGINT," +
                    "FOREIGN KEY (role_id) REFERENCES role (role_id)," +
                    ");");

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
