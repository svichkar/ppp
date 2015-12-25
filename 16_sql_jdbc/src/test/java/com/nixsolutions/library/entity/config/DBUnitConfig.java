package com.nixsolutions.library.entity.config;

import com.nixsolutions.library.dao.DaoFactory;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
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
 * Created by kozlovskij on 12/24/2015.
 */
public class DBUnitConfig  extends DBTestCase{
    public static Logger LOGGER = LogManager.getLogger(DBUnitConfig.class.getName());
    protected IDatabaseTester tester;
    private Properties properties;
    protected IDataSet beforeData;
    protected DaoFactory daoFactory = new DaoFactoryImpl();

    public DBUnitConfig(String name) {
        super(name);
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            LOGGER.error(e);
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, properties.getProperty("DB_DRIVER"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, properties.getProperty("DB_URL"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, properties.getProperty("DB_USER"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, properties.getProperty("DB_PASSWORD"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }

    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(properties.getProperty("DB_DRIVER"),
                properties.getProperty("DB_URL"),
                properties.getProperty("DB_USER"),
                properties.getProperty("DB_PASSWORD"));
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }
}
