package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.*;
import com.nixsolutions.entity.*;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

public class JournalTest extends DBUnitConfig {
	@Autowired
    private JournalDao journalDao;
	@Autowired
    private RateDao rateDao;
	@Autowired
    private StudentDao studentDao;
	@Autowired
    private SubjectDao subjectDao;

	public JournalTest(String name) throws SQLException, ClassNotFoundException {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Journal/Journal.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
	}

	@Test
	public void testGetAll() throws Exception {
		List<Journal> journals = journalDao.getAll();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Journal/Journal.xml"));
		ITable expectedTable = expectedData.getTable("journal");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("journal");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("journal").getRowCount(), journals.size());
	}

	@Test
	public void testCreate() throws Exception {
		Journal journal = new Journal();
        journal.setStudent(studentDao.getByStudentId(2));
        journal.setSubject(subjectDao.getBySubjectId(1));
        journal.setRate(rateDao.getByRateId(4));
		journalDao.create(journal);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Journal/JournalCreate.xml"));
		ITable expectedTable = expectedData.getTable("journal");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("journal");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "journal_id" });
	}

	@Test
	public void testDelete() throws Exception {
        Journal journal = journalDao.getByJournalById(1);
        journalDao.delete(journal);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Journal/JournalDelete.xml"));
		ITable expectedTable = expectedData.getTable("journal");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("journal");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
        Journal journal = journalDao.getByJournalById(1);
        journal.setRate(rateDao.getByRateId(5));
		journalDao.update(journal);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Journal/JournalUpdate.xml"));
		ITable expectedTable = expectedData.getTable("journal");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("journal");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
        Journal journalExpected = new Journal();
        journalExpected.setId(1);
        journalExpected.setStudent(studentDao.getByStudentId(1));
        journalExpected.setSubject(subjectDao.getBySubjectId(1));
        journalExpected.setRate(rateDao.getByRateId(3));
		//Journal journalExpected = new Journal(1, 1, 1, 3);
		Journal journalActual =  journalDao.getByJournalById(1);
		Assert.assertEquals(journalExpected.getRate().getId(), journalActual.getRate().getId());
		Assert.assertEquals(journalExpected.getStudent().getStudentId(), journalExpected.getStudent().getStudentId());
		Assert.assertEquals(journalExpected.getSubject().getSubjectId(), journalExpected.getSubject().getSubjectId());
		Assert.assertEquals(journalExpected.getId(), journalActual.getId());
	}
	
	@Test
	public void testGetByJournalsByStudentId() throws Exception {
		List<Journal> journalActual =  journalDao.getJournalByStudentId(1);
		for (Journal journal : journalActual){
			Assert.assertEquals(1, journal.getStudent().getStudentId());
		}
		Assert.assertEquals(2, journalActual.size());
	}
	
	@Test
	public void testGetByJournalsBySubjectId() throws Exception {
		List<Journal> journalActual =  journalDao.getJournalBySubjectId(2);
		for (Journal journal : journalActual){
			Assert.assertEquals(2, journal.getSubject().getSubjectId());
		}
		Assert.assertEquals(2, journalActual.size());
	}
}
