package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Student;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentTest extends DBUnitConfig {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TermDao termDao;
	@Autowired
    private StatusDao statusDao;
    @Autowired
    private StudentGroupDao studentGroupDao;

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
        Student student = new Student();
        student.setFirstName("Test");
        student.setLastName("Test");
        student.setDateBirthday(Date.valueOf("1990-01-01"));
        student.setDateEntry(Date.valueOf("2012-05-05"));
        student.setStudentGroup(studentGroupDao.getByStudentGroupId(1));
        student.setTerm(termDao.getByTermId(1));
        student.setStatus(statusDao.getByStatusId(1));
		studentDao.create(student);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Student/StudentCreate.xml"));
		ITable expectedTable = expectedData.getTable("student");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "student_id" });
	}

	@Test
	public void testDelete() throws Exception {
        Student student =studentDao.getByStudentId(4);
		studentDao.delete(student);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Student/StudentDelete.xml"));
		ITable expectedTable = expectedData.getTable("student");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
        Student student =studentDao.getByStudentId(4);
        student.setLastName("Pravsha");
		studentDao.update(student);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Student/StudentUpdate.xml"));
		ITable expectedTable = expectedData.getTable("student");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
        Student studentExpected = new Student();
        studentExpected.setStudentId(4);
        studentExpected.setFirstName("Pavel");
        studentExpected.setLastName("Levsha");
        studentExpected.setDateBirthday(Date.valueOf("1993-05-12"));
        studentExpected.setDateEntry(Date.valueOf("2012-06-11"));
        studentExpected.setStudentGroup(studentGroupDao.getByStudentGroupId(3));
        studentExpected.setTerm(termDao.getByTermId(2));
        studentExpected.setStatus(statusDao.getByStatusId(1));
		//Student studentExpected = new Student(4, "Pavel", "Levsha", Date.valueOf("1993-05-12"), Date.valueOf("2012-06-11"), 3, 2, 1);
		Student studentActual =  studentDao.getByStudentId(4);
		Assert.assertEquals(studentExpected.getFirstName(), studentActual.getFirstName());
		Assert.assertEquals(studentExpected.getLastName(), studentActual.getLastName());
		Assert.assertEquals(studentExpected.getStudentId(), studentActual.getStudentId());
	}
	
	@Test
	public void testGetByStudentsByName() throws Exception {
		List<Student> studentExpected = new ArrayList<Student>();
        Student expected = new Student();
        expected.setStudentId(4);
        expected.setFirstName("Pavel");
        expected.setLastName("Levsha");
        expected.setDateBirthday(Date.valueOf("1993-05-12"));
        expected.setDateEntry(Date.valueOf("2012-06-11"));
        expected.setStudentGroup(studentGroupDao.getByStudentGroupId(3));
        expected.setTerm(termDao.getByTermId(2));
        expected.setStatus(statusDao.getByStatusId(1));
        studentExpected.add(expected);
        List<Student> studentActual =  studentDao.getByStudentsByName("Pavel", "Levsha");
		Assert.assertEquals(studentExpected.size(), studentActual.size());
		Assert.assertEquals(studentExpected.get(0).getFirstName(), studentActual.get(0).getFirstName());
		Assert.assertEquals(studentExpected.get(0).getLastName(), studentActual.get(0).getLastName());
		Assert.assertEquals(studentExpected.get(0).getStudentId(), studentActual.get(0).getStudentId());
	}
}
