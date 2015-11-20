/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.impl.CarDaoImpl;
import com.nixsolutions.dao.impl.CustomerDaoImpl;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class CustomerDaoImplTest {
	private final static Logger logger = LogManager.getLogger();

	protected DataSource dataSource;
	private IDatabaseTester tester;
	private IDataSet beforeData;

	@Before
	public void init() throws Exception {
		Properties properties = ConnectionManager.getProperties();
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, properties.getProperty("h2URL"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, properties.getProperty("h2Login"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, properties.getProperty("h2Password"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "sqllab");
		// insert data into db
		CommonTestsMethods.updateDB();
		CommonTestsMethods.getTables("customer");
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/customer/customer.xml"));
		tester.setDataSet(beforeData);
		// tester.onSetup();
	}

	@After
	public void deleteCustomerFile() {
		new File("src/test/resources/customer/customer.xml").delete();
	}

	@Test
	public void createCustomerTable() throws SQLException, Exception {
		CustomerDaoImpl customerDao = DaoFactory.getCustomerDaoImpl();
		customerDao.deleteTableWithAllData();
		customerDao.createNewTable();
		customerDao.createNewCustomer("last Name", "First name", "+380675678912", 2);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("customer");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(new File("src/test/resources/customer/customer_newcustomerTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("customer");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void updateCustomerTableTest() throws SQLException, Exception {
		CustomerDaoImpl customerDao = DaoFactory.getCustomerDaoImpl();
		Customer customer = customerDao.getAllCustomers().get(0);
		customer.setFirst_name("updateCustomerTableTest");
		customerDao.updateCustomer(customer);
		// CarDaoImplTest.getTables();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/customer/customer_update.xml"));
		IDataSet actualData = tester.getConnection().createDataSet();
		String[] ignoredColumn = { "customer_id" };
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "customer", ignoredColumn);
	}

	@Test
	public void addingNewCustomerToCustomerTable() throws SQLException, Exception {
		CustomerDaoImpl customerDao = DaoFactory.getCustomerDaoImpl();
		Customer customer = new Customer("NewCustomer first_name", "NewCustomer last_name", "1234567893245", 3);
		customerDao.createNewCustomer(customer.getLast_name(), customer.getFirst_name(), customer.getPhone(), customer.getUser_id());
		;

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("customer");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer
				.build(new File("src/test/resources/customer/customer_newCustomer.xml"));
		ITable expectedTable = expectedDataSet.getTable("customer");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void deleteCustomer() throws SQLException, Exception {
		CustomerDaoImpl customerDao = DaoFactory.getCustomerDaoImpl();
		Customer customer = new Customer("NewCustomer first_name", "NewCustomer last_name", "1234567893245", 3);
		customerDao.createNewCustomer(customer.getLast_name(), customer.getFirst_name(), customer.getPhone(), customer.getUser_id());
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("customer");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer
				.build(new File("src/test/resources/customer/customer_newCustomer.xml"));
		ITable expectedTable = expectedDataSet.getTable("customer");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
		
		customerDao.deleteCustomer(customer.getLast_name(), customer.getFirst_name());

		// Fetch database data after executing your code
		IDataSet databaseDataSetD = tester.getConnection().createDataSet();
		ITable actualTableD = databaseDataSetD.getTable("customer");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducerD = new FlatXmlDataSetBuilder();
		flatXmlProducerD.setColumnSensing(false);
		IDataSet expectedDataSetD = flatXmlProducer
				.build(new File("src/test/resources/customer/customer_deleteCustomer.xml"));
		ITable expectedTableD = expectedDataSetD.getTable("customer");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTableD, actualTableD);
	}
}
