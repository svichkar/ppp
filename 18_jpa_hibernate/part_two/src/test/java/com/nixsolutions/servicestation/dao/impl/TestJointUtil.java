package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class TestJointUtil extends DBTestCase{
    public static Logger LOGGER = LogManager.getLogger(TestJointUtil.class.getName());
    protected IDatabaseTester tester;
    private Properties properties;
    protected IDataSet preCondition;
    protected FactoryDAO factoryDAO = new FactoryDAOImpl();

    public TestJointUtil(String name) {
        super(name);
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            LOGGER.error(e);
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, properties.getProperty("DRIVER"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, properties.getProperty("JDBC_URL"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, properties.getProperty("USER"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, properties.getProperty("PASSWORD"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }

    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(properties.getProperty("DRIVER"),
                properties.getProperty("JDBC_URL"),
                properties.getProperty("USER"),
                properties.getProperty("PASSWORD"));
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return preCondition;
    }
}