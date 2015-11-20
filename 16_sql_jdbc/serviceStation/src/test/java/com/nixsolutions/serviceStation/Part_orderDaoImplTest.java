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
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.impl.PartOrderDaoImpl;
import com.nixsolutions.entity.PartOrder;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class Part_orderDaoImplTest {
	private final static Logger logger = LogManager.getLogger();

	protected DataSource dataSource;
	private IDatabaseTester tester;
	private IDataSet beforeData;

	@Before
	public void init() throws Exception {
		Properties properties = ConnectionManager.getProperties();
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, properties.getProperty("h2URL"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, properties.getProperty("h2Login"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, properties.getProperty("h2Password"));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "sqllab");
		// insert data into db
		CommonTestsMethods.updateDB();
		CommonTestsMethods.getTables("part_order");
		tester = new JdbcDatabaseTester("org.h2.Driver", properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"), "sqllab");
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/part_order/part_order.xml"));
		tester.setDataSet(beforeData);
	}

	@After
	public void deletePart_orderFile() {
		new File("src/test/resources/part_order/part_order.xml").delete();
	}

	@Test
	public void createPart_orderTable() throws SQLException, Exception {
		PartOrderDaoImpl part_orderDao = DaoFactory.getPartOrderDaoImpl();
		part_orderDao.deleteTableWithAllData();
		part_orderDao.createNewTable();
		part_orderDao.setPartToOrder(1, 2, 3);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("part_order");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(new File("src/test/resources/part_order/part_order_newTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("part_order");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test
	public void updatePart_orderTableTest() throws SQLException, Exception {
		PartOrderDaoImpl part_orderDao = DaoFactory.getPartOrderDaoImpl();
		PartOrder partOrder = part_orderDao.getAllParts().get(0);
		part_orderDao.deletePartFromOrder(2, 3);
		// CarDaoImplTest.getTables();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/part_order/part_order_update.xml"));
		IDataSet actualData = tester.getConnection().createDataSet();
		String[] ignoredColumn = { "part_id" };
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "part_order", ignoredColumn);
	}

	@Test
	public void addingNewPart_order() throws SQLException, Exception {
		PartOrderDaoImpl part_orderDao = DaoFactory.getPartOrderDaoImpl();
		part_orderDao.setPartToOrder(1, 2, 3);

		// Fetch database data after executing your code
		IDataSet databaseDataSet = tester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("part_order");

		// Load expected data from an XML dataset
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		IDataSet expectedDataSet = flatXmlProducer
				.build(new File("src/test/resources/part_order/part_order_newpart_order.xml"));
		ITable expectedTable = expectedDataSet.getTable("part_order");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}
}
