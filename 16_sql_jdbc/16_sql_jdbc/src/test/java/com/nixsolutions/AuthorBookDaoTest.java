package com.nixsolutions;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;
import com.nixsolutions.dao.impl.AuthorBookDaoImpl;
import com.nixsolutions.entity.AuthorBook;

public class AuthorBookDaoTest extends DBUnitConfig {

	public AuthorBookDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataFileLoader().load("/authorBook/AuthorBookInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
	public void testShouldretrieveAllBooksByAuthorId() throws Exception { //need to check
		List<AuthorBook> authorBooks = new AuthorBookDaoImpl().getBooksIdByAuthorId(1);
		Assert.assertEquals(2,authorBooks.size());
	}
	
	public void testShouldRetrieveAllAuthorBooks() throws Exception { 
		List<AuthorBook> authorBooks = new AuthorBookDaoImpl().getAllAuthorBook();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/authorBook/AuthorBookInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author_book"), actualData.getTable("author_book"));
		Assert.assertEquals(expectedData.getTable("author_book").getRowCount(),authorBooks.size());
	}
	
	public void testShouldretrieveAuthorBookById() throws Exception {
		AuthorBook authBook = new AuthorBookDaoImpl().getAuthorBookById(1, 1);
		Assert.assertEquals(new Integer(1), authBook.getAuthorId());
		Assert.assertEquals(new Integer(1), authBook.getBookId());
	}

	public void testShouldDeleteAuthorBook() throws Exception {
		AuthorBook authorBook = new AuthorBookDaoImpl().getAuthorBookById(1, 1);
		new AuthorBookDaoImpl().deleteAuthorBook(authorBook);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/authorBook/AuthorBookDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author_book"), actualData.getTable("author_book"));
	}

	public void testShouldCreateAuthorBook() throws Exception {
		AuthorBook authBook = new AuthorBook(1, 6);
		new AuthorBookDaoImpl().createAuthorBook(authBook);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/authorBook/AuthorBookCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author_book"), 
	            expectedData.getTable("author_book").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author_book"), filteredTable); 
	}
/*
	public void testShouldUpdateAuthorBook() throws Exception {
		AuthorBook updAuthorBook = new AuthorBookDaoImpl().getAuthorBookById(3, 4); 
		updAuthorBook.setAuthorId(2);
		updAuthorBook.setBookId(7);
		new AuthorBookDaoImpl().updateAuthorBook(updAuthorBook);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/authorBook/AuthorBookUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author_book"), 
	            expectedData.getTable("author_book").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author_book"), filteredTable);
	}
	*/
}

