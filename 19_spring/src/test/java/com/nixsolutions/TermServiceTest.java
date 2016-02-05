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

import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.service.TermService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration("classpath:test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("/dbunit/term/termInit.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/term/termInit.xml")
public class TermServiceTest {
	@Autowired
	private TermService termService;

	@Autowired
	private IDatabaseTester databaseTester;

	@Test
	@ExpectedDatabase(value = "/dbunit/term/termCreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "term")
	public void shouldCreateTerm() {
		Long termId = termService.createTerm("Spring-2016");
	}
	
	@Test
	@ExpectedDatabase(value = "/dbunit/term/termUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "term")
	public void shouldUpdateTerm() {
        termService.updateTerm(Long.valueOf(3), "Spring-2016");
	}

	@Test
	@ExpectedDatabase(value = "/dbunit/term/termDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "term")
	public void shouldDeleteTerm() {
        termService.deleteTerm(Long.valueOf(1));
	}

	@Test
	public void shouldGetAllTerms() throws SQLException, Exception {	        
		List<Term> terms = termService.findAllTerms();
		IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/term/termInit.xml"));
		ITable expected = ds.getTable("term");
		ITable actual = databaseTester.getConnection().createTable("term");
		Assertion.assertEquals(expected, actual);
		Assert.assertEquals(expected.getRowCount(), terms.size());
	}

	@Test
	public void shouldFindTermById() throws DatabaseUnitException, SQLException, Exception {
        Term termTest = termService.findTermById(Long.valueOf(3));
        IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/term/termFindById.xml"));
        ITable expected = ds.getTable("term");
        String sqlQuery = String.format("SELECT * FROM term WHERE term_id = %d", Long.valueOf(3));
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expected, databaseTester.getConnection(), "term", sqlQuery, ignore);
        Assert.assertEquals(expected.getValue(0, "term_name"), termTest.getTermName());
	}

}
