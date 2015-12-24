package com.nixsolutions;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;
import com.nixsolutions.dao.impl.AuthorDaoImpl;
import com.nixsolutions.entity.Author;

public class AuthorDaoTest extends DBUnitConfig {

	public AuthorDaoTest(String name) {
		super(name);
	}


	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataFileLoader().load("/author/AuthorInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	

	public void testShouldretrieveAuthorById() throws Exception {
		Author auth = new AuthorDaoImpl().getAuthor(5);
		Assert.assertEquals(1, auth.getAuthorId());
		Assert.assertEquals("Stephen", auth.getFirstName());
		Assert.assertEquals("King", auth.getSecondName());
	}

	public void testShouldRetrieveAllUthors() throws Exception {
		List<Author> authors = new AuthorDaoImpl().getAllAuthors();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
		Assert.assertEquals(expectedData.getTable("author").getRowCount(),authors.size());
	}

	public void testShouldDeleteAuthor() throws Exception {
		Author author = new AuthorDaoImpl().getAuthor(1);
		new AuthorDaoImpl().deleteAuthor(author);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
	}

	public void testShouldCreateAuthor() throws Exception {
		Author authMiha = new Author();
		authMiha.setFirstName("Michail");
		authMiha.setSecondName("Lelyakov");
		new AuthorDaoImpl().createAuthor(authMiha);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author"), 
	            expectedData.getTable("author").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author"), filteredTable); 
	}

	public void testShouldUpdateAuthor() throws Exception {
		Author updAuth = new AuthorDaoImpl().getAuthor(3);
		updAuth.setFirstName("Michail");
		updAuth.setSecondName("Lelyakov");
		new AuthorDaoImpl().updateAuthor(updAuth);;
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author"), 
	            expectedData.getTable("author").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author"), filteredTable);
	}
}
