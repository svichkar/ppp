package com.nixsolutions;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {
    private final static Logger LOG = LogManager.getLogger(CreationTables.class.getName());
    private static JdbcConnectionPool cpool;

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
	if (cpool == null) {
	    Properties props = new Properties();
	    FileInputStream fis = null;
	    fis = new FileInputStream("jdbc.properties");
	    props.load(fis);
	    Class.forName(props.getProperty("jdbc.driver"));
	    cpool = JdbcConnectionPool.create(props.getProperty("jdbc.connectionstring"),
		    props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
	}
	return cpool.getConnection();
    }
    
    public static void closePoolConnections(){
	cpool.dispose();
    }
}
