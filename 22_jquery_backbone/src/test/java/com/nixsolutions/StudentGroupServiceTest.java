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

import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.service.StudentGroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration("classpath:test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("/dbunit/group/groupInit.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/group/groupInit.xml")
public class StudentGroupServiceTest {
	@Autowired
	private StudentGroupService groupService;

	@Autowired
	private IDatabaseTester databaseTester;

	@Test
	@ExpectedDatabase(value = "/dbunit/group/groupCreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "student_group")
	public void shouldCreateStudentGroup() {
		StudentGroup groupNew = new StudentGroup();
		groupNew.setGroupName("PI-2016");
		groupService.createStudentGroup(groupNew);
	}
	
	@Test
	@ExpectedDatabase(value = "/dbunit/group/groupUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "student_group")
	public void shouldUpdateStudentGroup() {
        StudentGroup groupUpdate = groupService.findStudentGroupById(Long.valueOf(3));
        groupUpdate.setGroupName("KI-2016");
        groupService.updateStudentGroup(groupUpdate);
	}

	@Test
	@ExpectedDatabase(value = "/dbunit/group/groupDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "student_group")
	public void shouldDeleteStudentGroup() {
        groupService.deleteStudentGroup(groupService.findStudentGroupById(Long.valueOf(2)));
	}

	@Test
	public void shouldGetAllStudentGroups() throws SQLException, Exception {	        
		List<StudentGroup> groups = groupService.findAllStudentGroups();
		IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/group/groupInit.xml"));
		ITable expected = ds.getTable("student_group");
		ITable actual = databaseTester.getConnection().createTable("student_group");
		Assertion.assertEquals(expected, actual);
		Assert.assertEquals(expected.getRowCount(), groups.size());
	}

	@Test
	public void shouldFindStudentGroupById() throws DatabaseUnitException, SQLException, Exception {
        StudentGroup groupTest = groupService.findStudentGroupById(Long.valueOf(3));
        IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/group/groupFindById.xml"));
        ITable expected = ds.getTable("student_group");
        String sqlQuery = String.format("SELECT * FROM student_group WHERE group_id = %d", Long.valueOf(3));
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expected, databaseTester.getConnection(), "student_group", sqlQuery, ignore);
        Assert.assertEquals(expected.getValue(0, "group_name"), groupTest.getGroupName());
	}

}
