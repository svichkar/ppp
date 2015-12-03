package com.nixsolutions.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
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
import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.entity.Part;

public class TestOrderPartDAO extends DBTestCase {
	
	public static Logger LOG = LogManager.getLogger(TestOrderPartDAO.class.getName());
	private IDatabaseConnection conn;
	private IDataSet dataSet;
	private DAOFactory daoFactory;
	private String xmlPath; 
	
	public TestOrderPartDAO(String name) throws SQLException, DatabaseUnitException, ClassNotFoundException {
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
		OrderInWorkDAO orderDAO = daoFactory.getOrderInWorkDAO();
		OrderInWork toCreateFrom = orderDAO.getByPK(1);
		OrderInWork o = new OrderInWork(0, toCreateFrom.getOrderStatus(), "test", toCreateFrom.getCar(),
				new Timestamp(new Date().getTime()), null);
		OrderInWork tOrder = orderDAO.createFrom(o);
		PartDAO partDAO = daoFactory.getPartDAO();
		Part p = new Part(0, "test", "test", 0);
		Part tPart = partDAO.createFrom(p);
		OrderPartDAO orderPartDAO = daoFactory.getOrderPartDAO();
		OrderPart tOrderPart = new OrderPart(0, tOrder, tPart, 0);
		tOrderPart = orderPartDAO.createFrom(tOrderPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_PART"});
		ITable dbTable = dbSet.getTable("ORDER_PART");
		Assert.assertEquals(BigInteger.valueOf((long) tOrderPart.getOrder().getOrderId()), 
				dbTable.getValue(dbTable.getRowCount() - 1, "order_id"));
		Assert.assertEquals(BigInteger.valueOf((long) tOrderPart.getPart().getPartId()), 
				dbTable.getValue(dbTable.getRowCount() - 1, "part_id"));
		Assert.assertEquals(BigInteger.valueOf((long) tOrderPart.getUsedAmount()), 
				dbTable.getValue(dbTable.getRowCount() - 1, "used_amount"));
	}
	
	public void testOrderPartDAODeletesEntity() throws DataSetException, Exception {
		OrderInWorkDAO orderDAO = daoFactory.getOrderInWorkDAO();
		OrderInWork toCreateFrom = orderDAO.getByPK(1);
		OrderInWork o = new OrderInWork(0, toCreateFrom.getOrderStatus(), "test", toCreateFrom.getCar(),
				new Timestamp(new Date().getTime()), null);
		OrderInWork tOrder = orderDAO.createFrom(o);
		PartDAO partDAO = daoFactory.getPartDAO();
		Part p = new Part(0, "test", "test", 0);
		Part tPart = partDAO.createFrom(p);
		OrderPartDAO orderPartDAO = daoFactory.getOrderPartDAO();
		OrderPart tOrderPart = new OrderPart(0, tOrder, tPart, 0);
		tOrderPart = orderPartDAO.createFrom(tOrderPart);
		orderPartDAO.delete(tOrderPart);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"ORDER_PART"});
		ITable dbTable = dbSet.getTable("ORDER_PART");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("ORDER_PART");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testOrderPartDAOUpdatesEntity() throws Exception {
		OrderPartDAO orderPartDAO = daoFactory.getOrderPartDAO();
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
