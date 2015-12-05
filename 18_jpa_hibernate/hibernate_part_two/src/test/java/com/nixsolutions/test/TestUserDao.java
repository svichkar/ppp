package com.nixsolutions.test;

import java.io.FileInputStream;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;

public class TestUserDao extends DBTestCase {

	public static Logger LOG = LogManager.getLogger(TestUserDao.class.getName());
	protected DAOFactory daoFactory;
	private UserDAO userDao;
	private RoleDAO roleDao;
	private JdbcDatabaseTester tester;

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().setColumnSensing(true).build(new FileInputStream("src/test/resources/User/User.xml"));
	}

	public TestUserDao(String name) throws ClassNotFoundException, SQLException {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:test;SCHEMA=sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}

	@Before
	public void setUp() throws Exception {
		tester = new JdbcDatabaseTester("org.h2.Driver",
				"jdbc:h2:mem:test;SCHEMA=sqllab", "sa", "");
		tester.setDataSet(getDataSet());
		daoFactory = new DAOFactoryImpl();
		userDao = daoFactory.getUserDAO();
		roleDao = daoFactory.getRoleDAO();
	}

	@Test
	public void testAdd() throws Exception {
		Role testRole = new Role("test");
		testRole = roleDao.createFrom(testRole);
		User testUser = new User("testLogin", "testPassword", testRole);
		testUser = userDao.createFrom(testUser);
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("USER");
		IDataSet expectedData = new FlatXmlDataSetBuilder().setMetaDataSet(actualData)
				.build(new FileInputStream("src/test/resources/User/UserCreate.xml"));
		ITable expectedTable = expectedData.getTable("USER");
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test
	public void testUpdate() throws Exception {
		Role testRole = new Role("test");
		testRole = roleDao.createFrom(testRole);
		User testUser = new User("testLogin", "testPassword", testRole);
		testUser = userDao.createFrom(testUser);
		testUser.setUserLogin("test2Login");
		userDao.update(testUser);
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("USER");
		IDataSet expectedData = new FlatXmlDataSetBuilder().setMetaDataSet(actualData)
				.build(new FileInputStream("src/test/resources/User/UserUpdate.xml"));
		ITable expectedTable = expectedData.getTable("USER");
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testDelete() throws Exception {
		Role testRole = new Role("test");
		testRole = roleDao.createFrom(testRole);
		User testUserOne = new User("testLogin", "testPassword", testRole);
		testUserOne = userDao.createFrom(testUserOne);
		User testUserTwo = new User("testLogin2", "testPassword2", testRole);
		testUserTwo = userDao.createFrom(testUserTwo);
		userDao.delete(testUserOne);
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("USER");
		IDataSet expectedData = new FlatXmlDataSetBuilder().setMetaDataSet(actualData)
				.build(new FileInputStream("src/test/resources/User/UserDelete.xml"));
		ITable expectedTable = expectedData.getTable("USER");
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
	
	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}
	
}
