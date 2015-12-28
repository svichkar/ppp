package com.nixsolutions.studentgrade.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Create {

    private static final Logger LOG = LogManager.getLogger(Create.class);
    private static Connection connection;

    public static void main(String args[]) {

        try {
            Properties prop = new Properties();
            try {
                prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
            } catch (IOException e) {
                LOG.error("Properties cannot be loaded.", e);
                throw new RuntimeException(e);
            }
            String driver = prop.getProperty("jdbc.driver");
            String url = prop.getProperty("jdbc.url");
            String user = prop.getProperty("jdbc.user");
            String password = prop.getProperty("jdbc.password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            LOG.debug("Connection for user '{}' has been initialized.", user);
            Statement statement = connection.createStatement();
            LOG.debug("Statement for current connection has been created.");

            statement.executeUpdate("CREATE TABLE student_group(group_id BIGINT PRIMARY KEY, group_name VARCHAR(256) NOT NULL);");
            LOG.info("Table 'student_group' has been created.");
            statement.executeUpdate("CREATE TABLE status (status_id TINYINT PRIMARY KEY, status_name VARCHAR(256) NOT NULL);");
            LOG.info("Table 'status' has been created.");
            statement.executeUpdate("CREATE TABLE term(term_id BIGINT PRIMARY KEY, term_name VARCHAR(256) NOT NULL);");
            LOG.info("Table 'term' has been created.");
            statement.executeUpdate("CREATE TABLE student (student_id BIGINT IDENTITY PRIMARY KEY, " +
                    "first_name VARCHAR(256) NOT NULL, last_name VARCHAR(256) NOT NULL, " +
                    "admission_date DATE NOT NULL, group_id BIGINT NOT NULL REFERENCES student_group(group_id), " +
                    "status_id TINYINT NOT NULL REFERENCES status(status_id), term_id BIGINT REFERENCES term(term_id));");
            LOG.info("Table 'student' has been created.");
            statement.executeUpdate("CREATE INDEX last_name_indx ON student(last_name);");
            LOG.info("Index 'last_name_indx' on 'last_name' column of 'student' table  has been created.");
            statement.executeUpdate("CREATE TABLE grade(grade_id TINYINT PRIMARY KEY, grade_name VARCHAR(256) NOT NULL);");
            LOG.info("Table 'grade' has been created.");
            statement.executeUpdate("CREATE TABLE subject(subject_id BIGINT PRIMARY KEY, " +
                    "subject_name VARCHAR(256) NOT NULL, term_id BIGINT REFERENCES term(term_id));");
            LOG.info("Table 'subject' has been created.");
            statement.executeUpdate("CREATE INDEX subject_indx ON subject(subject_name);");
            LOG.info("Index 'subject_indx' on 'subject_name' column in 'subject' table has been created.");
            statement.executeUpdate("CREATE TABLE journal(journal_id BIGINT IDENTITY PRIMARY KEY, " +
                    "student_id BIGINT REFERENCES student(student_id), subject_id BIGINT REFERENCES subject(subject_id), " +
                    "grade_id TINYINT REFERENCES grade(grade_id));");
            LOG.info("Table 'journal' has been created.");

        } catch (ClassNotFoundException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
        LOG.info("All tables successfully created.");
    }
}