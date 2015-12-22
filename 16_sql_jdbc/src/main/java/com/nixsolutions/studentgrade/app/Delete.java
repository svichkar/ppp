package com.nixsolutions.studentgrade.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Delete {

    private static Connection connection;

    public static void main(String args[]) {



        try {
            Properties prop = new Properties();
            try {
                prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String driver = prop.getProperty("jdbc.driver");
            String url = prop.getProperty("jdbc.url");
            String user = prop.getProperty("jdbc.user");
            String password = prop.getProperty("jdbc.password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS student_group;");
            statement.executeUpdate("DROP TABLE IF EXISTS status;");
            statement.executeUpdate("DROP TABLE IF EXISTS term;");
           // statement.executeUpdate("DROP TABLE IF EXISTS grade;");
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
