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
import com.nixsolutions.dao.impl.OrderInWorkDaoImpl;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class Order_in_workDaoImplTest {
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
		CommonTestsMethods.getTables("order_in_work");
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/order_in_work/order_in_work.xml"));
		tester.setDataSet(beforeData);
		// tester.onSetup();
	}

	@After
	public void deleteOrder_in_workFile() {
		new File("src/test/resources/order_in_work/order_in_work.xml").delete();
	}

	@Test
	public void createOrder_in_workTable() throws SQLException, Exception {
		OrderInWorkDaoImpl order_in_workDao = DaoFactory.getOrderInWorkDaoImpl();
		order_in_workDao.deleteTableWithAllData();
		order_in_workDao.createNewTable();
		order_in_workDao.createNewOrder(1, "createOrder_in_workTable");
		;

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("order_in_work");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(new File("src/test/resources/order_in_work/order_in_work_newOrder_in_workTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("order_in_work");

		// Assert actual database table match expected table
		String[] ignoredColumn = { "order_in_work_id", "datetime_start", "datetime_finish" };
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoredColumn);
	}

	@Test
	public void updateOrder_in_workTableTest() throws SQLException, Exception {
		OrderInWorkDaoImpl order_in_workDao = DaoFactory.getOrderInWorkDaoImpl();
		OrderInWork order = order_in_workDao.getAllOrders().get(0);
		order.setOrder_status_id(2);
		order_in_workDao.changeOrderStatusByOrderID(order.getOrder_id(), order.getOrder_status_id());
		// CarDaoImplTest.getTables();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/order_in_work/order_in_work_update.xml"));
		IDataSet actualData = tester.getConnection().createDataSet();
		String[] ignoredColumn = { "order_in_work_id", "datetime_start", "datetime_finish" };
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "order_in_work", ignoredColumn);
	}

	@Test
	public void addingNewOrder_in_workToOrder_in_workTable() throws SQLException, Exception {
		OrderInWorkDaoImpl order_in_workDao = DaoFactory.getOrderInWorkDaoImpl();
		order_in_workDao.createNewOrder(1, "addingNewOrder_in_workToOrder_in_workTable");

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("order_in_work");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer
				.build(new File("src/test/resources/order_in_work/order_in_work_neworder_in_work.xml"));
		ITable expectedTable = expectedDataSet.getTable("order_in_work");

		// Assert actual database table match expected table
		String[] ignoredColumn = { "order_in_work_id", "datetime_start", "datetime_finish" };
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoredColumn);
	}
}
