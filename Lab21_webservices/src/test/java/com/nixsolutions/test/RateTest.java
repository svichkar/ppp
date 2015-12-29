package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.RateDao;
import com.nixsolutions.entity.Rate;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

public class RateTest extends DBUnitConfig {
	@Autowired
	private RateDao rateDao;

	public RateTest() throws SQLException, ClassNotFoundException {
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Rate/Rate.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
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
		Rate rate = new Rate();
		rate.setRateValue("X");
		rateDao.create(rate);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Rate/RateCreate.xml"));
		ITable expectedTable = expectedData.getTable("rate");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("rate");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "rate_id" });
	}

	@Test
	public void testDelete() throws Exception {
        Rate rate = rateDao.getByRateId(1);
        rateDao.delete(rate);
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new FileInputStream("src/test/resources/Rate/RateDelete.xml"));
        ITable expectedTable = expectedData.getTable("rate");
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTale = actualData.getTable("rate");
        Assertion.assertEquals(expectedTable, actualTale);
    }

	@Test
	public void testUpdate() throws Exception {
        Rate rate = rateDao.getByRateId(1);
        rate.setRateValue("Z");
		rateDao.update(rate);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Rate/RateUpdate.xml"));
		ITable expectedTable = expectedData.getTable("rate");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("rate");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
        Rate rateExpected = new Rate();
        rateExpected.setId(5);
        rateExpected.setRateValue("A");
		Rate rateActual =  rateDao.getByRateId(5);
		Assert.assertEquals(rateExpected.getRateValue(), rateActual.getRateValue());
		Assert.assertEquals(rateExpected.getId(), rateActual.getId());
	}
	
	@Test
	public void testGetByRateValue() throws Exception {
        Rate rateExpected = new Rate();
        rateExpected.setId(4);
        rateExpected.setRateValue("B");
		Rate rateActual =  rateDao.getByRateValue("B");
		Assert.assertEquals(rateExpected.getRateValue(), rateActual.getRateValue());
		Assert.assertEquals(rateExpected.getId(), rateActual.getId());
	}
}
