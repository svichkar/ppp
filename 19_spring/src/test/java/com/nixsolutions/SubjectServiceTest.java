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

import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.service.SubjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration("classpath:test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("/dbunit/subject/subjectInit.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/subject/subjectInit.xml")
public class SubjectServiceTest {
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private IDatabaseTester databaseTester;

	@Test
	@ExpectedDatabase(value = "/dbunit/subject/subjectCreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "subject")
	public void shouldCreateSubject() {
		Long subjectId = subjectService.createSubject("Philosophy", Long.valueOf(1));
		
	}
	
	@Test
	@ExpectedDatabase(value = "/dbunit/subject/subjectUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "subject")
	public void shouldUpdateSubject() {
        subjectService.updateSubject(Long.valueOf(3), "Philosophy", Long.valueOf(1));
	}

	@Test
	@ExpectedDatabase(value = "/dbunit/subject/subjectDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "subject")
	public void shouldDeleteSubject() {
        subjectService.deleteSubject(Long.valueOf(1));
	}

	@Test
	public void shouldGetAllSubjects() throws SQLException, Exception {	        
		List<Subject> subjects = subjectService.findAllSubjects();
		IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/subject/subjectInit.xml"));
		ITable expected = ds.getTable("subject");
		ITable actual = databaseTester.getConnection().createTable("subject");
		Assertion.assertEquals(expected, actual);
		Assert.assertEquals(expected.getRowCount(), subjects.size());
	}

	@Test
	public void shouldFindSubjectById() throws DatabaseUnitException, SQLException, Exception {
        Subject subjectTest = subjectService.findSubjectById(Long.valueOf(2));
        IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/subject/subjectFindById.xml"));
        ITable expected = ds.getTable("subject");
        String sqlQuery = String.format("SELECT * FROM subject WHERE subject_id = %d", Long.valueOf(2));
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expected, databaseTester.getConnection(), "subject", sqlQuery, ignore);
        Assert.assertEquals(expected.getValue(0, "subject_name"), subjectTest.getSubjectName());
	}

}
