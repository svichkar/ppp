package com.nixsolutions.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Worker;

public class TestOrderWorkerDAO extends DBTestCase {
	
	public static Logger LOG = LogManager.getLogger(TestOrderWorkerDAO.class.getName());
	private IDatabaseConnection conn;
	private IDataSet dataSet;
	private DAOFactory daoFactory;
	private String xmlPath; 
	
	public TestOrderWorkerDAO(String name) throws SQLException, DatabaseUnitException, ClassNotFoundException {
		super(name);
		Properties props = new Properties();
		String propsLocation = DBCreation.class.getClassLoader().getResource("jdbc.properties").getFile();
		try (FileInputStream fis = new FileInputStream(propsLocation)) {
			props.load(fis);
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
		}
		Class.forName("org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, props.getProperty("DB_DRIVER"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, props.getProperty("DB_USER"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, props.getProperty("DB_PASSWORD"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "sqllab");		
		Connection jdbcConn = DriverManager.getConnection(
				props.getProperty("DB_DRIVER"), props.getProperty("DB_USER"), props.getProperty("DB_PASSWORD"));
        conn = new DatabaseConnection(jdbcConn);
        conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
        daoFactory = new DAOFactoryImpl();
		QueryDataSet partialDataSet = new QueryDataSet(conn);
		partialDataSet.addTable("ORDER_WORKER", "SELECT * FROM sqllab.order_worker");
        dataSet = partialDataSet;
        xmlPath = this.getClass().getClassLoader().getResource("order_worker.xml").getFile();
	}
	
	protected void setUpDatabaseConfig(DatabaseConfig config) {
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
    }
	
	protected void setUp() throws Exception {
		FileOutputStream fos = new FileOutputStream(xmlPath);
		FlatXmlDataSet.write(dataSet, fos);
		fos.flush();
		fos.close();
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		FlatXmlDataSetBuilder f = new FlatXmlDataSetBuilder().setMetaDataSet(dataSet);
		FlatXmlDataSet ds = f.build(new FileInputStream(xmlPath));
		ds.endDataSet();
		return ds;
	}
	
	public void testOrderWorkerDAOAddsEntity() throws DataSetException, Exception {
		OrderInWorkDAO orderDAO = daoFactory.getOrderInWorkDAO();
		OrderInWork tOrder = orderDAO.create();
		WorkerDAO workerDAO = daoFactory.getWorkerDAO();
		Worker tWorker = workerDAO.create();
		OrderWorkerDAO orderWorkerDAO = daoFactory.getOrderWorkerDAO();
		OrderWorker tOrderWorker = new OrderWorker(tOrder.getId(), tWorker.getId(), "Y");
		tOrderWorker = orderWorkerDAO.createFrom(tOrderWorker);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_WORKER"});
		ITable dbTable = dbSet.getTable("ORDER_WORKER");
		Assert.assertEquals(BigInteger.valueOf((long) tOrderWorker.getId()), 
				dbTable.getValue(dbTable.getRowCount() - 1, "order_id"));
		Assert.assertEquals(tOrderWorker.getWorkerId(), 
				dbTable.getValue(dbTable.getRowCount() - 1, "worker_id"));
		Assert.assertEquals(tOrderWorker.getIsCompleted(), 
				dbTable.getValue(dbTable.getRowCount() - 1, "is_completed"));
	}
	
	public void testOrderWorkerDAODeletesEntity() throws DataSetException, Exception {
		OrderInWorkDAO orderDAO = daoFactory.getOrderInWorkDAO();
		OrderInWork tOrder = orderDAO.create();
		WorkerDAO workerDAO = daoFactory.getWorkerDAO();
		Worker tWorker = workerDAO.create();
		OrderWorkerDAO orderWorkerDAO = daoFactory.getOrderWorkerDAO();
		OrderWorker tOrderWorker = new OrderWorker(tOrder.getId(), tWorker.getId(), "Y");
		tOrderWorker = orderWorkerDAO.createFrom(tOrderWorker);
		orderWorkerDAO.delete(tOrderWorker);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_WORKER"});
		ITable dbTable = dbSet.getTable("ORDER_WORKER");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("ORDER_WORKER");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testOrderWorkerDAOUpdatesEntity() throws Exception {
		OrderWorkerDAO orderWorkerDAO = daoFactory.getOrderWorkerDAO();
		List<OrderWorker> orderWorkerList = orderWorkerDAO.getAll();
		OrderWorker tOrderWorker = orderWorkerList.get(orderWorkerList.size() - 1);
		tOrderWorker.setIsCompleted("N");
		orderWorkerDAO.update(tOrderWorker);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_WORKER"});
		ITable dbTable = dbSet.getTable("ORDER_WORKER");
		Assert.assertEquals(tOrderWorker.getIsCompleted(), 
				dbTable.getValue(dbTable.getRowCount() - 1, "is_completed"));
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

}
