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
import com.nixsolutions.dao.impl.OrderWorkerDaoImpl;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class Order_workerDaoImplTest {
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
		CommonTestsMethods.getTables("order_worker");
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/order_worker/order_worker.xml"));
		tester.setDataSet(beforeData);
	}

	@After
	public void deleteOrder_workerFile() {
		new File("src/test/resources/order_worker/order_worker.xml").delete();
	}

	@Test
	public void createOrder_workerTable() throws SQLException, Exception {
		OrderWorkerDaoImpl order_workerDao = DaoFactory.getOrderWorkerDaoImpl();
		order_workerDao.deleteTableWithAllData();
		order_workerDao.createNewTable();
		order_workerDao.assignWorkerToOrder(1, 2);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("order_worker");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(new File("src/test/resources/order_worker/order_worker_newTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("order_worker");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void updateOrder_workerTableTest() throws SQLException, Exception {
		OrderWorkerDaoImpl order_workerDao = DaoFactory.getOrderWorkerDaoImpl();
		order_workerDao.assignWorkerToOrder(1, 2);
		OrderWorker orderWorker = order_workerDao.getAll().get(0);
		orderWorker.setCompleted(true);
		order_workerDao.changeStatus(orderWorker.getOrder_id(), orderWorker.getWorker_id(), true);
		// CarDaoImplTest.getTables();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/order_worker/order_worker_update.xml"));
		IDataSet actualData = tester.getConnection().createDataSet();
		String[] ignoredColumn = { "order_id" };
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "order_worker", ignoredColumn);
	}

	@Test
	public void addingNewOrder_worker() throws SQLException, Exception {
		OrderWorkerDaoImpl order_workerDao = DaoFactory.getOrderWorkerDaoImpl();
		order_workerDao.assignWorkerToOrder(1, 2);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("order_worker");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer
				.build(new File("src/test/resources/order_worker/order_worker_newTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("order_worker");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}
}
