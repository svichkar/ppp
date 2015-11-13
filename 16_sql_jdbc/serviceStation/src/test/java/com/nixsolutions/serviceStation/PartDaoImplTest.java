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

import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.dbObjects.Part;
import com.nixsolutions.serviceStation.h2Objects.CarDaoImpl;
import com.nixsolutions.serviceStation.h2Objects.PartDaoImpl;
import com.nixsolutions.serviceStation.h2Objects.ServiceFactory;

/**
 * @author mixeyes
 *
 */
public class PartDaoImplTest {
	private final static Logger logger = LogManager.getLogger();

	protected DataSource dataSource;
	private IDatabaseTester tester;
	private IDataSet beforeData;
	private ServiceFactory factory;

	@Before
	public void init() throws Exception {
		Properties properties = DbConnector.getProperties();
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, properties.getProperty("h2URL"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, properties.getProperty("h2Login"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, properties.getProperty("h2Password"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "sqllab");
		// insert data into db
		CommonTestsMethods.updateDB();
		CommonTestsMethods.getTables("part");
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/part/part.xml"));
		tester.setDataSet(beforeData);
		// tester.onSetup();
		factory = new ServiceFactory();
	}

	@After
	public void deletePartFile() {
		new File("src/test/resources/part/part.xml").delete();
	}

	@Test
	public void createPartTable() throws SQLException, Exception {
		PartDaoImpl partDao = factory.getPartDaoImpl();
		partDao.deleteTableWithAllData();
		partDao.createNewTable();
		partDao.addNewPart("createPartTable", "createPartTable", 10);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("part");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(new File("src/test/resources/part/new_partTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("part");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void updatePartTableTest() throws SQLException, Exception {
		PartDaoImpl partDao = factory.getPartDaoImpl();
		Part part = partDao.getAllParts().get(0);
		part.setAmount(22);
		partDao.updateExistingPart(part);
		// CarDaoImplTest.getTables();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/part/part_update.xml"));
		IDataSet actualData = tester.getConnection().createDataSet();
		String[] ignoredColumn = { "part_id" };
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "part", ignoredColumn);
		/*
		 * } catch (Exception e) { logger.error(e); }
		 */
	}

	@Test
	public void addingNewPart() throws SQLException, Exception {
		PartDaoImpl partDao = factory.getPartDaoImpl();
		partDao.addNewPart("createPartTable", "createPartTable", 10);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("part");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer.build(new File("src/test/resources/part/part_newpart.xml"));
		ITable expectedTable = expectedDataSet.getTable("part");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void deletePart() throws SQLException, Exception {
		PartDaoImpl partDao = factory.getPartDaoImpl();
		partDao.deletePartByID(partDao.getAllParts().size() - 1);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("part");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer.build(new File("src/test/resources/part/deletepart.xml"));
		ITable expectedTable = expectedDataSet.getTable("part");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

}
