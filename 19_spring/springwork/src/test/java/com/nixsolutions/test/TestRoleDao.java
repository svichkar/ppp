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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.hibernate.entity.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test.xml")
public class TestRoleDao extends DBTestCase {
	public static Logger LOG = LogManager.getLogger(TestRoleDao.class.getName());
	@Autowired
	private RoleDAO roleDao;
	private JdbcDatabaseTester tester;

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().setColumnSensing(true)
				.build(new FileInputStream("src/test/resources/Role/Role.xml"));
	}

	public TestRoleDao() throws ClassNotFoundException, SQLException {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:test;SCHEMA=sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}

	@Before
	public void setUp() throws Exception {
		tester = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:mem:test;SCHEMA=sqllab", "sa", "");
		tester.setDataSet(getDataSet());
	}

	@Test
	public void testAdd() throws Exception {
		Role testRole = new Role("test");
		roleDao.createFrom(testRole);
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("ROLE");
		IDataSet expectedData = new FlatXmlDataSetBuilder().setMetaDataSet(actualData)
				.build(new FileInputStream("src/test/resources/Role/RoleCreate.xml"));
		ITable expectedTable = expectedData.getTable("ROLE");
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void testUpdate() throws Exception {
		Role testRole = new Role("test");
		roleDao.createFrom(testRole);
		testRole.setRoleName("test2");
		roleDao.update(testRole);
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("ROLE");
		IDataSet expectedData = new FlatXmlDataSetBuilder().setMetaDataSet(actualData)
				.build(new FileInputStream("src/test/resources/Role/RoleUpdate.xml"));
		ITable expectedTable = expectedData.getTable("ROLE");
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void testDelete() throws Exception {
		Role testRoleOne = new Role("test");
		roleDao.createFrom(testRoleOne);
		Role testRoleTwo = new Role("test2");
		roleDao.createFrom(testRoleTwo);
		roleDao.delete(testRoleOne);
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("ROLE");
		IDataSet expectedData = new FlatXmlDataSetBuilder().setMetaDataSet(actualData)
				.build(new FileInputStream("src/test/resources/Role/RoleDelete.xml"));
		ITable expectedTable = expectedData.getTable("ROLE");
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
