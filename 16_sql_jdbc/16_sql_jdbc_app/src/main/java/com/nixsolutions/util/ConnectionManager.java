package com.nixsolutions.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {
    private final static Logger LOG = LogManager.getLogger(ConnectionManager.class.getName());
    private static JdbcConnectionPool cpool;

    public static Connection getConnection() {
	if (cpool == null) {
	    Properties props = new Properties();
	    FileInputStream fis = null;
	    try {
		fis = new FileInputStream("jdbc.properties");
	    } catch (FileNotFoundException e) {
		LOG.error(e);
	    }
	    try {
		props.load(fis);
	    } catch (IOException e) {
		LOG.error(e);
	    }
	    try {
		Class.forName(props.getProperty("jdbc.driver"));
	    } catch (ClassNotFoundException e) {
		LOG.error(e);
	    }
	    cpool = JdbcConnectionPool.create(props.getProperty("jdbc.connectionstring"),
		    props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
	}
	try {
	    return cpool.getConnection();
	} catch (SQLException e) {
	    LOG.error(e);
	    return null;
	}
    }

    public static void closePoolConnections() {
	cpool.dispose();
    }
}
