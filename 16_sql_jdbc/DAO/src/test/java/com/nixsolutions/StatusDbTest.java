package com.nixsolutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.app.ConnectionManager;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.StatusDAOImpl;
import com.nixsolutions.entities.Status;

public class StatusDbTest extends DBTestCase {

	private String pathToFile;
	private QueryDataSet partialDS;
	private ServiceStationDAOFactoryImpl ssFactory;

	public StatusDbTest() throws ClassNotFoundException, DatabaseUnitException,
			SQLException {
		super();
		Class cls = Class.forName("org.h2.Driver");
		// /path to xml file
		pathToFile = cls.getClassLoader()
				.getResource("com/nixsolutions/status.xml").getFile();

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
		partialDS.addTable("STATUS", "SELECT * FROM SQLLAB.status");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File(pathToFile));
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
	public void testAddStatus() throws Exception {
		StatusDAOImpl sDAO = (StatusDAOImpl) ssFactory.getDao(Status.class);
		Status s1 = new Status();
		s1.setStatus_name(UUID.randomUUID().toString());

		sDAO.create(s1);

		IDataSet ds = getConnection().createDataSet(new String[] { "STATUS" });
		ITable tActual = ds.getTable("STATUS");
		ITable tExpectedWorker = getDataSet().getTable("STATUS");

		Assert.assertNotEquals(tActual, tExpectedWorker);
	}

	@Test
	public void testUpdateStatus() throws Exception {
		StatusDAOImpl sDAO = (StatusDAOImpl) ssFactory.getDao(Status.class);
		Status s1 = sDAO.getAll().get(sDAO.getAll().size() - 1);
		s1.setStatus_name(String.format("Bugagag status %s", UUID.randomUUID()
				.toString()));

		sDAO.update(s1);

		IDataSet ds = getConnection().createDataSet(new String[] { "STATUS" });
		ITable tActual = ds.getTable("STATUS");
		ITable tExpectedWorker = getDataSet().getTable("STATUS");

		Assert.assertNotEquals(tActual.getValue(tActual.getRowCount() - 1,
				"status_name"), tExpectedWorker.getValue(
				tExpectedWorker.getRowCount() - 1, "status_name"));
	}

	@Test
	public void testDeleteStatus() throws Exception {
		StatusDAOImpl sDAO = (StatusDAOImpl) ssFactory.getDao(Status.class);
		Status s1 = sDAO.getAll().get(sDAO.getAll().size() - 1);
		sDAO.delete(s1);

		IDataSet ds = getConnection().createDataSet(new String[] { "STATUS" });
		ITable tActual = ds.getTable("STATUS");
		ITable tExpectedWorker = getDataSet().getTable("STATUS");

		Assert.assertNotEquals(tActual, tExpectedWorker);
	}
}
