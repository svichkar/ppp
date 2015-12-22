package com.nixsolutions.studentgrade.app;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Create {

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

            statement.executeUpdate("CREATE TABLE student_group(group_id BIGINT PRIMARY KEY, group_name VARCHAR(256) NOT NULL);");
            statement.executeUpdate("CREATE TABLE status (status_id TINYINT PRIMARY KEY, status_name VARCHAR(256) NOT NULL);");
            statement.executeUpdate("CREATE TABLE term(term_id BIGINT PRIMARY KEY, term_name VARCHAR(256) NOT NULL);");
            statement.executeUpdate("CREATE TABLE grade(grade_id TINYINT PRIMARY KEY, grade_name VARCHAR(256) NOT NULL);");
            statement.executeUpdate("CREATE TABLE subject(subject_id BIGINT PRIMARY KEY, " +
                    "subject_name VARCHAR(256) NOT NULL, term_id BIGINT REFERENCES term(term_id));");
            statement.executeUpdate("CREATE TABLE student (student_id BIGINT IDENTITY PRIMARY KEY, " +
                    "first_name VARCHAR(256) NOT NULL, last_name VARCHAR(256) NOT NULL, " +
                    "admission_date DATE NOT NULL, group_id BIGINT NOT NULL REFERENCES student_group(group_id), " +
                    "status_id TINYINT NOT NULL REFERENCES status(status_id), term_id BIGINT REFERENCES term(term_id));");
            statement.executeUpdate("CREATE TABLE journal(journal_id BIGINT IDENTITY PRIMARY KEY, " +
                    "student_id BIGINT REFERENCES student(student_id), subject_id BIGINT REFERENCES subject(subject_id), " +
                    "grade_id TINYINT REFERENCES grade(grade_id));");

            //statement.executeUpdate("INSERT INTO grade (`grade_id`, `grade_name`) VALUES ('5', 'Excellent');");

            ResultSet resultSet = statement.executeQuery("select * from student_group;");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
