package com.nixsolutions.userTest;

import java.io.FileInputStream;

import javax.sql.DataSource;

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

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.dao.UserRoleDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.UserRole;

public class UserTest extends DBTestCase {
	private final static Logger logger = LogManager.getLogger();

	protected DataSource dataSource;
	private JdbcDatabaseTester tester;
	private IDataSet beforeData;
	private UserDao userDao;
	private UserRoleDao userRoleDao;

	public UserTest() {
		super();
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:test;SCHEMA=sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().setColumnSensing(true)
				.build(new FileInputStream("src/test/resources/User/user.xml"));
	}

	@Before
	public void setUp() throws Exception {
		tester = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:mem:test;SCHEMA=sqllab", "sa", "");
		beforeData = getDataSet();
		tester.setDataSet(beforeData);

		userDao = DaoFactory.getUserDaoImpl();
		userRoleDao = DaoFactory.getUserRoleDaoImpl();
	}

	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE;
	}

	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}

	@Test
	public void testCreateNewUser() throws Exception {
		UserRole testRole = new UserRole();
		testRole.setUser_role_name("testRole");
		userRoleDao.createNewUserRole(testRole);
		testRole = userRoleDao.getUserRole("testRole");
		userDao.createNewUser("testCreateNewUser", "pass", testRole);
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("USER");
		IDataSet expectedData = new FlatXmlDataSetBuilder().setMetaDataSet(actualData)
				.build(new FileInputStream("src/test/resources/User/addNewUser.xml"));
		ITable expectedTable = expectedData.getTable("USER");
		// Assertion.assertEquals(expectedTable, actualTable);
		String[] ignoredColumn = { "user_id" };
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoredColumn);

	}

	@Test
	public void testUpdateExistingUser() throws Exception {
		UserRole testRole = new UserRole();
		testRole.setUser_role_name("testRole");
		userRoleDao.createNewUserRole(testRole);
		testRole = userRoleDao.getUserRole("testRole");
		userDao.createNewUser("testLogin", "pass", testRole);
		User testUser = userDao.getUserByLogin("testLogin");
		testUser.setUser_password("newPass");
		userDao.updateUser(testUser);
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("USER");
		IDataSet expectedData = new FlatXmlDataSetBuilder().setMetaDataSet(actualData)
				.build(new FileInputStream("src/test/resources/User/updateExistingUser.xml"));
		ITable expectedTable = expectedData.getTable("USER");
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test
	public void testDeleteExistingUser() throws Exception {
		UserRole testRole = new UserRole();
		testRole.setUser_role_name("testRole");
		userRoleDao.createNewUserRole(testRole);
		testRole = userRoleDao.getUserRole("testRole");
		userDao.createNewUser("DeleteExistingUser", "pass", testRole);
		User testUser = userDao.getUserByLogin("DeleteExistingUser");
		
		userDao.deleteUserByID(testUser.getUser_id().intValue());
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("USER");
		IDataSet expectedData = new FlatXmlDataSetBuilder().setMetaDataSet(actualData)
				.build(new FileInputStream("src/test/resources/User/deleteExistingUser.xml"));
		ITable expectedTable = expectedData.getTable("USER");
		Assertion.assertEquals(expectedTable, actualTable);
	}
}
