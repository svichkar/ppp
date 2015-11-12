package com.nixsolutions.sql_jdbc;

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

import entities.OrderStatus;
import h2.H2DAOFactoryImpl;
import h2.OrderStatusDAOImpl;

public class TestOrderStatusDAO extends DBTestCase{
	
	private IDatabaseConnection conn;
	private IDataSet dataSet;
	private H2DAOFactoryImpl daoFactory;
	private String xmlPath; 
	
	public TestOrderStatusDAO(String name) throws SQLException, DatabaseUnitException, ClassNotFoundException {
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
		partialDataSet.addTable("ORDER_STATUS", "SELECT * FROM sqllab.order_status");
        dataSet = partialDataSet;
        xmlPath = this.getClass().getClassLoader().getResource("order_status.xml").getFile();
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
	
	public void testOrderStatusDAOAddsEntity() throws DataSetException, Exception {
		OrderStatusDAOImpl orderStatusDAO = (OrderStatusDAOImpl) daoFactory.getDao(conn.getConnection(), OrderStatus.class);
		OrderStatus tOrderStatus = orderStatusDAO.create();
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_STATUS"});
		ITable dbTable = dbSet.getTable("ORDER_STATUS");
		Assert.assertEquals(tOrderStatus.getId(), dbTable.getValue(dbTable.getRowCount() - 1, "order_status_id"));
		Assert.assertEquals(tOrderStatus.getOrderStatusName(), dbTable.getValue(dbTable.getRowCount() - 1, "order_status_name"));
	}
	
	public void testOrderStatusDAODeletesEntity() throws DataSetException, Exception {
		OrderStatusDAOImpl orderStatusDAO = (OrderStatusDAOImpl) daoFactory.getDao(conn.getConnection(), OrderStatus.class);
		OrderStatus tOrderStatus = orderStatusDAO.create();
		orderStatusDAO.delete(tOrderStatus);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_STATUS"});
		ITable dbTable = dbSet.getTable("ORDER_STATUS");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("ORDER_STATUS");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testOrderStatusDAOUpdatesEntity() throws Exception {
		OrderStatusDAOImpl orderStatusDAO = (OrderStatusDAOImpl) daoFactory.getDao(conn.getConnection(), OrderStatus.class);
		List<OrderStatus> orderStatusList = orderStatusDAO.getAll();
		OrderStatus tOrderStatus = orderStatusList.get(orderStatusList.size() - 1);
		tOrderStatus.setOrderStatusName("No Status");
		orderStatusDAO.update(tOrderStatus);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_STATUS"});
		ITable dbTable = dbSet.getTable("ORDER_STATUS");
		Assert.assertEquals(tOrderStatus.getOrderStatusName(), dbTable.getValue(dbTable.getRowCount() - 1, "order_status_name"));
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

}
