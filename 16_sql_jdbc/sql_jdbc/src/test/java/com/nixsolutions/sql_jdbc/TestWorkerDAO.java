package com.nixsolutions.sql_jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
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

import entities.Worker;
import h2.H2DAOFactoryImpl;
import h2.WorkerDAOImpl;

public class TestWorkerDAO extends DBTestCase {
	
	private IDatabaseConnection conn;
	private IDataSet dataSet;
	private H2DAOFactoryImpl daoFactory;
	private static final String CURRENT_SEPARATOR = File.separator;
	private static final String XML_PATH = System.getProperty("user.dir") + CURRENT_SEPARATOR + "src" + CURRENT_SEPARATOR + 
			"test" + CURRENT_SEPARATOR + "resources" + CURRENT_SEPARATOR + "worker.xml";
	
	public TestWorkerDAO(String name) throws SQLException, DatabaseUnitException, ClassNotFoundException {
		super(name);
		Class.forName("org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:tcp://localhost/~/sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "sqllab");		
		Connection jdbcConn = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/sqllab", "sa", "");
        conn = new DatabaseConnection(jdbcConn);
        conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
        daoFactory = new H2DAOFactoryImpl();
		QueryDataSet partialDataSet = new QueryDataSet(conn);
        partialDataSet.addTable("WORKER", "SELECT * FROM sqllab.worker");
        dataSet = partialDataSet;
	}
	
	protected void setUpDatabaseConfig(DatabaseConfig config) {
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
    }
	
	protected void setUp() throws Exception {
		FileOutputStream fos = new FileOutputStream(XML_PATH);
		FlatXmlDataSet.write(dataSet, fos);
		fos.flush();
		fos.close();
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		FlatXmlDataSetBuilder f = new FlatXmlDataSetBuilder().setMetaDataSet(dataSet);
		FlatXmlDataSet ds = f.build(new FileInputStream(XML_PATH));
		ds.endDataSet();
		return ds;
	}
	
	public void testWorkerDAOAddsEntity() throws DataSetException, Exception {
		WorkerDAOImpl workerDAO = (WorkerDAOImpl) daoFactory.getDao(conn.getConnection(), Worker.class);
		Worker tWorker = workerDAO.create();
		IDataSet dbSet = getConnection().createDataSet(new String[] {"WORKER"});
		ITable dbTable = dbSet.getTable("WORKER");
		Assert.assertEquals(tWorker.getFirstName(), dbTable.getValue(dbTable.getRowCount() - 1, "first_name"));
		Assert.assertEquals(tWorker.getLastName(), dbTable.getValue(dbTable.getRowCount() - 1, "last_name"));
		Assert.assertEquals(tWorker.getStatusId() == 0 ? null : tWorker.getStatusId(),
				dbTable.getValue(dbTable.getRowCount() - 1, "status_id"));
		Assert.assertEquals(tWorker.getSpecId() == 0 ? null : tWorker.getSpecId(), 
				dbTable.getValue(dbTable.getRowCount() - 1, "specialization_id"));
	}
	
	public void testWorkerDAODeletesEntity() throws DataSetException, Exception {
		WorkerDAOImpl workerDAO = (WorkerDAOImpl) daoFactory.getDao(conn.getConnection(), Worker.class);
		Worker tWorker = workerDAO.create();
		workerDAO.delete(tWorker);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"WORKER"});
		ITable dbTable = dbSet.getTable("WORKER");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("WORKER");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testWorkerDAOUpdatesEntity() throws Exception {
		WorkerDAOImpl workerDAO = (WorkerDAOImpl) daoFactory.getDao(conn.getConnection(), Worker.class);
		List<Worker> workerList = workerDAO.getAll();
		Worker tWorker = workerList.get(workerList.size() - 1);
		tWorker.setLastName("No Last Name");
		workerDAO.update(tWorker);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"WORKER"});
		ITable dbTable = dbSet.getTable("WORKER");
		Assert.assertEquals(tWorker.getLastName(), dbTable.getValue(dbTable.getRowCount() - 1, "last_name"));
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}
}
