package com.nixsolutions.sql_jdbc;

import java.io.File;
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

import entities.OrderPart;
import h2.H2DAOFactoryImpl;
import h2.OrderPartDAOImpl;

public class TestOrderPartDAO extends DBTestCase {
	
	private IDatabaseConnection conn;
	private IDataSet dataSet;
	private H2DAOFactoryImpl daoFactory;
	private static final String CURRENT_SEPARATOR = File.separator;
	private static final String XML_PATH = System.getProperty("user.dir") + CURRENT_SEPARATOR + "src" + CURRENT_SEPARATOR + 
			"test" + CURRENT_SEPARATOR + "resources" + CURRENT_SEPARATOR + "customer.xml";
	
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
	
	public void testOrderPartDAOAddsEntity() throws DataSetException, Exception {
		OrderPartDAOImpl orderPartDAO = (OrderPartDAOImpl) daoFactory.getDao(conn.getConnection(), OrderPart.class);
		OrderPart tOrderPart = new OrderPart(1, 1, 0);
		tOrderPart = orderPartDAO.createFrom(tOrderPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_PART"});
		ITable dbTable = dbSet.getTable("ORDER_PART");
		Assert.assertEquals(tOrderPart.getId(), dbTable.getValue(dbTable.getRowCount() - 1, "order_id"));
		Assert.assertEquals(tOrderPart.getPartId(), dbTable.getValue(dbTable.getRowCount() - 1, "part_id"));
		Assert.assertEquals(BigInteger.valueOf((long) tOrderPart.getUsedAmount()), dbTable.getValue(dbTable.getRowCount() - 1, "used_amount"));
	}
	
	public void testCustomerDAODeletesEntity() throws DataSetException, Exception {
		OrderPartDAOImpl orderPartDAO = (OrderPartDAOImpl) daoFactory.getDao(conn.getConnection(), OrderPart.class);
		OrderPart tOrderPart = new OrderPart(1, 1, 10);
		tOrderPart = orderPartDAO.createFrom(tOrderPart);
		orderPartDAO.delete(tOrderPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_PART"});
		ITable dbTable = dbSet.getTable("ORDER_PART");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("ORDER_PART");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testCustomerDAOUpdatesEntity() throws Exception {
		OrderPartDAOImpl orderPartDAO = (OrderPartDAOImpl) daoFactory.getDao(conn.getConnection(), OrderPart.class);
		List<OrderPart> orderPartList = orderPartDAO.getAll();
		OrderPart tOrderPart = orderPartList.get(orderPartList.size() - 1);
		tOrderPart.setUsedAmount(30);
		orderPartDAO.update(tOrderPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_PART"});
		ITable dbTable = dbSet.getTable("ORDER_PART");
		Assert.assertEquals(BigInteger.valueOf((long) tOrderPart.getUsedAmount()), dbTable.getValue(dbTable.getRowCount() - 1, "used_amount"));
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

}
