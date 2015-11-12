package com.nixsolutions.sql_jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
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

import entities.OrderInWork;
import entities.OrderPart;
import entities.Part;
import h2.H2DAOFactoryImpl;
import h2.OrderInWorkDAOImpl;
import h2.OrderPartDAOImpl;
import h2.PartDAOImpl;

public class TestOrderPartDAO extends DBTestCase {
	
	private IDatabaseConnection conn;
	private IDataSet dataSet;
	private H2DAOFactoryImpl daoFactory;
	private String xmlPath; 
	
	public TestOrderPartDAO(String name) throws SQLException, DatabaseUnitException, ClassNotFoundException {
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
		partialDataSet.addTable("ORDER_PART", "SELECT * FROM sqllab.order_part");
        dataSet = partialDataSet;
        xmlPath = this.getClass().getClassLoader().getResource("order_part.xml").getFile();
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
	
	public void testOrderPartDAOAddsEntity() throws DataSetException, Exception {
		OrderInWorkDAOImpl orderDAO = (OrderInWorkDAOImpl) daoFactory.getDao(conn.getConnection(), OrderInWork.class);
		OrderInWork tOrder = orderDAO.create();
		PartDAOImpl partDAO = (PartDAOImpl) daoFactory.getDao(conn.getConnection(), Part.class);
		Part tPart = partDAO.create();
		OrderPartDAOImpl orderPartDAO = (OrderPartDAOImpl) daoFactory.getDao(conn.getConnection(), OrderPart.class);
		OrderPart tOrderPart = new OrderPart(tOrder.getId(), tPart.getId(), 0);
		tOrderPart = orderPartDAO.createFrom(tOrderPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_PART"});
		ITable dbTable = dbSet.getTable("ORDER_PART");
		Assert.assertEquals(BigInteger.valueOf((long) tOrderPart.getId()), 
				dbTable.getValue(dbTable.getRowCount() - 1, "order_id"));
		Assert.assertEquals(BigInteger.valueOf((long) tOrderPart.getPartId()), 
				dbTable.getValue(dbTable.getRowCount() - 1, "part_id"));
		Assert.assertEquals(BigInteger.valueOf((long) tOrderPart.getUsedAmount()), 
				dbTable.getValue(dbTable.getRowCount() - 1, "used_amount"));
	}
	
	public void testOrderPartDAODeletesEntity() throws DataSetException, Exception {
		OrderInWorkDAOImpl orderDAO = (OrderInWorkDAOImpl) daoFactory.getDao(conn.getConnection(), OrderInWork.class);
		OrderInWork tOrder = orderDAO.create();
		PartDAOImpl partDAO = (PartDAOImpl) daoFactory.getDao(conn.getConnection(), Part.class);
		Part tPart = partDAO.create();
		OrderPartDAOImpl orderPartDAO = (OrderPartDAOImpl) daoFactory.getDao(conn.getConnection(), OrderPart.class);
		OrderPart tOrderPart = new OrderPart(tOrder.getId(), tPart.getId(), 0);
		tOrderPart = orderPartDAO.createFrom(tOrderPart);
		orderPartDAO.delete(tOrderPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_PART"});
		ITable dbTable = dbSet.getTable("ORDER_PART");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("ORDER_PART");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testOrderPartDAOUpdatesEntity() throws Exception {
		OrderPartDAOImpl orderPartDAO = (OrderPartDAOImpl) daoFactory.getDao(conn.getConnection(), OrderPart.class);
		List<OrderPart> orderPartList = orderPartDAO.getAll();
		OrderPart tOrderPart = orderPartList.get(orderPartList.size() - 1);
		tOrderPart.setUsedAmount(30);
		orderPartDAO.update(tOrderPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_PART"});
		ITable dbTable = dbSet.getTable("ORDER_PART");
		Assert.assertEquals(BigInteger.valueOf((long) tOrderPart.getUsedAmount()), 
				dbTable.getValue(dbTable.getRowCount() - 1, "used_amount"));
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

}
