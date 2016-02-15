package com.nixsolutions;

import java.sql.SQLException;
import java.util.List;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.service.StatusService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration("classpath:test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("/dbunit/status/statusInit.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/status/statusInit.xml")
public class StatusServiceTest {
	@Autowired
	private StatusService statusService;

	@Autowired
	private IDatabaseTester databaseTester;

	@Test
	@ExpectedDatabase(value = "/dbunit/status/statusCreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "status")
	public void shouldCreateStatus() {
		Status statusNew = new Status();
		statusNew.setStatusName("Non-active");
		statusService.createStatus(statusNew);
	}
	
	@Test
	@ExpectedDatabase(value = "/dbunit/status/statusUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "status")
	public void shouldUpdateStatus() {
        Status statusUpdate = statusService.findStatusById(Long.valueOf(3));
        statusUpdate.setStatusName("Non-active");
        statusService.updateStatus(statusUpdate);
	}

	@Test
	@ExpectedDatabase(value = "/dbunit/status/statusDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "status")
	public void shouldDeleteStatus() {
        statusService.deleteStatus(statusService.findStatusById(Long.valueOf(3)));
	}

	@Test
	public void shouldGetAllStatuss() throws SQLException, Exception {	        
		List<Status> statuss = statusService.findAllStatuses();
		IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/status/statusInit.xml"));
		ITable expected = ds.getTable("status");
		ITable actual = databaseTester.getConnection().createTable("status");
		Assertion.assertEquals(expected, actual);
		Assert.assertEquals(expected.getRowCount(), statuss.size());
	}

	@Test
	public void shouldFindStatusById() throws DatabaseUnitException, SQLException, Exception {
        Status statusTest = statusService.findStatusById(Long.valueOf(3));
        IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/status/statusFindById.xml"));
        ITable expected = ds.getTable("status");
        String sqlQuery = String.format("SELECT * FROM status WHERE status_id = %d", Long.valueOf(3));
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expected, databaseTester.getConnection(), "status", sqlQuery, ignore);
        Assert.assertEquals(expected.getValue(0, "status_name"), statusTest.getStatusName());
	}

}
