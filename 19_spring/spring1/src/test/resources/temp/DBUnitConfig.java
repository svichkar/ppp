package com.nixsolutions;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.hibernate.util.HibernateUtil;

public class DBUnitConfig extends DBTestCase {
    protected IDatabaseTester tester;
    protected IDataSet beforeData;
    @Autowired
    private static SessionFactory sessionFactory;  
    protected Session session;  
    
    public DBUnitConfig(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:sqllabtesting;DB_CLOSE_DELAY=-1");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
       // System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }
    
    public void setUp() throws Exception {    
    	tester = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:mem:sqllabtesting;DB_CLOSE_DELAY=-1", "sa", "");
    }
 
    @Override
    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }
 
    public void tearDown() throws Exception {  
    	session.close();  
    }  
    
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
    	return DatabaseOperation.DELETE_ALL;
    	
    }
    
    protected DatabaseOperation getSetUpOperation() throws Exception {  
        return DatabaseOperation.DELETE_ALL;  
    }  

}
