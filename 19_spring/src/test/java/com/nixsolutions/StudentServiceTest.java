package com.nixsolutions;

import java.sql.Date;
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

import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration("classpath:test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("/dbunit/student/studentInit.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/student/studentInit.xml")
public class StudentServiceTest {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private IDatabaseTester databaseTester;

	@Test
	@ExpectedDatabase(value = "/dbunit/student/studentCreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "student")
	public void shouldCreateStudent() {
		Long studentId = studentService.createStudent("Julie", "West", Long.valueOf(1), Date.valueOf("2014-09-01"), Long.valueOf(1), Long.valueOf(1));
	}
	
	@Test
	@ExpectedDatabase(value = "/dbunit/student/studentUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "student")
	public void shouldUpdateStudent() {
        studentService.updateStudent(Long.valueOf(3), "Kate", "Test", Long.valueOf(1), Date.valueOf("2014-09-01"), Long.valueOf(1), Long.valueOf(1));
	}

	@Test
	@ExpectedDatabase(value = "/dbunit/student/studentDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "student")
	public void shouldDeleteStudent() {
        studentService.deleteStudent(Long.valueOf(2));
	}

	@Test
	public void shouldGetAllStudents() throws SQLException, Exception {	        
		List<Student> students = studentService.findAllStudents();
		IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/student/studentInit.xml"));
		ITable expected = ds.getTable("student");
		ITable actual = databaseTester.getConnection().createTable("student");
		Assertion.assertEquals(expected, actual);
		Assert.assertEquals(expected.getRowCount(), students.size());
	}

	@Test
	public void shouldFindStudentById() throws DatabaseUnitException, SQLException, Exception {
        Student studentTest = studentService.findStudentById(Long.valueOf(1));
        IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/student/studentFindById.xml"));
        ITable expected = ds.getTable("student");
        String sqlQuery = String.format("SELECT * FROM student WHERE student_id = %d", Long.valueOf(1));
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expected, databaseTester.getConnection(), "student", sqlQuery, ignore);
        Assert.assertEquals(expected.getValue(0, "first_name"), studentTest.getFirstName());
        Assert.assertEquals(expected.getValue(0, "last_name"), studentTest.getLastName());
	}

}
