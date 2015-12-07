package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.entity.StudentGroup;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

public class StudentGroupTest extends DBUnitConfig {
	private StudentGroupDao studentGroupDao;

	public StudentGroupTest(String name) throws SQLException, ClassNotFoundException {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/StudentGroup/StudentGroup.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
		studentGroupDao = daoFactory.getStudentGroupDao();
	}

	@Test
	public void testGetAll() throws Exception {
		List<StudentGroup> student_groups = studentGroupDao.getAll();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/StudentGroup/StudentGroup.xml"));
		ITable expectedTable = expectedData.getTable("studentgroup");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("studentgroup");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("studentgroup").getRowCount(), student_groups.size());
	}

	@Test
	public void testCreate() throws Exception {
		StudentGroup studentGroup = new StudentGroup();
		studentGroup.setStudentGroupName("Test");
		studentGroupDao.create(studentGroup);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/StudentGroup/StudentGroupCreate.xml"));
		ITable expectedTable = expectedData.getTable("studentgroup");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("studentgroup");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "student_group_id" });
	}

	@Test
	public void testDelete() throws Exception {
		StudentGroup studentGroup = new StudentGroup();
		studentGroup.setStudentGroupName("ToDelete");
		studentGroup.setStudentGroupId(4);
		studentGroupDao.delete(studentGroup);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/StudentGroup/StudentGroupDelete.xml"));
		ITable expectedTable = expectedData.getTable("studentgroup");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("studentgroup");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setStudentGroupName("Test");
        studentGroup.setStudentGroupId(3);
		studentGroupDao.update(studentGroup);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/StudentGroup/StudentGroupUpdate.xml"));
		ITable expectedTable = expectedData.getTable("studentgroup");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("studentgroup");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
        StudentGroup studentGroupExpected = new StudentGroup();
        studentGroupExpected.setStudentGroupName("One");
        studentGroupExpected.setStudentGroupId(1);
		StudentGroup studentGroupActual =  studentGroupDao.getByStudentGroupId(1);
		Assert.assertEquals(studentGroupExpected.getStudentGroupName(), studentGroupActual.getStudentGroupName());
		Assert.assertEquals(studentGroupExpected.getStudentGroupId(), studentGroupActual.getStudentGroupId());
	}
	
	@Test
	public void testGetByGroupName() throws Exception {
        StudentGroup studentGroupExpected = new StudentGroup();
        studentGroupExpected.setStudentGroupName("Three");
        studentGroupExpected.setStudentGroupId(3);
		StudentGroup studentGroupActual =  studentGroupDao.getByStudentGroupName("Three");
		Assert.assertEquals(studentGroupExpected.getStudentGroupName(), studentGroupActual.getStudentGroupName());
		Assert.assertEquals(studentGroupExpected.getStudentGroupId(), studentGroupActual.getStudentGroupId());
	}
}
