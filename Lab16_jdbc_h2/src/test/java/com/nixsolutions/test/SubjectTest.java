package com.nixsolutions.test;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.entity.Subject;

import com.nixsolutions.config.DBUnitConfig;

public class SubjectTest extends DBUnitConfig {
	private SubjectDao subjectDao;

	public SubjectTest(String name) throws SQLException, ClassNotFoundException {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Subject/Subject.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
		subjectDao = daoFactory.getSubjectDao();
	}

	@Test
	public void testGetAll() throws Exception {
		List<Subject> Subjects = subjectDao.getAll();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Subject/Subject.xml"));
		ITable expectedTable = expectedData.getTable("subject");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("subject");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("subject").getRowCount(), Subjects.size());
	}

	@Test
	public void testCreate() throws Exception {
		subjectDao.create("Test", 3);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Subject/SubjectCreate.xml"));
		ITable expectedTable = expectedData.getTable("subject");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("subject");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "subject_id" });
	}

	@Test
	public void testDelete() throws Exception {
		subjectDao.delete(new Subject(1, "Test Subject 1", 1));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Subject/SubjectDelete.xml"));
		ITable expectedTable = expectedData.getTable("subject");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("subject");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
		subjectDao.update(new Subject(6, "Test Update", 3));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Subject/SubjectUpdate.xml"));
		ITable expectedTable = expectedData.getTable("subject");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("subject");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
		Subject subjectExpected = new Subject(1, "Test Subject 1", 1);
		Subject subjectActual =  subjectDao.getBySubjectId(1);
		Assert.assertEquals(subjectExpected.getName(), subjectActual.getName());
		Assert.assertEquals(subjectExpected.getId(), subjectExpected.getId());
		Assert.assertEquals(subjectExpected.getTermId(), subjectExpected.getTermId());
	}
	
	@Test
	public void testGetBySubjectName() throws Exception {
		Subject subjectExpected = new Subject(1, "Test Subject 1", 1);
		Subject subjectActual =  subjectDao.getBySubjectName("Test Subject 1", 1);
		Assert.assertEquals(subjectExpected.getName(), subjectActual.getName());
		Assert.assertEquals(subjectExpected.getId(), subjectExpected.getId());
		Assert.assertEquals(subjectExpected.getTermId(), subjectExpected.getTermId());
	}
	
	@Test
	public void testGetByTermId() throws Exception {
		List<Subject> listActual = subjectDao.getSubjectsByTermId(1);
		Assert.assertEquals(2, listActual.size());
	}

}
