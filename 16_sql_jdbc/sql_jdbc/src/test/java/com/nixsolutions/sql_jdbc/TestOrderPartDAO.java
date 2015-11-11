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

import entities.Customer;
import h2.CustomerDAOImpl;
import h2.H2DAOFactoryImpl;

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
	
	public void testCustomerDAOAddsEntity() throws DataSetException, Exception {
		CustomerDAOImpl customerDAO = (CustomerDAOImpl) daoFactory.getDao(conn.getConnection(), Customer.class);
		Customer tCustomer = customerDAO.create();
		IDataSet dbSet = getConnection().createDataSet(new String[] {"CUSTOMER"});
		ITable dbTable = dbSet.getTable("CUSTOMER");
		Assert.assertEquals(tCustomer.getFirstName(), dbTable.getValue(dbTable.getRowCount() - 1, "first_name"));
		Assert.assertEquals(tCustomer.getLastName(), dbTable.getValue(dbTable.getRowCount() - 1, "last_name"));
		Assert.assertEquals(tCustomer.getPhone(), dbTable.getValue(dbTable.getRowCount() - 1, "phone"));
	}
	
	public void testCustomerDAODeletesEntity() throws DataSetException, Exception {
		CustomerDAOImpl customerDAO = (CustomerDAOImpl) daoFactory.getDao(conn.getConnection(), Customer.class);
		Customer tCustomer = customerDAO.create();
		customerDAO.delete(tCustomer);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"CUSTOMER"});
		ITable dbTable = dbSet.getTable("CUSTOMER");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("CUSTOMER");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testCustomerDAOUpdatesEntity() throws Exception {
		CustomerDAOImpl customerDAO = (CustomerDAOImpl) daoFactory.getDao(conn.getConnection(), Customer.class);
		List<Customer> customerList = customerDAO.getAll();
		Customer tCustomer = customerList.get(customerList.size() - 1);
		tCustomer.setPhone("No Phone");
		customerDAO.update(tCustomer);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"CUSTOMER"});
		ITable dbTable = dbSet.getTable("CUSTOMER");
		Assert.assertEquals(tCustomer.getPhone(), dbTable.getValue(dbTable.getRowCount() - 1, "phone"));
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

}
