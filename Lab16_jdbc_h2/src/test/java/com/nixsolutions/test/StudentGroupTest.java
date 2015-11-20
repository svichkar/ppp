package com.nixsolutions.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.entity.StudentGroup;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import com.nixsolutions.config.DBUnitConfig;

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
		ITable expectedTable = expectedData.getTable("student_group");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student_group");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("student_group").getRowCount(), student_groups.size());
	}

	@Test
	public void testCreate() throws Exception {
		studentGroupDao.create("Test");
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/StudentGroup/StudentGroupCreate.xml"));
		ITable expectedTable = expectedData.getTable("student_group");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student_group");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "student_group_id" });
	}

	@Test
	public void testDelete() throws Exception {
		studentGroupDao.delete(new StudentGroup(2, "Two"));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/StudentGroup/StudentGroupDelete.xml"));
		ITable expectedTable = expectedData.getTable("student_group");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student_group");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
		studentGroupDao.update(new StudentGroup(3, "Test"));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/StudentGroup/StudentGroupUpdate.xml"));
		ITable expectedTable = expectedData.getTable("student_group");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("student_group");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
		StudentGroup studentGroupExpected = new StudentGroup(1, "One");
		StudentGroup studentGroupActual =  studentGroupDao.getByStudentGroupId(1);
		Assert.assertEquals(studentGroupExpected.getGroupName(), studentGroupActual.getGroupName());
		Assert.assertEquals(studentGroupExpected.getId(), studentGroupActual.getId());
	}
	
	@Test
	public void testGetByGroupName() throws Exception {
		StudentGroup studentGroupExpected = new StudentGroup(3, "Three");
		StudentGroup studentGroupActual =  studentGroupDao.getByStudentGroupName("Three");
		Assert.assertEquals(studentGroupExpected.getGroupName(), studentGroupActual.getGroupName());
		Assert.assertEquals(studentGroupExpected.getId(), studentGroupActual.getId());
	}
}
