package com.nixsolutions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.entities.Journal;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import config.DBUnitConfig;

public class JournalTest extends DBUnitConfig {
	private JournalDao journalDao;

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
		journalDao = daoFactory.getJournalDao(conn);
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
		journalDao.create(2, 1, 4);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Journal/JournalCreate.xml"));
		ITable expectedTable = expectedData.getTable("journal");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("journal");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "journal_id" });
	}

	@Test
	public void testDelete() throws Exception {
		journalDao.delete(new Journal(1, 1, 1, 3));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Journal/JournalDelete.xml"));
		ITable expectedTable = expectedData.getTable("journal");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("journal");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
		journalDao.update(new Journal(1, 1, 1, 5));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Journal/JournalUpdate.xml"));
		ITable expectedTable = expectedData.getTable("journal");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("journal");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
		Journal journalExpected = new Journal(1, 1, 1, 3);
		Journal journalActual =  journalDao.getByJournalById(1);
		Assert.assertEquals(journalExpected.getRate(), journalActual.getRate());
		Assert.assertEquals(journalExpected.getStudentId(), journalActual.getStudentId());
		Assert.assertEquals(journalExpected.getSubjectId(), journalActual.getSubjectId());
		Assert.assertEquals(journalExpected.getId(), journalActual.getId());
	}
	
	@Test
	public void testGetByJournalsByStudentId() throws Exception {
		List<Journal> journalActual =  journalDao.getJournalByStudentId(1);
		for (Journal journal : journalActual){
			Assert.assertEquals(1, journal.getStudentId());
		}
		Assert.assertEquals(2, journalActual.size());
	}
	
	@Test
	public void testGetByJournalsBySubjectId() throws Exception {
		List<Journal> journalActual =  journalDao.getJournalBySubjectId(2);
		for (Journal journal : journalActual){
			Assert.assertEquals(2, journal.getSubjectId());
		}
		Assert.assertEquals(2, journalActual.size());
	}
}
