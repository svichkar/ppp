package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Subject;
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

public class SubjectTest extends DBUnitConfig {
	private SubjectDao subjectDao;
	private TermDao termDao;

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
        termDao = daoFactory.getTermDao();
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
        Subject subject = new Subject();
        subject.setName("Test");
        subject.setTerm(termDao.getByTermId(3));
		subjectDao.create(subject);
        IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Subject/SubjectCreate.xml"));
		ITable expectedTable = expectedData.getTable("subject");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("subject");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "subject_id" });
	}

	@Test
	public void testDelete() throws Exception {
        Subject subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject 1");
        subject.setTerm(termDao.getByTermId(1));
		subjectDao.delete(subject);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Subject/SubjectDelete.xml"));
		ITable expectedTable = expectedData.getTable("subject");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("subject");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
        Subject subject = subjectDao.getBySubjectId(6);
        subject.setTerm(termDao.getByTermId(3));
        subject.setName("Test Update");
		subjectDao.update(subject);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Subject/SubjectUpdate.xml"));
		ITable expectedTable = expectedData.getTable("subject");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("subject");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
        Subject subjectExpected = new Subject();
        subjectExpected.setSubjectId(1);
        subjectExpected.setName("Test Subject 1");
        subjectExpected.setTerm(termDao.getByTermId(1));
        //Subject subjectExpected = new Subject(1, "Test Subject 1", 1);
		Subject subjectActual =  subjectDao.getBySubjectId(1);
		Assert.assertEquals(subjectExpected.getName(), subjectActual.getName());
		Assert.assertEquals(subjectExpected.getSubjectId(), subjectExpected.getSubjectId());
		Assert.assertEquals(subjectExpected.getTerm().getTermId(), subjectExpected.getTerm().getTermId());
	}
	
	@Test
	public void testGetBySubjectName() throws Exception {
        Subject subjectExpected = new Subject();
        subjectExpected.setSubjectId(1);
        subjectExpected.setName("Test Subject 1");
        subjectExpected.setTerm(termDao.getByTermId(1));
		//Subject subjectExpected = new Subject(1, "Test Subject 1", 1);
		List<Subject> subjectActual =  subjectDao.getBySubjectName("Test Subject 1");
        Assert.assertEquals(1, subjectActual.size());
		Assert.assertEquals(subjectExpected.getName(), subjectActual.get(0).getName());
		Assert.assertEquals(subjectExpected.getSubjectId(), subjectActual.get(0).getSubjectId());
		Assert.assertEquals(subjectExpected.getTerm().getTermId(), subjectActual.get(0).getTerm().getTermId());
	}
	
	@Test
	public void testGetByTermId() throws Exception {
		List<Subject> listActual = subjectDao.getSubjectsByTermId(termDao.getByTermId(1).getTermId());
		Assert.assertEquals(2, listActual.size());
	}
}
