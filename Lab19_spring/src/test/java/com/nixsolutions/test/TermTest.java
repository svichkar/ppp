package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Term;
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

public class TermTest extends DBUnitConfig {
	private TermDao termDao;

	public TermTest(String name) throws SQLException, ClassNotFoundException {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Term/Term.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
		termDao = daoFactory.getTermDao();
	}

	@Test
	public void testGetAll() throws Exception {
		List<Term> terms = termDao.getAll();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Term/Term.xml"));
		ITable expectedTable = expectedData.getTable("term");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("term");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("term").getRowCount(), terms.size());
	}

	@Test
	public void testCreate() throws Exception {
		Term term = new Term();
        term.setAlias("IV");
		termDao.create(term);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Term/TermCreate.xml"));
		ITable expectedTable = expectedData.getTable("term");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("term");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "term_id" });
	}

	@Test
	public void testDelete() throws Exception {
        Term term = new Term();
        term.setAlias("II");
        term.setTermId(2);
		termDao.delete(term);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Term/TermDelete.xml"));
		ITable expectedTable = expectedData.getTable("term");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("term");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
        Term term = new Term();
        term.setAlias("V");
        term.setTermId(3);
        termDao.update(term);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Term/TermUpdate.xml"));
		ITable expectedTable = expectedData.getTable("term");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("term");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
        Term termExpected = new Term();
        termExpected.setAlias("I");
        termExpected.setTermId(1);
		Term termActual =  termDao.getByTermId(1);
		Assert.assertEquals(termExpected.getAlias(), termActual.getAlias());
		Assert.assertEquals(termExpected.getTermId(), termActual.getTermId());
	}
	
	@Test
	public void testGetByAlias() throws Exception {
        Term termExpected = new Term();
        termExpected.setAlias("II");
        termExpected.setTermId(2);
		Term termActual =  termDao.getByTermAlias("II");
		Assert.assertEquals(termExpected.getAlias(), termActual.getAlias());
		Assert.assertEquals(termExpected.getTermId(), termActual.getTermId());
	}
}
