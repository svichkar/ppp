package com.nixsolutions;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;
import com.nixsolutions.dao.impl.BookDaoImpl;
import com.nixsolutions.entity.Book;

public class BookDaoTest extends DBUnitConfig {

	public BookDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataFileLoader().load("/book/BookInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
	public void testShouldretrieveBookById() throws Exception {
		Book book = new BookDaoImpl().getBookById(1);
		Assert.assertEquals(new Integer(1), book.getBookId());
		Assert.assertEquals("book1", book.getName());
		Assert.assertEquals(new Integer(1), book.getCategoryId());
		Assert.assertEquals(new Integer(1), book.getCellId());
	}
	
	public void testShouldReturnNullIfQueryReturnedNoResultsBookById() throws Exception {
		Book book = new BookDaoImpl().getBookById(5);
		Assert.assertNull(book);
	}
	
	public void testShouldRetrieveAllBooks() throws Exception {
		List<Book> books = new BookDaoImpl().getAllBooks();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/book/BookInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("book"), actualData.getTable("book"));
		Assert.assertEquals(expectedData.getTable("book").getRowCount(),books.size());
	}

	public void testShouldDeleteBook() throws Exception {
		Book book = new BookDaoImpl().getBookById(1);
		new BookDaoImpl().deleteBook(book);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/book/BookDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("book"), actualData.getTable("book"));
	}

	public void testShouldCreateBook() throws Exception {
		Book book = new Book();
		book.setName("book5");
		book.setCategoryId(2);
		book.setCellId(2);
		new BookDaoImpl().createBook(book);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/book/BookCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("book"), 
	            expectedData.getTable("book").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("book"), filteredTable); 
	}

	public void testShouldUpdateBook() throws Exception {
		Book book = new BookDaoImpl().getBookById(4);
		book.setName("book5");
		book.setCategoryId(2);
		book.setCellId(2);
		new BookDaoImpl().updateBook(book);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/book/BookUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
	    Assertion.assertEquals(expectedData.getTable("book"), actualData.getTable("book"));
	}
}
