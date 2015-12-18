package com.nixsolutions.studentgrade.apps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Delete {

    private static Connection connection;

    public static void main(String args[]) {

        try {

            Class.forName("org.h2.Driver");

            Properties prop = new Properties();
            try {
                FileInputStream propStream = new FileInputStream("jdbc.properties");
                prop.load(propStream);
                propStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String url = prop.getProperty("jdbc.url");
            String user = prop.getProperty("jdbc.user");
            String password = prop.getProperty("jdbc.password");

            connection = DriverManager.getConnection(url, user, password);
            //connection = M2ConnectionManager.getConnection();
            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS student_group;");
            statement.executeUpdate("DROP TABLE IF EXISTS status;");
            statement.executeUpdate("DROP TABLE IF EXISTS term;");
            statement.executeUpdate("DROP TABLE IF EXISTS grade;");
            statement.executeUpdate("DROP TABLE IF EXISTS subject;");
            statement.executeUpdate("DROP TABLE IF EXISTS student;");
            statement.executeUpdate("DROP TABLE IF EXISTS journal;");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
