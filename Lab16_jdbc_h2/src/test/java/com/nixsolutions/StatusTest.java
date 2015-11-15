package com.nixsolutions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.entities.Status;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import config.DBUnitConfig;

public class StatusTest extends DBUnitConfig {
	private StatusDao statusDao;

	public StatusTest(String name) throws SQLException, ClassNotFoundException {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Status/Status.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
		statusDao = daoFactory.getStatusDao(conn);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Status> statuss = statusDao.getAll();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Status/Status.xml"));
		ITable expectedTable = expectedData.getTable("status");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("status");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("status").getRowCount(), statuss.size());
	}

	@Test
	public void testCreate() throws Exception {
		statusDao.create("Test");
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Status/StatusCreate.xml"));
		ITable expectedTable = expectedData.getTable("status");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("status");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "status_id" });
	}

	@Test
	public void testDelete() throws Exception {
		statusDao.delete(new Status(1, "Active"));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Status/StatusDelete.xml"));
		ITable expectedTable = expectedData.getTable("status");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("status");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
		statusDao.update(new Status(2, "Test"));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Status/StatusUpdate.xml"));
		ITable expectedTable = expectedData.getTable("status");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("status");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
		Status statusExpected = new Status(1, "Active");
		Status statusActual =  statusDao.getByStatusId(1);
		Assert.assertEquals(statusExpected.getStatusName(), statusActual.getStatusName());
		Assert.assertEquals(statusExpected.getId(), statusActual.getId());
	}
	
	@Test
	public void testGetByStatusName() throws Exception {
		Status statusExpected = new Status(2, "Vacation");
		Status statusActual =  statusDao.getByStatusName("Vacation");
		Assert.assertEquals(statusExpected.getStatusName(), statusActual.getStatusName());
		Assert.assertEquals(statusExpected.getId(), statusActual.getId());
	}
}
