package com.nixsolutions;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Author;

public class AuthorDaoTest extends DBUnitConfig {
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	
	public AuthorDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataFileLoader().load("/author/AuthorInitialDataSet.xml");
		//beforeData = new FlatXmlDataFileLoader().load("/book/BookInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
	public void testShouldretrieveAuthorById() throws Exception {
		Author auth = factory.getAuthorDao().getAuthorById(1l);
		Assert.assertEquals(new Long(1), auth.getAuthorId());
		Assert.assertEquals("Stephen", auth.getFirstName());
		Assert.assertEquals("King", auth.getSecondName());
	}
	
	public void testShouldReturnNullIfQueryReturnedNoResultsAuthorById() throws Exception {
		Author auth = factory.getAuthorDao().getAuthorById(5l);
		Assert.assertNull(auth);
	}
	
	public void testShouldRetrieveAllUthors() throws Exception {
		List<Author> authors = factory.getAuthorDao().getAllAuthors();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
		Assert.assertEquals(expectedData.getTable("author").getRowCount(),authors.size());
	}

/*
	public void testShouldDeleteAuthor() throws Exception {
		Author author = factory.getAuthorDao().getAuthorById(1l);
		factory.getAuthorDao().deleteAuthor(author);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
	}
*/
	public void testShouldCreateAuthor() throws Exception {
		Author authMiha = new Author();
		authMiha.setFirstName("Michail");
		authMiha.setSecondName("Lelyakov");
		factory.getAuthorDao().createAuthor(authMiha);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author"), 
	            expectedData.getTable("author").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author"), filteredTable); 
	}

	public void testShouldUpdateAuthor() throws Exception {
		Author updAuth = factory.getAuthorDao().getAuthorById(3l);
		updAuth.setFirstName("Michail");
		updAuth.setSecondName("Lelyakov");
		factory.getAuthorDao().updateAuthor(updAuth);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author"), 
	            expectedData.getTable("author").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author"), filteredTable);
	}
}
