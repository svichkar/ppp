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

import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.service.JournalService;


@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration("classpath:test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("/dbunit/journal/journalInit.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/journal/journalInit.xml")
public class JournalServiceTest {
	@Autowired
	private JournalService journalService;	

	@Autowired
	private IDatabaseTester databaseTester;

	@Test
	@ExpectedDatabase(value = "/dbunit/journal/journalCreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "journal")
	public void shouldCreateJournal() {
		Long journalId = journalService.createJournal(Long.valueOf(3), Long.valueOf(3), Long.valueOf(5));		 
	}
	
	@Test
	@ExpectedDatabase(value = "/dbunit/journal/journalUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "journal")
	public void shouldUpdateJournal() {
        journalService.updateJournal(Long.valueOf(5), Long.valueOf(2), Long.valueOf(2), Long.valueOf(4));
	}

	@Test
	@ExpectedDatabase(value = "/dbunit/journal/journalDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "journal")
	public void shouldDeleteJournal() {
		journalService.deleteJournal(Long.valueOf(4));
	}

	@Test
	public void shouldGetAllJournals() throws SQLException, Exception {	        
		List<Journal> journals = journalService.findAllJournals();
		IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/journal/journalInit.xml"));
		ITable expected = ds.getTable("journal");
		ITable actual = databaseTester.getConnection().createTable("journal");
		Assertion.assertEquals(expected, actual);
		Assert.assertEquals(expected.getRowCount(), journals.size());
	}

	@Test
	public void shouldFindJournalById() throws DatabaseUnitException, SQLException, Exception {
		Journal journalTest = journalService.findJournalById(Long.valueOf(5));
        IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/journal/journalFindById.xml"));
        ITable expected = ds.getTable("journal");
        String sqlQuery = String.format("SELECT * FROM journal WHERE journal_id = %d", Long.valueOf(5));
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expected, databaseTester.getConnection(), "journal", sqlQuery, ignore);
        Assert.assertEquals(Long.valueOf(expected.getValue(0, "grade_id").toString()), journalTest.getGrade().getGradeId());
        Assert.assertEquals(Long.valueOf(expected.getValue(0, "student_id").toString()), journalTest.getStudent().getStudentId());
        Assert.assertEquals(Long.valueOf(expected.getValue(0, "subject_id").toString()), journalTest.getSubject().getSubjectId());
	}

}
