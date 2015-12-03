package com.nixsolutions.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.OrderInWork;

public class TestOrderDAO extends DBTestCase {
	
	public static Logger LOG = LogManager.getLogger(TestOrderDAO.class.getName());
	private IDatabaseConnection conn;
	private IDataSet dataSet;
	private DAOFactory daoFactory;
	private String xmlPath;
	
	public TestOrderDAO(String name) throws SQLException, DatabaseUnitException, ClassNotFoundException {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:tcp://localhost/~/sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "sqllab");		
		Connection jdbcConn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/sqllab", "sa", "");
        conn = new DatabaseConnection(jdbcConn);
        conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
        daoFactory = new DAOFactoryImpl();
		QueryDataSet partialDataSet = new QueryDataSet(conn);
		partialDataSet.addTable("ORDER_IN_WORK", "SELECT * FROM sqllab.order_in_work");
        dataSet = partialDataSet;
        xmlPath = this.getClass().getClassLoader().getResource("order.xml").getFile();
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
	
	public void testOrderDAOAddsEntity() throws DataSetException, Exception {
		OrderInWorkDAO orderDAO = daoFactory.getOrderInWorkDAO();
		OrderInWork toCreateFrom = orderDAO.getByPK(1);
		OrderInWork o = new OrderInWork(0, toCreateFrom.getOrderStatus(), "test", toCreateFrom.getCar(),
				new Timestamp(new Date().getTime()), null);
		OrderInWork tOrder = orderDAO.createFrom(o);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_IN_WORK"});
		ITable dbTable = dbSet.getTable("ORDER_IN_WORK");
		Assert.assertEquals(tOrder.getDescription(), dbTable.getValue(dbTable.getRowCount() - 1, "description"));
		Assert.assertEquals(tOrder.getCar().getCarId(),
				dbTable.getValue(dbTable.getRowCount() - 1, "car_id"));
		Assert.assertEquals(tOrder.getOrderStatus().getOrderStatusId(), 
				dbTable.getValue(dbTable.getRowCount() - 1, "order_status_id"));
		Assert.assertEquals(tOrder.getTimestampFinish(), dbTable.getValue(dbTable.getRowCount() - 1, "timestamp_finish"));
		Assert.assertEquals(tOrder.getTimestampStart(), dbTable.getValue(dbTable.getRowCount() - 1, "timestamp_start"));
	}
	
	public void testOrderDAODeletesEntity() throws DataSetException, Exception {
		OrderInWorkDAO orderDAO = daoFactory.getOrderInWorkDAO();
		OrderInWork toCreateFrom = orderDAO.getByPK(1);
		OrderInWork o = new OrderInWork(0, toCreateFrom.getOrderStatus(), "test", toCreateFrom.getCar(),
				new Timestamp(new Date().getTime()), null);
		OrderInWork tOrder = orderDAO.createFrom(o);
		orderDAO.delete(tOrder);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_IN_WORK"});
		ITable dbTable = dbSet.getTable("ORDER_IN_WORK");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("ORDER_IN_WORK");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testOrderDAOUpdatesEntity() throws Exception {
		OrderInWorkDAO orderDAO = daoFactory.getOrderInWorkDAO();
		List<OrderInWork> orderList = orderDAO.getAll();
		OrderInWork tOrder = orderList.get(orderList.size() - 1);
		tOrder.setDescription("No Description");
		orderDAO.update(tOrder);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_IN_WORK"});
		ITable dbTable = dbSet.getTable("ORDER_IN_WORK");
		Assert.assertEquals(tOrder.getDescription(), dbTable.getValue(dbTable.getRowCount() - 1, "description"));
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}
}
