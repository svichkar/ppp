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
import com.nixsolutions.dao.impl.OrderInWorkDaoImpl;
import com.nixsolutions.dao.impl.OrderStatusDaoImpl;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.util.ConnectionManager;

public class Order_statusDaoImplTest {
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
		CommonTestsMethods.getTables("order_status");
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/order_status/order_status.xml"));
		tester.setDataSet(beforeData);
	}

	@After
	public void deleteOrder_statusFile() {
		new File("src/test/resources/order_status/order_status.xml").delete();
	}

	@Test
	public void createOrder_statusTable() throws SQLException, Exception {
		OrderStatusDaoImpl order_statusDao = DaoFactory.getOrderStatusDaoImpl();
		order_statusDao.deleteTableWithAllData();
		order_statusDao.createNewTable();
		order_statusDao.createNewStatus("createOrder_statusTable");
		;

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("order_status");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(new File("src/test/resources/order_status/order_status_newTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("order_status");

		// Assert actual database table match expected table

		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void addingNewOrder_status() throws SQLException, Exception {
		OrderStatusDaoImpl order_statusDao = DaoFactory.getOrderStatusDaoImpl();
		order_statusDao.createNewStatus("addingNewOrder_status");

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("order_status");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer
				.build(new File("src/test/resources/order_status/newOrder_status.xml"));
		ITable expectedTable = expectedDataSet.getTable("order_status");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

}
