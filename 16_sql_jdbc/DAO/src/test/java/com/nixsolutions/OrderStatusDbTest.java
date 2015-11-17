package com.nixsolutions;

import h2.CarDAOImpl;
import h2.OrderStatusDAOImpl;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entities.Car;
import entities.OrderInWork;
import entities.OrderStatus;

public class OrderStatusDbTest extends DBTestCase {

	private String pathToFile;
	private QueryDataSet partialDS;
	private ServiceStationDAOFactoryImpl ssFactory;
	private ConnectionManager connMgr;

	public OrderStatusDbTest() throws Exception {
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
		partialDS.addTable("ORDER_STATUS", "SELECT * FROM SQLLAB.order_status");
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
	public void testAddingOneOrderStatus() throws DataSetException, SQLException,
			Exception {
		OrderStatusDAOImpl osDAO = (OrderStatusDAOImpl) ssFactory.getDao(
				connMgr.getConnection(), OrderStatus.class);
		OrderStatus os1 = new OrderStatus();
		os1.setOrder_status_name("Test status");

		osDAO.create(os1);

		IDataSet ds = getConnection().createDataSet(
				new String[] { "ORDER_STATUS" });
		ITable tActual = ds.getTable("ORDER_STATUS");

		Assert.assertEquals(os1.getOrder_status_name(), tActual.getValue(
				tActual.getRowCount() - 1, "order_status_name"));
	}

}
