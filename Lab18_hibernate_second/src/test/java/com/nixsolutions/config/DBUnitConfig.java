package com.nixsolutions.config;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DBUnitConfig extends DBTestCase {
	private final static Logger LOG = LogManager.getLogger(DBUnitConfig.class.getName());

	protected IDatabaseTester tester;
	private Properties property;
	protected IDataSet beforeData;
	protected DaoFactory daoFactory;

	public DBUnitConfig(String name) throws SQLException, ClassNotFoundException {
		super(name);
		FileInputStream inputStream = null;
		property = new Properties();
		String propsLocation = this.getClass().getClassLoader().getResource("jdbc.properties").getFile();
		try {
			inputStream = new FileInputStream(propsLocation);
			property.load(inputStream);
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					LOG.error(ex.getMessage());
				}
			}
		}
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, property.getProperty("DB_driver"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, property.getProperty("DB_URL"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, property.getProperty("DB_login"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, property.getProperty("DB_password"));
		//System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "sqllab");	*/
        HibernateUtil.getSessionFactory();
		daoFactory = new H2DaoFactory();
	}

	@Before
	public void setUp() throws Exception {
		tester = new JdbcDatabaseTester(property.getProperty("DB_driver"), property.getProperty("DB_URL"),
				property.getProperty("DB_login"), property.getProperty("DB_password"));
		//conn = ConnectionManager.getConnection();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return beforeData;
	}
	
	@Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
	
	@After
	public void ternDown() {
		HibernateUtil.shutdown();
		//ConnectionManager.closeAllConnections();
	}
}
