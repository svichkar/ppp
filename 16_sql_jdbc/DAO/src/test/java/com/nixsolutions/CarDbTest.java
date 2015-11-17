package com.nixsolutions;

import h2.CarDAOImpl;
import h2.ServiceStationDAOFactoryImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import entities.Car;

public class CarDbTest extends DBTestCase {

	private String pathToFile;
	private QueryDataSet partialDS;
	private ServiceStationDAOFactoryImpl ssFactory;
	private ConnectionManager connMgr;

	public CarDbTest() throws Exception {
		super();
		Class cls = Class.forName("org.h2.Driver");
		// /path to xml file
		pathToFile = cls.getClassLoader()
				.getResource("com/nixsolutions/car.xml").getFile();

		// define default settings
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				"org.h2.Driver");
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				"jdbc:h2:tcp://localhost/~/sqldb");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				"sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				"");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
				"SQLLAB");

		connMgr = new ConnectionManager();
		IDatabaseConnection idbconn = new DatabaseConnection(
				connMgr.getConnection());
		idbconn.getConfig().setProperty(
				DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
				new H2DataTypeFactory());

		// init factory
		ssFactory = new ServiceStationDAOFactoryImpl();

		partialDS = new QueryDataSet(idbconn);
		partialDS.addTable("CAR", "SELECT * FROM SQLLAB.car");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File(pathToFile));
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}

	protected void setUpDatabaseConfig(DatabaseConfig config) {

	}

	@Before
	protected void setUp() throws DataSetException, IOException {
		try (FileOutputStream fos = new FileOutputStream(pathToFile)) {
			FlatXmlDataSet.write(partialDS, fos);
			fos.flush();
			fos.close();
		} catch (IOException ex) {
			throw new IOException(ex.getMessage());
		}
	}

	@Test
	public void testTablesAreEqual() throws SQLException, Exception {
		CarDAOImpl carDAO = (CarDAOImpl) ssFactory.getDao(
				connMgr.getConnection(), Car.class);
		IDataSet ds = getConnection().createDataSet(new String[] { "CAR" });
		ITable tActualCar = ds.getTable("CAR");

		ITable tExpectedCar = getDataSet().getTable("CAR");
		Assertion.assertEquals(tExpectedCar, tActualCar);
	}

	@Test
	public void testAddingOneCar() throws DataSetException, SQLException,
			Exception {
		CarDAOImpl carDAO = (CarDAOImpl) ssFactory.getDao(
				connMgr.getConnection(), Car.class);
		Car car1 = new Car();
		car1.setCustomer_id(1);
		car1.setDescription("bla5");
		car1.setModel("TOYOTA");
		car1.setVin("FFF6354387FGGHJGD");
		carDAO.create(car1);
		Car car2 = carDAO.findByName(car1.getModel());
		carDAO.delete(car2);

		IDataSet ds = getConnection().createDataSet(new String[] { "CAR" });
		ITable tActual = ds.getTable("CAR");

		ITable tExpectedCar = getDataSet().getTable("CAR");
		Assertion.assertEquals(tExpectedCar, tActual);
	}
	
	@Test(expected = junit.framework.ComparisonFailure.class)
	public void testUpdateOneCar() throws DataSetException, SQLException,
			Exception {
		CarDAOImpl carDAO = (CarDAOImpl) ssFactory.getDao(
				connMgr.getConnection(), Car.class);
		Car car1 = carDAO.findByName("VW");

		car1.setDescription("this is update desc");
		carDAO.update(car1);
		
		IDataSet ds = getConnection().createDataSet(new String[] { "CAR" });
		ITable tActual = ds.getTable("CAR");

		ITable tExpectedCar = getDataSet().getTable("CAR");
		Assertion.assertEquals(tExpectedCar, tActual);
	}
	

}
