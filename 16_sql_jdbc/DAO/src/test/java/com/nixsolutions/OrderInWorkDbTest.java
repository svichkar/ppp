package com.nixsolutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

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
import com.nixsolutions.dao.impl.OrderInWorkDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.OrderInWork;

public class OrderInWorkDbTest extends DBTestCase {
	private String pathToFile;
	private QueryDataSet partialDS;
	private ServiceStationDAOFactoryImpl ssFactory;

	public OrderInWorkDbTest() throws Exception {
		super();
		Class cls = Class.forName("org.h2.Driver");
		// /path to xml file
		pathToFile = cls.getClassLoader()
				.getResource("com/nixsolutions/orderinwork.xml").getFile();

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
		partialDS.addTable("ORDER_IN_WORK",
				"SELECT * FROM SQLLAB.order_in_work");
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
	public void testAddOrder() throws DataSetException, Exception {
		OrderInWorkDAOImpl oiwDAO = (OrderInWorkDAOImpl) ssFactory.getDao(
				OrderInWork.class);
		OrderInWork oiw1 = new OrderInWork();
		oiw1.setCar_id(4);
		oiw1.setDatetime_start(new Timestamp(new Date().getTime()));
		oiw1.setDescription("ola-la new oder");
		oiw1.setOrder_status_id(2);
		oiwDAO.create(oiw1);

		IDataSet ds = getConnection().createDataSet(
				new String[] { "ORDER_IN_WORK" });
		ITable tActual = ds.getTable("ORDER_IN_WORK");

		Assert.assertEquals(BigInteger.valueOf(oiw1.getCar_id()),
				tActual.getValue(tActual.getRowCount() - 1, "car_id"));
		Assert.assertEquals(oiw1.getOrder_status_id(), tActual.getValue(
				tActual.getRowCount() - 1, "order_status_id"));
		Assert.assertEquals(oiw1.getDescription(), tActual.getValue(
				tActual.getRowCount() - 1, "description"));

	}

}
