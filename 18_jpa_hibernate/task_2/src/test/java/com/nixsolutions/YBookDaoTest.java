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
import com.nixsolutions.entity.Book;

public class YBookDaoTest extends DBUnitConfig {
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	
	public YBookDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataFileLoader().load("/book/BookInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
	public void testShouldretrieveBookById() throws Exception {
		Book book = factory.getBookDao().getBookById(1l);
		Assert.assertEquals(new Long(1), book.getBookId());
		Assert.assertEquals("book1", book.getName());
		Assert.assertEquals(new Long(1), book.getCategory().getCategoryId());
		Assert.assertEquals(new Long(1), book.getCell().getCellId());
	}
	
	
	public void testShouldReturnNullIfQueryReturnedNoResultsBookById() throws Exception {
		Book book = factory.getBookDao().getBookById(5l);
		Assert.assertNull(book);
	}
	
	public void testShouldRetrieveAllBooks() throws Exception {
		List<Book> books = factory.getBookDao().getAllBooks();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/book/BookInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("book"), actualData.getTable("book"));
		Assert.assertEquals(expectedData.getTable("book").getRowCount(),books.size());
	}

	public void testShouldDeleteBook() throws Exception {
		Book book = factory.getBookDao().getBookById(1l);
		factory.getBookDao().deleteBook(book);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/book/BookDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("book"), actualData.getTable("book"));
	}
	
	public void testShouldCreateBook() throws Exception {
		Book book = new Book();
		book.setName("book5");
		book.setCategory(factory.getCategoryDao().getCategoryById(2l));
		book.setCell(factory.getCellDao().getCellById(2l));
		book.setCount(1);
		factory.getBookDao().createBook(book);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/book/BookCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("book"), 
	            expectedData.getTable("book").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("book"), filteredTable); 
	}
	
	public void testShouldUpdateBook() throws Exception {
		Book book = factory.getBookDao().getBookById(4l);
		book.setName("book5");
		book.setCategory(factory.getCategoryDao().getCategoryById(2l));
		book.setCell(factory.getCellDao().getCellById(2l));
		factory.getBookDao().updateBook(book);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/book/BookUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
	    Assertion.assertEquals(expectedData.getTable("book"), actualData.getTable("book"));
	}
	
}
