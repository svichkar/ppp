package com.nixsolutions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.RateDao;
import com.nixsolutions.entities.Rate;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import config.DBUnitConfig;

public class RateTest extends DBUnitConfig {
	private RateDao rateDao;

	public RateTest(String name) throws SQLException, ClassNotFoundException {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Rate/Rate.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
		rateDao = daoFactory.getRateDao(conn);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Rate> rates = rateDao.getAll();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Rate/Rate.xml"));
		ITable expectedTable = expectedData.getTable("rate");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("rate");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("rate").getRowCount(), rates.size());
	}

	@Test
	public void testCreate() throws Exception {
		rateDao.create('X');
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Rate/RateCreate.xml"));
		ITable expectedTable = expectedData.getTable("rate");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("rate");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "rate_id" });
	}

	@Test
	public void testDelete() throws Exception {
		rateDao.delete(new Rate(1, 'E'));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Rate/RateDelete.xml"));
		ITable expectedTable = expectedData.getTable("rate");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("rate");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
		rateDao.update(new Rate(1, 'Z'));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Rate/RateUpdate.xml"));
		ITable expectedTable = expectedData.getTable("rate");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("rate");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
		Rate rateExpected = new Rate(5, 'A');
		Rate rateActual =  rateDao.getByRateId(5);
		Assert.assertEquals(rateExpected.getRateValue(), rateActual.getRateValue());
		Assert.assertEquals(rateExpected.getId(), rateActual.getId());
	}
	
	@Test
	public void testGetByRateValue() throws Exception {
		Rate rateExpected = new Rate(4, 'B');
		Rate rateActual =  rateDao.getByRateValue('B');
		Assert.assertEquals(rateExpected.getRateValue(), rateActual.getRateValue());
		Assert.assertEquals(rateExpected.getId(), rateActual.getId());
	}
}
