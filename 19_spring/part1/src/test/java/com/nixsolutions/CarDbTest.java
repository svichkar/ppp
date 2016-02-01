package com.nixsolutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nixsolutions.dao.impl.CarDAOImpl;
import com.nixsolutions.entities.Car;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class CarDbTest extends DBTestCase {
	private final static Logger LOG = LogManager.getLogger(CarDbTest.class);
	private String pathToFile;
	private QueryDataSet partialDataset;
	private IDatabaseTester tester;
	@Autowired
	private CarDAOImpl carDAOImpl;

	public CarDbTest() throws Exception {
		ClassLoader cLoader = Thread.currentThread().getContextClassLoader();
		// /path to xml file
		pathToFile = cLoader.getResource("com/nixsolutions/car.xml").getFile();
		// define default settings
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "SQLLAB");
	}

	@Before
	public void setUp() throws Exception {
		tester = new PropertiesBasedJdbcDatabaseTester();
		IDatabaseConnection idbconn = tester.getConnection();
		idbconn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		partialDataset = new QueryDataSet(idbconn);
		partialDataset.addTable("CAR", "SELECT * FROM SQLLAB.car");
		getOriginalDataWritten(partialDataset);
	}

	@Test
	public void testTablesAreEqual() throws SQLException, Exception {
		IDataSet ds = getConnection().createDataSet(new String[] { "CAR" });
		ITable tActualCar = ds.getTable("CAR");
		ITable tExpectedCar = getDataSet().getTable("CAR");
		Assertion.assertEquals(tExpectedCar, tActualCar);
	}

	@Test
	public void testAddingCar() throws DataSetException, SQLException, Exception {
		Car car1 = new Car();
		car1.setDescription("bla5");
		car1.setModel("TOYOTA");
		car1.setVin("FFF6354387FGGHJGD");

		carDAOImpl.create(car1);
		Car car2 = carDAOImpl.getAll().get(carDAOImpl.getAll().size() - 1);
		carDAOImpl.delete(car2);

		IDataSet ds = getConnection().createDataSet(new String[] { "CAR" });
		ITable tActual = ds.getTable("CAR");

		ITable tExpectedCar = getDataSet().getTable("CAR");
		Assertion.assertEquals(tExpectedCar, tActual);
	}

	@Test
	public void testUpdateCar() throws DataSetException, SQLException, Exception {
		List<Car> cars = carDAOImpl.getAll();
		Car car1 = cars.get(cars.size() - 1);

		car1.setDescription("this is update desc");
		carDAOImpl.update(car1);

		IDataSet ds = getConnection().createDataSet(new String[] { "CAR" });
		ITable tActual = ds.getTable("CAR");

		ITable tExpected = getDataSet().getTable("CAR");
		Assert.assertNotEquals(tExpected.getValue(tExpected.getRowCount() - 1, "description"),
				tActual.getValue(tExpected.getRowCount() - 1, "description"));
	}

	@Test
	public void testDeleteCar() throws DataSetException, SQLException, Exception {
		List<Car> cars = carDAOImpl.getAll();
		Car car1 = cars.get(cars.size() - 1);

		carDAOImpl.delete(car1);

		IDataSet ds = getConnection().createDataSet(new String[] { "CAR" });
		ITable tActual = ds.getTable("CAR");
		ITable tExpectedCar = getDataSet().getTable("CAR");

		Assert.assertNotEquals(tActual.getRowCount(), tExpectedCar.getRowCount());
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

	private void getOriginalDataWritten(QueryDataSet partialDataset) throws DataSetException, IOException {
		try (FileOutputStream fos = new FileOutputStream(pathToFile)) {
			FlatXmlDataSet.write(partialDataset, fos);
			fos.flush();
			fos.close();
		} catch (IOException ex) {
			LOG.error(ex, ex);
			throw new IOException(ex.getMessage());
		}
	}

}
