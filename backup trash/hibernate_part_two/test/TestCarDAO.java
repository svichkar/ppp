package com.nixsolutions.test;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.hibernate.entity.Car;

public class TestCarDAO extends DBUnitConfig {
	
	public static Logger LOG = LogManager.getLogger(TestCarDAO.class.getName());
	private CarDAO carDao;
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/Car/car.xml"));
	}
	
	public TestCarDAO(String name) throws ClassNotFoundException, SQLException
    {
        super(name);
    }
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(true);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Car/Car.xml"));
		tester.setDataSet(beforeData);
		//tester.onSetup();
		carDao = daoFactory.getCarDAO();
	}
	
	@Test
	public void testGetAll() throws Exception {
		List<Car> cars = carDao.getAll();
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTable = actualData.getTable("CAR");
		FlatXmlDataSetBuilder fx = new FlatXmlDataSetBuilder().setColumnSensing(true);
		IDataSet expectedData = fx.build(new FileInputStream("src/test/resources/Car/Car.xml"));
		ITable expectedTable = expectedData.getTable("CAR");
		Assertion.assertEquals(expectedTable, actualTable);
		Assert.assertEquals(expectedData.getTable("CAR").getRowCount(), cars.size());
	}
	
	@Test
	public void testAdd() throws Exception {
		Car used = carDao.getByPK(1);
		Car c = new Car(0, "test", "test", "test", used.getCustomer());
		carDao.createFrom(c);
		IDataSet expectedData = new FlatXmlDataSetBuilder().setColumnSensing(true)
				.build(new FileInputStream("src/test/resources/Car/CarCreate.xml"));
		ITable expectedTable = expectedData.getTable("CAR");
		IDatabaseConnection conn = tester.getConnection();
		conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		IDataSet actualData = conn.createDataSet();
		ITable actualTale = actualData.getTable("CAR");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "CAR_ID" });
	}
}
