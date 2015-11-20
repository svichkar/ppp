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
import com.nixsolutions.dao.impl.WorkerDaoImpl;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author Михаил
 *
 */
public class WorkerDaoImplTest {
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
		CommonTestsMethods.getTables("worker");
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/worker/worker.xml"));
		tester.setDataSet(beforeData);
	}

	@After
	public void deleteWorkerFile() {
		new File("src/test/resources/worker/worker.xml").delete();
	}

	@Test
	public void createWorkerTable() throws SQLException, Exception {
		WorkerDaoImpl workerDao = DaoFactory.getWorkerDaoImpl();
		workerDao.deleteTableWithAllData();
		workerDao.createNewTable();
		Worker worker = new Worker(1, "createWorkerTable", "createWorkerTable", 1, 1);
		workerDao.createWorker(worker);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("worker");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(new File("src/test/resources/worker/new_workerTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("worker");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void updateWorkerTableTest() throws SQLException, Exception {
		WorkerDaoImpl workerDao = DaoFactory.getWorkerDaoImpl();
		Worker worker = workerDao.getAllWorkers().get(0);
		worker.setLast_name("updateWorkerTableTest");
		workerDao.updateWorker(worker);
		// CarDaoImplTest.getTables();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/worker/worker_update.xml"));
		IDataSet actualData = tester.getConnection().createDataSet();
		String[] ignoredColumn = { "worker_id" };
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "worker", ignoredColumn);
		 
	}

	@Test
	public void addingWorker() throws SQLException, Exception {
		WorkerDaoImpl workerDao = DaoFactory.getWorkerDaoImpl();
		Worker worker = new Worker(1, "createWorkerTable", "createWorkerTable", 1, 2);
		workerDao.createWorker(worker);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("worker");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer.build(new File("src/test/resources/worker/new_worker.xml"));
		ITable expectedTable = expectedDataSet.getTable("worker");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void deleteWorker() throws SQLException, Exception {
		WorkerDaoImpl workerDao = DaoFactory.getWorkerDaoImpl();
		Worker worker = new Worker(1, "createWorkerTable", "createWorkerTable", 1, 2);
		workerDao.createWorker(worker);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("worker");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer.build(new File("src/test/resources/worker/new_worker.xml"));
		ITable expectedTable = expectedDataSet.getTable("worker");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
		
		workerDao.deleteWorker(worker.getLast_name(), worker.getFirst_name());

		// Fetch database data after executing your code
		IDataSet databaseDataSetD = tester.getConnection().createDataSet();
		ITable actualTableD = databaseDataSetD.getTable("worker");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducerD = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSetD = flatXmlProducerD.build(new File("src/test/resources/worker/delete_worker.xml"));
		ITable expectedTableD = expectedDataSetD.getTable("worker");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTableD, actualTableD);
	}

}
