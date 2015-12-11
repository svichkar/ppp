package com.nixsolutions;

import java.io.FileInputStream;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Term;

public class TermTest extends DBUnitConfig {

    private TermDao termDao;

    public TermTest(String name) {
	super(name);
    }

    @Before
    public void setUp() throws Exception {
	super.setUp();
	FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
	flatXmlProducer.setColumnSensing(false);
	beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Term/term.xml"));
	tester.setDataSet(beforeData);
	tester.onSetup();
	termDao = daoFactory.getTerm();
    }

    @Test
    public void testGetAll() throws Exception {
	List<Term> terms = termDao.getAll();
	IDataSet expectedData = new FlatXmlDataSetBuilder()
		.build(new FileInputStream("src/test/resources/Term/term.xml"));
	ITable expectedTable = expectedData.getTable("term");
	IDataSet actualData = tester.getConnection().createDataSet();
	ITable actualTale = actualData.getTable("term");
	Assertion.assertEquals(expectedTable, actualTale);
	Assert.assertEquals(expectedData.getTable("term").getRowCount(), terms.size());
    }

    @Test
    public void testCreate() throws Exception {
	Term term = new Term();
	term.setTermAlias("III");
	termDao.create(term);
	IDataSet expectedData = new FlatXmlDataSetBuilder()
		.build(new FileInputStream("src/test/resources/Term/termCreate.xml"));
	ITable expectedTable = expectedData.getTable("term");
	IDataSet actualData = tester.getConnection().createDataSet();
	ITable actualTale = actualData.getTable("term");
	Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "term_id" });
    }

    @Test
    public void testDelete() throws Exception {
	Term term = new Term();
	term.setTermAlias("I");
	term.setTermId(1);
	termDao.delete(term);
	IDataSet expectedData = new FlatXmlDataSetBuilder()
		.build(new FileInputStream("src/test/resources/Term/termDelete.xml"));
	ITable expectedTable = expectedData.getTable("term");
	IDataSet actualData = tester.getConnection().createDataSet();
	ITable actualTale = actualData.getTable("term");
	Assertion.assertEquals(expectedTable, actualTale);
    }

    @Test
    public void testUpdate() throws Exception {
	Term term = new Term();
	term.setTermAlias("III");
	term.setTermId(2);
	termDao.update(term);
	IDataSet expectedData = new FlatXmlDataSetBuilder()
		.build(new FileInputStream("src/test/resources/Term/termUpdate.xml"));
	ITable expectedTable = expectedData.getTable("term");
	IDataSet actualData = tester.getConnection().createDataSet();
	ITable actualTale = actualData.getTable("term");
	Assertion.assertEquals(expectedTable, actualTale);
    }

    @Test
    public void testGetById() throws Exception {
	Term termExpected = new Term();
	termExpected.setTermAlias("I");
	termExpected.setTermId(1);
	Term termActual = termDao.getByTermId(1);
	Assert.assertEquals(termExpected.getTermAlias(), termActual.getTermAlias());
	Assert.assertEquals(termExpected.getTermId(), termActual.getTermId());
    }

    @Test
    public void testGetByAlias() throws Exception {
	Term termExpected = new Term();
	termExpected.setTermAlias("II");
	termExpected.setTermId(2);
	Term termActual = termDao.getTermByAlias("II");
	Assert.assertEquals(termExpected.getTermAlias(), termActual.getTermAlias());
	Assert.assertEquals(termExpected.getTermId(), termActual.getTermId());
    }
}
