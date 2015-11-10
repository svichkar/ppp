package sql_jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import entities.Customer;
import entities.PersistenceException;
import h2.CustomerDAOImpl;
import h2.H2DAOFactoryImpl;

public class TestDAO extends DBTestCase {
	
	IDatabaseConnection conn;
	
	//@BeforeClass
	public void initClass() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		//Class.forName("org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:tcp://localhost/~/sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
		Connection jdbcConn = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/sqllab", "sa", "");
        conn = new DatabaseConnection(jdbcConn);
	}
	
	//@Before
	public void init() {
		
	}
	
	public void testMethod() throws ClassNotFoundException, PersistenceException {
		Assert.assertEquals(0, 0);
		H2DAOFactoryImpl daoFactory = new H2DAOFactoryImpl();
		CustomerDAOImpl customerDAO = (CustomerDAOImpl) daoFactory.getDao((Connection) conn, Customer.class);
		List<Customer> customerList = customerDAO.getAll();
		Customer tCustomer = customerDAO.create();
		List<Customer> customerListPost = customerDAO.getAll();
		tCustomer.setFirstName("Test");
		tCustomer.setLastName("Testson");
		tCustomer.setPhone("Nokia");
		customerDAO.update(tCustomer);
		customerList = customerDAO.getAll();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		/*FlatXmlDataSet.write(conn.createDataSet(), new FileOutputStream("full.xml"));
		return new FlatXmlDataSetBuilder().build(new FileInputStream("full.xml"));*/
		return conn.createDataSet();
	}
	
	public TestDAO(String name) throws SQLException, DatabaseUnitException, ClassNotFoundException {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:tcp://localhost/~/sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "sqllab");
		Class.forName("org.h2.Driver");
		Connection jdbcConn = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/sqllab", "sa", "");
        conn = new DatabaseConnection(jdbcConn);
	}
	
	public static void main(String[] args) throws Exception {
		Class driverClass = Class.forName("org.h2.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/sqllab", "sa", "");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
	}
}
