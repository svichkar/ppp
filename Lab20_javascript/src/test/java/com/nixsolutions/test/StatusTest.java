package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.entity.Status;
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

public class StatusTest extends DBUnitConfig {
	@Autowired
	private StatusDao statusDao;

	public StatusTest() throws SQLException, ClassNotFoundException {
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Status/Status.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
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
		Status status = new Status();
        status.setStatusName("Test");
		statusDao.create(status);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Status/StatusCreate.xml"));
		ITable expectedTable = expectedData.getTable("status");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("status");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "status_id" });
	}

	@Test
	public void testDelete() throws Exception {
        Status status = statusDao.getByStatusId(4);
		statusDao.delete(status);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Status/StatusDelete.xml"));
		ITable expectedTable = expectedData.getTable("status");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("status");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
        Status status = statusDao.getByStatusId(2);
        status.setStatusName("Test");
        statusDao.update(status);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Status/StatusUpdate.xml"));
		ITable expectedTable = expectedData.getTable("status");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("status");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
        Status statusExpected = new Status();
        statusExpected.setId(1);
        statusExpected.setStatusName("Active");
		//Status statusExpected = new Status(1, "Active");
		Status statusActual =  statusDao.getByStatusId(1);
		Assert.assertEquals(statusExpected.getStatusName(), statusActual.getStatusName());
		Assert.assertEquals(statusExpected.getId(), statusActual.getId());
	}
	
	@Test
	public void testGetByStatusName() throws Exception {
        Status statusExpected = new Status();
        statusExpected.setId(2);
        statusExpected.setStatusName("Vacation");
		//Status statusExpected = new Status(2, "Vacation");
		Status statusActual =  statusDao.getByStatusName("Vacation");
		Assert.assertEquals(statusExpected.getStatusName(), statusActual.getStatusName());
		Assert.assertEquals(statusExpected.getId(), statusActual.getId());
	}
}
