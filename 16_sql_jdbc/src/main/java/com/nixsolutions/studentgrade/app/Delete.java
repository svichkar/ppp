package com.nixsolutions.studentgrade.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger LOG = LogManager.getLogger(Delete.class);
    private static Connection connection;

    public static void main(String args[]) {


        try {
            Properties prop = new Properties();
            try {
                prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
            } catch (IOException e) {
                LOG.error(e);
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

            statement.executeUpdate("DROP TABLE IF EXISTS student_group;");
            LOG.info("Table 'student_group' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS status;");
            LOG.info("Table 'status' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS term;");
            LOG.info("Table 'term' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS grade;");
            LOG.info("Table 'grade' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS subject;");
            LOG.info("Table 'subject' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS student;");
            LOG.info("Table 'student' has been dropped.");
            statement.executeUpdate("DROP TABLE IF EXISTS journal;");
            LOG.info("Table 'journal' has been dropped.");

        } catch (ClassNotFoundException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }

        LOG.info("All tables dropped from database.");
    }
}
