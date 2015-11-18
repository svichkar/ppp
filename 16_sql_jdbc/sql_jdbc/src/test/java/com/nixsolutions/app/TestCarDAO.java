package com.nixsolutions.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.Car;

public class TestCarDAO extends DBTestCase{
	
	public static Logger LOG = LogManager.getLogger(TestCarDAO.class.getName());
	private IDatabaseConnection conn;
	private IDataSet dataSet;
	private DAOFactory daoFactory;
	private String xmlPath; 

	public TestCarDAO(String name) throws SQLException, DatabaseUnitException, ClassNotFoundException {
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
		partialDataSet.addTable("CAR", "SELECT * FROM sqllab.car");
        dataSet = partialDataSet;
        xmlPath = this.getClass().getClassLoader().getResource("car.xml").getFile();
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
	
	public void testCarDAOAddsEntity() throws DataSetException, Exception {
		CarDAO carDAO = daoFactory.getCarDAO();
		Car tCar = carDAO.create();
		IDataSet dbSet = getConnection().createDataSet(new String[] {"CAR"});
		ITable dbTable = dbSet.getTable("CAR");
		Assert.assertEquals(tCar.getDescription(), dbTable.getValue(dbTable.getRowCount() - 1, "description"));
		Assert.assertEquals(tCar.getModel(), dbTable.getValue(dbTable.getRowCount() - 1, "model"));
		Assert.assertEquals(tCar.getVin(), dbTable.getValue(dbTable.getRowCount() - 1, "vin"));
		Assert.assertEquals(tCar.getCustomerId() == 0 ? null : tCar.getCustomerId(),
				dbTable.getValue(dbTable.getRowCount() - 1, "customer_id"));
	}
	
	public void testCarDAODeletesEntity() throws DataSetException, Exception {
		CarDAO carDAO = daoFactory.getCarDAO();
		Car tCar = carDAO.create();
		carDAO.delete(tCar);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"CAR"});
		ITable dbTable = dbSet.getTable("CAR");
		IDataSet xmlSet = getDataSet();
		ITable xmlTable = xmlSet.getTable("CAR");
		Assertion.assertEquals(xmlTable, dbTable);
	}
	
	public void testCarDAOUpdatesEntity() throws Exception {
		CarDAO carDAO = daoFactory.getCarDAO();
		List<Car> carList = carDAO.getAll();
		Car tCar = carList.get(carList.size() - 1);
		tCar.setDescription("No Description");
		carDAO.update(tCar);
		IDataSet dbSet = getConnection().createDataSet(new String[] {"CAR"});
		ITable dbTable = dbSet.getTable("CAR");
		Assert.assertEquals(tCar.getDescription(), dbTable.getValue(dbTable.getRowCount() - 1, "description"));
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}
}
