package com.nixsolutions;

import java.sql.Date;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.RentJournal;

public class RentJournalDaoTest extends DBUnitConfig {
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	
	public RentJournalDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataFileLoader().load("/rentJournal/RentJournalInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
	public void testShouldretrieveRentJournalById() throws Exception {
		RentJournal rentJournal = factory.getRentJournalDao().getRentById(1l);
		Assert.assertEquals(new Long(1), rentJournal.getRentId());
		Assert.assertEquals(new Long(1), rentJournal.getBook().getBookId());
		Assert.assertEquals(new Long(1), rentJournal.getClient().getClientId());
		Assert.assertEquals(Date.valueOf("2015-12-25"), rentJournal.getRentDate());
		Assert.assertEquals(Date.valueOf("2016-12-26"), rentJournal.getReturnDate());
	}

	public void testShouldReturnNullIfQueryReturnedNoResultsRentJournalById() throws Exception {
		RentJournal rentJournal = factory.getRentJournalDao().getRentById(5l);
		Assert.assertNull(rentJournal);
	}
	
	public void testShouldRetrieveAllRentJournals() throws Exception {
		List<RentJournal> rentJournals = factory.getRentJournalDao().getAllRents();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/rentJournal/RentJournalInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("rent_journal"), actualData.getTable("rent_journal"));
		Assert.assertEquals(expectedData.getTable("rent_journal").getRowCount(),rentJournals.size());
	}

	public void testShouldDeleteRentJournal() throws Exception {
		RentJournal rentJournal = factory.getRentJournalDao().getRentById(1l);
		factory.getRentJournalDao().deleteRent(rentJournal);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/rentJournal/RentJournalDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("rent_journal"), actualData.getTable("rent_journal"));
	}

	public void testShouldCreateRentJournal() throws Exception {
		RentJournal rentJournal = new RentJournal();
		rentJournal.setBook(factory.getBookDao().getBookById(5l));
		rentJournal.setClient(factory.getClientDao().getClientById(1l));
		rentJournal.setRentDate(Date.valueOf("2015-08-21"));
		rentJournal.setReturnDate(null);
		factory.getRentJournalDao().createRent(rentJournal);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/rentJournal/RentJournalCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("rent_journal"), 
	            expectedData.getTable("rent_journal").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("rent_journal"), filteredTable); 
	}

	public void testShouldUpdateRentJournal() throws Exception {
		RentJournal rentJournal = factory.getRentJournalDao().getRentById(4l);
		rentJournal.setBook(factory.getBookDao().getBookById(5l));
		rentJournal.setClient(factory.getClientDao().getClientById(1l));
		rentJournal.setRentDate(Date.valueOf("2015-06-11"));
		factory.getRentJournalDao().updateRent(rentJournal);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/rentJournal/RentJournalUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
	    Assertion.assertEquals(expectedData.getTable("rent_journal"), actualData.getTable("rent_journal"));
	}
	
}
