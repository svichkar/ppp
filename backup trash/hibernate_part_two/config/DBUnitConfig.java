package com.nixsolutions.config;

import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.impl.DAOFactoryImpl;

public class DBUnitConfig extends DBTestCase {
	private final static Logger LOG = LogManager.getLogger(DBUnitConfig.class.getName());

	protected IDatabaseTester tester;
	protected IDataSet beforeData;
	protected DAOFactory daoFactory;

	public DBUnitConfig(String name) throws SQLException, ClassNotFoundException {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:tcp://localhost/~/sqllab;SCHEMA=sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
		//System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "SQLLAB");	
		daoFactory = new DAOFactoryImpl();
	}

	@Before
	public void setUp() throws Exception {
		tester = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:mem:test;INIT=CREATE SCHEMA IF NOT EXISTS sqllab\\;SET SCHEMA sqllab",
				"sa", "");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return beforeData;
	}
	
	@Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }
}
