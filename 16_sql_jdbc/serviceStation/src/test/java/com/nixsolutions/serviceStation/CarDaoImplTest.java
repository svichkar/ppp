package com.nixsolutions.serviceStation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.h2Objects.CarDaoImpl;
import com.nixsolutions.serviceStation.h2Objects.ServiceFactory;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class CarDaoImplTest {
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
		CommonTestsMethods.getTables("car");
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/car.xml"));
		tester.setDataSet(beforeData);
		// tester.onSetup();
		factory = new ServiceFactory();
	}

	@After
	public void deleteCarFile() {
		new File("src/test/resources/car.xml").delete();
	}

	@Test
	public void createCarTable() throws SQLException, Exception {
		CarDaoImpl carDao = factory.getCarDao();
		carDao.deleteTableWithAllData();
		carDao.createNewTable();
		carDao.createNewCar("createCarTable", "12345678901234567", "createCarTable", 1);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("car");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(new File("src/test/resources/car_newCarTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("car");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void updateCarTableTest() throws SQLException, Exception {

		/* try { */
		CarDaoImpl carDao = factory.getCarDao();
		Car car = carDao.getAllCar().get(0);
		car.setDescription("AX73-99DE have no engine");
		carDao.updateCarByVinNumber(car.getModel(), car.getDescription(), car.getVin_number(), car.getCustomer_id());
		// CarDaoImplTest.getTables();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/car_update.xml"));
		IDataSet actualData = tester.getConnection().createDataSet();
		String[] ignoredColumn = { "car_id" };
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "car", ignoredColumn);
		/*
		 * } catch (Exception e) { logger.error(e); }
		 */
	}

	@Test
	public void addingNewCarToCarTable() throws SQLException, Exception {
		CarDaoImpl carDao = factory.getCarDao();
		Car car = new Car("car_model", "12345678901234567", "car_description", 1);
		carDao.createNewCar(car.getModel(), car.getVin_number(), car.getDescription(), car.getCustomer_id());

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("car");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer.build(new File("src/test/resources/car_newCar.xml"));
		ITable expectedTable = expectedDataSet.getTable("car");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

}
