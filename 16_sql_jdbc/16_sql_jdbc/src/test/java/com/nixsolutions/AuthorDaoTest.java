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
		beforeData = new FlatXmlDataFileLoader().load("/LibraryTestDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
/*
	@Test
	public void shouldretrieveAuthorById() throws Exception {
		Author auth = new AuthorDaoImpl().getAuthor(1);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("LibraryTestDataSet.xml"));

		IDataSet actualData = tester.getConnection().createDataSet();
		// Assertion.assertEquals(expectedData, actualData);
		// Assert.assertEquals(expectedData.getTable("author").getRowCount(),persons.size());
	}
*/

	public void testShouldRetrieveAllUthors() throws Exception {
		List<Author> authors = new AuthorDaoImpl().getAllAuthors();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/LibraryTestDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
		Assert.assertEquals(expectedData.getTable("author").getRowCount(),authors.size());
	}

	public void testShouldDeleteAuthor() throws Exception {
		Author author = new AuthorDaoImpl().getAuthor(1);
		new AuthorDaoImpl().deleteAuthor(author);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/LibraryTestDataSet2.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
	}

	public void testShouldCreateAuthor() throws Exception {
		Author authMiha = new Author();
		authMiha.setFirstName("Michail");
		authMiha.setSecondName("Lelyakov");
		new AuthorDaoImpl().createAuthor(authMiha);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/LibraryTestDataSet3.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author"), 
	            expectedData.getTable("author").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author"), filteredTable); 
	}

	public void testShouldUpdateAuthor() {

	}
}
