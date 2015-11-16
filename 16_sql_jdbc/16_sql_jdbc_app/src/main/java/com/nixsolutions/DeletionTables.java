package com.nixsolutions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DeletionTables {
    private final static Logger LOG = LogManager.getLogger(DeletionTables.class.getName());

    public static void main(String[] args) {
	BasicConfigurator.configure();
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
	    stmt.execute("DROP TABLE status");
	    LOG.info("Table \"status\" is deleted");
	    stmt.execute("DROP TABLE term");
	    LOG.info("Table \"term\" is deleted");
	    stmt.execute("DROP TABLE subject");
	    LOG.info("Table \"subject\" is deleted");
	    stmt.execute("DROP TABLE journal");
	    LOG.info("Table \"journal\" is deleted");
	    stmt.execute("DROP TABLE student");
	    LOG.info("Table \"student\" is deleted");
	    stmt.execute("DROP TABLE studentgroup");
	    LOG.info("Table \"studentgroup\" is deleted");
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
