package com.nixsolutions.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreationTables {
    private final static Logger LOG = LogManager.getLogger(CreationTables.class.getName());

    public static void main(String[] args) {
	try {
	    Class.forName("org.h2.Driver");
	} catch (ClassNotFoundException e) {
	    LOG.error(e);
	    throw new RuntimeException(e);
	}
	Statement stmt = null;
	Connection conn = null;
	try {
	    conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/sqllab", "sa", "");
	    stmt = conn.createStatement();
	    stmt.execute("CREATE TABLE student (student_id INT IDENTITY PRIMARY KEY, "
		    + "first_name VARCHAR(256) NOT NULL, last_name VARCHAR(256) NOT NULL, "
		    + "date_birthday DATE NOT NULL, date_entry DATE NOT NULL, student_group_id INT, "
		    + "term_id INT, status_id INT)");
	    LOG.info("Table \"student\" is created");
	    stmt.execute(
		    "CREATE TABLE studentgroup (student_group_id INT IDENTITY PRIMARY KEY, group_name VARCHAR(256) NOT NULL)");
	    LOG.info("Table \"student_group\" is created");
	    stmt.execute("CREATE TABLE term (term_id INT IDENTITY PRIMARY KEY, term_alias VARCHAR(256) NOT NULL)");
	    LOG.info("Table \"term\" is created");
	    stmt.execute(
		    "CREATE TABLE status (status_id INT IDENTITY PRIMARY KEY, status_name VARCHAR(256) NOT NULL)");
	    LOG.info("Table \"status\" is created");
	    stmt.execute(
		    "CREATE TABLE subject(subject_id INT IDENTITY PRIMARY KEY, subject_name VARCHAR(256) NOT NULL, term_id INT)");
	    LOG.info("Table \"subject\" is created");
	    stmt.execute(
		    "CREATE TABLE journal(journal_id INT IDENTITY PRIMARY KEY, student_id INT, subject_id INT, rate VARCHAR(128) NOT NULL)");
	    LOG.info("Table \"journal\" is created");
	    stmt.execute(
		    "ALTER TABLE student ADD FOREIGN KEY (student_group_id) REFERENCES studentgroup (student_group_id)");
	    stmt.execute("ALTER TABLE student ADD FOREIGN KEY (term_id) REFERENCES term (term_id)");
	    stmt.execute("ALTER TABLE student ADD FOREIGN KEY (status_id) REFERENCES status (status_id)");
	    stmt.execute("ALTER TABLE subject ADD FOREIGN KEY (term_id) REFERENCES term (term_id)");
	    stmt.execute("ALTER TABLE journal ADD FOREIGN KEY (student_id) REFERENCES student (student_id)");
	    stmt.execute("ALTER TABLE journal ADD FOREIGN KEY (subject_id) REFERENCES subject (subject_id)");
	    LOG.info("References between tables are created");
	} catch (SQLException e) {
	    LOG.error(e);
	    throw new RuntimeException(e);
	} finally {
	    if (stmt != null) {
		try {
		    stmt.close();
		} catch (SQLException e) {
		    LOG.error(e);
		}
	    }
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {
		    LOG.error(e);
		}
	    }
	}
    }
}
