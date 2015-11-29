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
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.app.ConnectionManager;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.WorkerDAOImpl;
import com.nixsolutions.entities.Worker;

public class WorkerDbTest extends DBTestCase {

	private String pathToFile;
	private QueryDataSet partialDS;
	private ServiceStationDAOFactoryImpl ssFactory;

	public WorkerDbTest() throws ClassNotFoundException, DatabaseUnitException,
			SQLException {
		super();
		Class cls = Class.forName("org.h2.Driver");
		// /path to xml file
		pathToFile = cls.getClassLoader()
				.getResource("com/nixsolutions/worker.xml").getFile();

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
		partialDS.addTable("WORKER", "SELECT * FROM SQLLAB.worker");
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
	public void testAddWorker() throws Exception {
		WorkerDAOImpl wDAO = (WorkerDAOImpl) ssFactory.getDao(Worker.class);
		Worker w1 = new Worker();
		w1.setF_name("Test");
		w1.setL_name("Test2");
		w1.setSpec_id(1);
		w1.setStatus_id(3);
		wDAO.create(w1);

		IDataSet ds = getConnection().createDataSet(new String[] { "WORKER" });
		ITable tActual = ds.getTable("WORKER");

		ITable tExpectedWorker = getDataSet().getTable("WORKER");

		Assert.assertNotEquals(tActual, tExpectedWorker);
	}

	@Test
	public void testUpdateWorker() throws Exception {
		WorkerDAOImpl wDAO = (WorkerDAOImpl) ssFactory.getDao(Worker.class);
		Worker w1 = wDAO.getAll().get(wDAO.getAll().size() - 1);
		w1.setL_name(UUID.randomUUID().toString());
		wDAO.update(w1);

		IDataSet ds = getConnection().createDataSet(new String[] { "WORKER" });
		ITable tActual = ds.getTable("WORKER");
		ITable tExpectedWorker = getDataSet().getTable("WORKER");

		Assert.assertNotEquals(tActual.getValue(
				tExpectedWorker.getRowCount() - 1, "l_name"), tExpectedWorker
				.getValue(tExpectedWorker.getRowCount() - 1, "l_name"));
	}

	@Test
	public void testDeleteWorker() throws Exception {
		WorkerDAOImpl wDAO = (WorkerDAOImpl) ssFactory.getDao(Worker.class);
		Worker w1 = wDAO.getAll().get(wDAO.getAll().size() - 1);

		wDAO.delete(w1);

		IDataSet ds = getConnection().createDataSet(new String[] { "WORKER" });
		ITable tActual = ds.getTable("WORKER");
		ITable tExpectedWorker = getDataSet().getTable("WORKER");

		Assert.assertNotEquals(tActual, tExpectedWorker);
	}

}
