package com.nixsolutions.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.Part;

public class TestPartDAO extends DBTestCase {
	
	public static Logger LOG = LogManager.getLogger(TestPartDAO.class.getName());
	private IDatabaseConnection conn;
	private IDataSet dataSet;
	private DAOFactory daoFactory;
	private String xmlPath; 
	
	public TestPartDAO(String name) throws SQLException, DatabaseUnitException, ClassNotFoundException {
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
		partialDataSet.addTable("PART", "SELECT * FROM sqllab.part");
        dataSet = partialDataSet;
        xmlPath = this.getClass().getClassLoader().getResource("part.xml").getFile();
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
	
	public void testPartDAOAddsEntity() throws DataSetException, Exception {
		PartDAO partDAO = daoFactory.getPartDAO();
		Part p = new Part(0, "test", "test", 0);
		Part tPart = partDAO.createFrom(p);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"PART"});
		ITable dbTable = dbSet.getTable("PART");
		Assert.assertEquals(BigInteger.valueOf(tPart.getAmount()), dbTable.getValue(dbTable.getRowCount() - 1, "amount"));
		Assert.assertEquals(tPart.getPartName(), dbTable.getValue(dbTable.getRowCount() - 1, "part_name"));
		Assert.assertEquals(tPart.getVendor(), dbTable.getValue(dbTable.getRowCount() - 1, "vendor"));
	}
	
	public void testPartDAODeletesEntity() throws DataSetException, Exception {
		PartDAO partDAO = daoFactory.getPartDAO();
		Part p = new Part(0, "test", "test", 0);
		Part tPart = partDAO.createFrom(p);
		partDAO.delete(tPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"PART"});
		ITable dbTable = dbSet.getTable("PART");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("PART");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testPartDAOUpdatesEntity() throws Exception {
		PartDAO partDAO = daoFactory.getPartDAO();
		List<Part> partList = partDAO.getAll();
		Part tPart = partList.get(partList.size() - 1);
		tPart.setPartName("No Part Name");
		partDAO.update(tPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"PART"});
		ITable dbTable = dbSet.getTable("PART");
		Assert.assertEquals(tPart.getPartName(), dbTable.getValue(dbTable.getRowCount() - 1, "part_name"));
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}
}
