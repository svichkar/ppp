package com.nixsolutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.JdbcDatabaseTester;
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

import com.nixsolutions.dao.impl.CustomerDAOImpl;
import com.nixsolutions.entities.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class CustomerDbTest extends DBTestCase {
	private final static Logger LOG = LogManager.getLogger(CustomerDbTest.class);
	private String pathToFile;
	private QueryDataSet partialDataset;
	@Autowired
	private CustomerDAOImpl customerImpl;
	private JdbcDatabaseTester tester;

	public CustomerDbTest() throws Exception {
		super();
		ClassLoader cLoader = Thread.currentThread().getContextClassLoader();
		// /path to xml file
		pathToFile = cLoader.getResource("com/nixsolutions/customer.xml").getFile();
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
		partialDataset.addTable("CUSTOMER", "SELECT * FROM SQLLAB.customer");
		getOriginalDataWritten(partialDataset);
	}

	@Test
	public void testAddCustomer() throws DataSetException, Exception {
		Customer customer1 = new Customer();
		customer1.setF_name("Igor");
		customer1.setL_name("Petrov");
		customer1.setPhone("+295543534543");
		customerImpl.create(customer1);

		IDataSet ds = getConnection().createDataSet(new String[] { "CUSTOMER" });
		ITable tActualCar = ds.getTable("CUSTOMER");

		Assert.assertEquals(customer1.getF_name(), tActualCar.getValue(tActualCar.getRowCount() - 1, "f_name"));
		Assert.assertEquals(customer1.getL_name(), tActualCar.getValue(tActualCar.getRowCount() - 1, "l_name"));
	}

	@Test
	public void testUpdateCustomer() throws DataSetException, Exception {
		Customer customer1 = new Customer();
		customer1.setF_name("Igor");
		customer1.setL_name("Petrov");
		customer1.setPhone("+295543534543");
		customerImpl.create(customer1);

		Customer customer2 = customerImpl.getAll().get(customerImpl.getAll().size() - 1);
		customer2.setPhone("+123342423423423");

		customerImpl.update(customer2);

		IDataSet datasetCustomer = getConnection().createDataSet(new String[] { "CUSTOMER" });
		ITable tActualCust = datasetCustomer.getTable("CUSTOMER");

		Assert.assertEquals(customer1.getF_name(), tActualCust.getValue(tActualCust.getRowCount() - 1, "f_name"));
		Assert.assertEquals(customer1.getL_name(), tActualCust.getValue(tActualCust.getRowCount() - 1, "l_name"));
		Assert.assertNotEquals(customer1.getPhone(), tActualCust.getValue(tActualCust.getRowCount() - 1, "phone"));
	}
	@Test
	public void testDeleteCustomer() throws DataSetException, Exception {
		List<Customer> customers = customerImpl.getAll();
		Customer customer2 = customers.get(customers.size() - 1);
		// delete
		customerImpl.delete(customer2);

		IDataSet datasetCustomer = getConnection().createDataSet(new String[] { "CUSTOMER" });
		ITable tActualCust = datasetCustomer.getTable("CUSTOMER");

		ITable tExpectedCar = getDataSet().getTable("CUSTOMER");

		Assert.assertNotEquals(tActualCust.getRowCount(), tExpectedCar.getRowCount());
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

}
