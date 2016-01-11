package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

public class DBUnitConfig extends DBTestCase {
    protected IDatabaseTester tester;
    private Properties prop;
    protected IDataSet beforeData;
    
    public DBUnitConfig(String name) {
        super(name);
        prop = new Properties();
		String dbPath = new File(Thread.currentThread().getContextClassLoader().getResource("sqllab.mv.db").getPath())
				.getParentFile().getPath();
		
        try {
            prop.load(Thread.currentThread()
                      .getContextClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("driver"));
      //  System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, prop.getProperty("host"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:file:" + dbPath + "/sqllab;FILE_LOCK=NO");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("login"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("password"));
       // System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }
    
    public void setUp() throws Exception {
    	String dbPath = new File(Thread.currentThread().getContextClassLoader().getResource("sqllab.mv.db").getPath())
				.getParentFile().getPath();
        tester = new JdbcDatabaseTester(prop.getProperty("driver"),
                                        //prop.getProperty("host"),
        		"jdbc:h2:file:" + dbPath + "/sqllab;FILE_LOCK=NO",
                                        prop.getProperty("login"),
                                        prop.getProperty("password"));
    }
 
    @Override
    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }
 
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }

}
