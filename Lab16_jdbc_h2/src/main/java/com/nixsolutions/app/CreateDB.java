package com.nixsolutions.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.nixsolutions.util.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateDB {
    private final static Logger LOG = LogManager.getLogger(CreateDB.class.getName());

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.h2.Driver");

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            stmt.addBatch("CREATE TABLE student (" +
                    "student_id BIGINT IDENTITY," +
                    "first_name VARCHAR(256) NOT NULL," +
                    "last_name VARCHAR(256) NOT NULL," +
                    "date_birthday DATE NOT NULL," +
                    "date_entry DATE NOT NULL," +
                    "student_group_id BIGINT NOT NULL," +
                    "term_id BIGINT NOT NULL," +
                    "status_id BIGINT NOT NULL);");
            stmt.addBatch("CREATE TABLE student_group (" +
                    "student_group_id BIGINT IDENTITY," +
                    "group_name VARCHAR(256) NOT NULL);");
            stmt.addBatch("CREATE TABLE term (" +
                    "term_id BIGINT IDENTITY," +
                    "alias VARCHAR(256) NOT NULL);");
            stmt.addBatch("CREATE TABLE status (" +
                    "status_id BIGINT IDENTITY," +
                    "status_name VARCHAR(256) NOT NULL);");
            stmt.addBatch("CREATE TABLE subject (" +
                    "subject_id BIGINT IDENTITY," +
                    "name VARCHAR(256) NOT NULL," +
                    "term_id BIGINT REFERENCES term(term_id));");
            stmt.addBatch("CREATE table rate (" +
                    "rate_id INT IDENTITY," +
                    "rate_value VARCHAR(1) NOT NULL);");
            stmt.addBatch("CREATE TABLE journal (" +
                    "journal_id BIGINT IDENTITY," +
                    "student_id BIGINT REFERENCES student(student_id)," +
                    "subject_id BIGINT REFERENCES subject(subject_id)," +
                    "rate INT REFERENCES rate(rate_id));");
            stmt.addBatch("ALTER TABLE student " +
                    "ADD FOREIGN KEY (student_group_id) " +
                    "REFERENCES student_group(student_group_id);");
            stmt.addBatch("ALTER TABLE student " +
                    "ADD FOREIGN KEY (term_id) " +
                    "REFERENCES term(term_id);");
            stmt.addBatch("ALTER TABLE student " +
                    "ADD FOREIGN KEY (status_id) " +
                    "REFERENCES status(status_id);");
            stmt.addBatch("CREATE table role (" +
                    "role_id BIGINT IDENTITY," +
                    "role_name VARCHAR(256) NOT NULL);");
            stmt.addBatch("CREATE table user (" +
                    "user_id INT IDENTITY," +
                    "user_name VARCHAR(256) NOT NULL," +
                    "password VARCHAR(256) NOT NULL," +
                    "email VARCHAR(256)," +
                    "role_id BIGINT REFERENCES role(role_id));");
            //execute scripts
            stmt.executeBatch();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
        }
        System.out.println("All tables are created");
    }
}
