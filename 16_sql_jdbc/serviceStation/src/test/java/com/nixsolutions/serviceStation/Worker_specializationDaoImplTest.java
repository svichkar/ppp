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
import com.nixsolutions.dao.impl.PartDaoImpl;
import com.nixsolutions.dao.impl.WorkerSpecializationDaoImpl;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.WorkerSpecialization;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class Worker_specializationDaoImplTest {
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
		CommonTestsMethods.getTables("worker_specialization");
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer
				.build(new FileInputStream("src/test/resources/worker_specialization/worker_specialization.xml"));
		tester.setDataSet(beforeData);
	}

	@After
	public void deleteWorker_specializationFile() {
		new File("src/test/resources/worker_specialization/worker_specialization.xml").delete();
	}

	@Test
	public void createWorker_specializationTable() throws SQLException, Exception {
		WorkerSpecializationDaoImpl worker_specializationDao = DaoFactory.getWorkerSpecializationDaoImpl();
		worker_specializationDao.deleteTableWithAllData();
		worker_specializationDao.createNewTable();
		worker_specializationDao.createNewSpecialization("createWorker_specializationTable");

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("worker_specialization");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(new File("src/test/resources/worker_specialization/new_worker_specializationTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("worker_specialization");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void addingNewWorker_specialization() throws SQLException, Exception {
		WorkerSpecializationDaoImpl worker_specializationDao = DaoFactory.getWorkerSpecializationDaoImpl();
		worker_specializationDao.createNewSpecialization("addingNewWorker_specialization");

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("worker_specialization");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer
				.build(new File("src/test/resources/worker_specialization/newworker_specialization.xml"));
		ITable expectedTable = expectedDataSet.getTable("worker_specialization");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void deleteWorker_specialization() throws SQLException, Exception {
		WorkerSpecializationDaoImpl worker_specializationDao = DaoFactory.getWorkerSpecializationDaoImpl();
		worker_specializationDao.createNewSpecialization("addingNewWorker_specialization");

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("worker_specialization");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer
				.build(new File("src/test/resources/worker_specialization/newworker_specialization.xml"));
		ITable expectedTable = expectedDataSet.getTable("worker_specialization");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	
		worker_specializationDao.deleteSpecializationByName("addingNewWorker_specialization");

		// Fetch database data after executing your code
		IDataSet databaseDataSetd = tester.getConnection().createDataSet();
		ITable actualTabled = databaseDataSetd.getTable("worker_specialization");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducerd = new FlatXmlDataSetBuilder();
		flatXmlProducerd.setColumnSensing(false);
		IDataSet expectedDataSetd = flatXmlProducerd
				.build(new File("src/test/resources/worker_specialization/deleteworker_specialization.xml"));
		ITable expectedTabled = expectedDataSetd.getTable("worker_specialization");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTabled, actualTabled);
	}

}
