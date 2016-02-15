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

import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.service.GradeService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration("classpath:test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("/dbunit/grade/gradeInit.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/grade/gradeInit.xml")
public class GradeServiceTest {
	@Autowired
	private GradeService gradeService;

	@Autowired
	private IDatabaseTester databaseTester;

	@Test
	@ExpectedDatabase(value = "/dbunit/grade/gradeCreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "grade")
	public void shouldCreateGrade() {
		Grade gradeNew = new Grade();
		gradeNew.setGradeName("Super");
		gradeService.createGrade(gradeNew);
	}
	
	@Test
	@ExpectedDatabase(value = "/dbunit/grade/gradeUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "grade")
	public void shouldUpdateGrade() {
        Grade gradeUpdate = gradeService.findGradeById(Long.valueOf(5));
        gradeUpdate.setGradeName("SuperUpdated");
        gradeService.updateGrade(gradeUpdate);
	}

	@Test
	@ExpectedDatabase(value = "/dbunit/grade/gradeDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "grade")
	public void shouldDeleteGrade() {
        gradeService.deleteGrade(gradeService.findGradeById(Long.valueOf(1)));
	}

	@Test
	public void shouldGetAllGrades() throws SQLException, Exception {	        
		List<Grade> grades = gradeService.findAllGrades();
		IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/grade/gradeInit.xml"));
		ITable expected = ds.getTable("grade");
		ITable actual = databaseTester.getConnection().createTable("grade");
		Assertion.assertEquals(expected, actual);
		Assert.assertEquals(expected.getRowCount(), grades.size());
	}

	@Test
	public void shouldFindGradeById() throws DatabaseUnitException, SQLException, Exception {
        Grade gradeTest = gradeService.findGradeById(Long.valueOf(5));
        IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/grade/gradeFindById.xml"));
        ITable expected = ds.getTable("grade");
        String sqlQuery = String.format("SELECT * FROM grade WHERE grade_id = %d", Long.valueOf(5));
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expected, databaseTester.getConnection(), "grade", sqlQuery, ignore);
        Assert.assertEquals(expected.getValue(0, "grade_name"), gradeTest.getGradeName());
	}

}
