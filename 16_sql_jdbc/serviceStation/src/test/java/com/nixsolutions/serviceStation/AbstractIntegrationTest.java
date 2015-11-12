/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.h2Objects.CarDaoImpl;
import com.nixsolutions.serviceStation.h2Objects.ServiceFactory;

/**
 * @author mixeyes
 *
 */
public class AbstractIntegrationTest {
	private final static Logger logger = LogManager.getLogger();

	protected DataSource dataSource;
	private IDatabaseTester tester;
	private IDataSet beforeData;
	private ServiceFactory factory;

	@Before
	public void init() throws Exception {
		// insert data into db
		Properties properties = DbConnector.getProperties();
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer= new FlatXmlDataSetBuilder();
				flatXmlProducer.setColumnSensing(true);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/dataset.xml"));
		tester.setDataSet(beforeData);
		//tester.onSetup();
		factory = new ServiceFactory();
	}

	@Test
	public void test() throws SQLException, Exception {
		try {
			CarDaoImpl carDao = factory.getCarDao();
			Car car = carDao.getAllCar().get(1);
			car.setDescription("AX777-99DE have no catalisator");
			carDao.updateCarByCustomerName(car.getModel(), car.getDescription(),
					car.getCustomer_id());
			IDataSet expectedData = new FlatXmlDataSetBuilder()
					.build(new FileInputStream("src/test/resources/dataset.xml"));
			IDataSet actualData = tester.getConnection().createDataSet();
			String[] ignore = { "car_id" };
			Assertion.assertEqualsIgnoreCols(expectedData, actualData, "sqllab.car", ignore);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	@Test
	public void test2() throws SQLException, Exception{
	    // Fetch database data after executing your code
        IDataSet databaseDataSet = tester.getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("car");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, actualTable);
	}

}
