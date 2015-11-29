package com.nixsolutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.app.ConnectionManager;
import com.nixsolutions.dao.impl.CustomerDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.entities.Customer;

public class CustomerDbTest extends DBTestCase {
	private String pathToFile;
	private QueryDataSet partialDS;
	private ServiceStationDAOFactoryImpl ssFactory;

	public CustomerDbTest() throws Exception {
		super();
		Class cls = Class.forName("org.h2.Driver");
		// /path to xml file
		pathToFile = cls.getClassLoader()
				.getResource("com/nixsolutions/customer.xml").getFile();

		// define default settings
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				"org.h2.Driver");
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				"jdbc:h2:tcp://localhost/~/sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				"sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				"");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
				"SQLLAB");

		IDatabaseConnection idbconn = new DatabaseConnection(
				ConnectionManager.getConnection());
		idbconn.getConfig().setProperty(
				DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
				new H2DataTypeFactory());

		// init factory
		ssFactory = new ServiceStationDAOFactoryImpl();

		partialDS = new QueryDataSet(idbconn);
		partialDS.addTable("CUSTOMER", "SELECT * FROM SQLLAB.customer");
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
	public void testAddingOneMoreCustomer() throws DataSetException, Exception {
		CustomerDAOImpl customerDAO = (CustomerDAOImpl) ssFactory
				.getDao(Customer.class);
		Customer customer1 = new Customer();
		customer1.setf_name("Igor");
		customer1.setl_name("Petrov");
		customer1.setphone("+295543534543");
		customerDAO.create(customer1);

		IDataSet ds = getConnection()
				.createDataSet(new String[] { "CUSTOMER" });
		ITable tActualCar = ds.getTable("CUSTOMER");

		Assert.assertEquals(customer1.getF_name(),
				tActualCar.getValue(tActualCar.getRowCount() - 1, "f_name"));
		Assert.assertEquals(customer1.getL_name(),
				tActualCar.getValue(tActualCar.getRowCount() - 1, "l_name"));
	}

	@Test
	public void testUpdateCustomer() throws DataSetException, Exception {
		CustomerDAOImpl customerDAO = (CustomerDAOImpl) ssFactory
				.getDao(Customer.class);
		Customer customer1 = new Customer();
		customer1.setf_name("Igor");
		customer1.setl_name("Petrov");
		customer1.setphone("+295543534543");
		customerDAO.create(customer1);

		Customer customer2 = customerDAO.getAll().get(
				customerDAO.getAll().size() - 1);
		customer2.setphone("+123");

		customerDAO.update(customer2);

		IDataSet ds = getConnection()
				.createDataSet(new String[] { "CUSTOMER" });
		ITable tActualCust = ds.getTable("CUSTOMER");

		Assert.assertEquals(customer1.getF_name(),
				tActualCust.getValue(tActualCust.getRowCount() - 1, "f_name"));
		Assert.assertEquals(customer1.getL_name(),
				tActualCust.getValue(tActualCust.getRowCount() - 1, "l_name"));
		Assert.assertNotEquals(customer1.getPhone(),
				tActualCust.getValue(tActualCust.getRowCount() - 1, "phone"));
	}
}
