package com.nixsolutions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.entities.Student;

import java.io.FileInputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import config.DBUnitConfig;

public class StudentTest extends DBUnitConfig {
	private StudentDao studentDao;

	public StudentTest(String name) throws SQLException, ClassNotFoundException {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Student/Student.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
		studentDao = daoFactory.getStudentDao(conn);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Student> students = studentDao.getAll();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Student/Student.xml"));
		ITable expectedTable = expectedData.getTable("student");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("student").getRowCount(), students.size());
	}

	@Test
	public void testCreate() throws Exception {
		studentDao.create("Test", "Test", Date.valueOf("1990-01-01"), Date.valueOf("2012-05-05"), 1, 1, 1);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Student/StudentCreate.xml"));
		ITable expectedTable = expectedData.getTable("student");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "student_id" });
	}

	@Test
	public void testDelete() throws Exception {
		studentDao.delete(new Student(4, "Pavel", "Levsha", Date.valueOf("1993-05-12"), Date.valueOf("2012-06-11"), 3, 2, 1));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Student/StudentDelete.xml"));
		ITable expectedTable = expectedData.getTable("student");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
		studentDao.update(new Student(4, "Pavel", "Pravsha", Date.valueOf("1993-05-12"), Date.valueOf("2012-06-11"), 3, 2, 1));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Student/StudentUpdate.xml"));
		ITable expectedTable = expectedData.getTable("student");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
		Student studentExpected = new Student(4, "Pavel", "Levsha", Date.valueOf("1993-05-12"), Date.valueOf("2012-06-11"), 3, 2, 1);
		Student studentActual =  studentDao.getByStudentId(4);
		Assert.assertEquals(studentExpected.getFirstName(), studentActual.getFirstName());
		Assert.assertEquals(studentExpected.getLastName(), studentActual.getLastName());
		Assert.assertEquals(studentExpected.getId(), studentActual.getId());
	}
	
	@Test
	public void testGetByStudentsByName() throws Exception {
		List<Student> studentExpected = new ArrayList<Student>();
		studentExpected.add(new Student(4, "Pavel", "Levsha", Date.valueOf("1993-05-12"), Date.valueOf("2012-06-11"), 3, 2, 1));
		List<Student> studentActual =  studentDao.getByStudentsByName("Pavel", "Levsha");
		Assert.assertEquals(studentExpected.size(), studentActual.size());
		Assert.assertEquals(studentExpected.get(0).getFirstName(), studentActual.get(0).getFirstName());
		Assert.assertEquals(studentExpected.get(0).getLastName(), studentActual.get(0).getLastName());
		Assert.assertEquals(studentExpected.get(0).getId(), studentActual.get(0).getId());
	}
}
