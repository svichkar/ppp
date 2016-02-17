package com.nixsolutions;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.entity.Author;
import com.nixsolutions.service.AuthorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:persistence-beans-test.xml")
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
  TransactionalTestExecutionListener.class
    })

@Transactional (value = "transactionManager")
public class AuthorDaoTest extends DBTestCase {
	 protected IDatabaseTester tester;
	 protected IDataSet dataSet;
	 protected IDatabaseConnection dbConn;
	 
	@Autowired
	org.springframework.jdbc.datasource.DriverManagerDataSource dataSource;
	
	@Autowired
	AuthorDao authorDao;
	@Autowired
	AuthorService authorService;
	
	
	@Before public void setUp() throws Exception {
	dataSet = new FlatXmlDataFileLoader().load("/author/AuthorInitialDataSet.xml");
	dbConn = new DatabaseDataSourceConnection(dataSource);
	DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
	tester = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
	tester.setDataSet(dataSet);
	tester.setSetUpOperation(this.getSetUpOperation());
    tester.onSetup();
	}

	 @After
	    public void tearDown() throws Exception {
		 	tester.setTearDownOperation(this.getTearDownOperation());
		    tester.onTearDown();
	        DatabaseOperation.DELETE_ALL.execute(dbConn, dataSet);
	    }
	
	@Test
	@Transactional (value = "transactionManager")
	public void testShouldretrieveAuthorById() throws Exception {
		Author auth = authorDao.getAuthorById(1l);
		Assert.assertEquals(new Long(1), auth.getAuthorId());
		Assert.assertEquals("Stephen", auth.getFirstName());
		Assert.assertEquals("King", auth.getSecondName());
	}
	
	@Test
	public void testShouldReturnNullIfQueryReturnedNoResultsAuthorById() throws Exception {
		Author auth = authorDao.getAuthorById(5l);
		Assert.assertNull(auth);
	}
	
	@Test
	public void testShouldRetrieveAllUthors() throws Exception {
		List<Author> authors = authorDao.getAllAuthors();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
		Assert.assertEquals(expectedData.getTable("author").getRowCount(),authors.size());
	}
	
	@Test
	@Transactional (value = "transactionManager")
	public void testShouldDeleteAuthor() throws Exception {
		Author author = authorDao.getAuthorById(1l);
		authorService.deleteAuthor(author);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
	}
	
	@Test
	@Transactional (value = "transactionManager")
	public void testShouldCreateAuthor() throws Exception {
		Author authMiha = new Author();
		authMiha.setFirstName("Michail");
		authMiha.setSecondName("Lelyakov");
		authorService.createAuthor(authMiha);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author"), 
	            expectedData.getTable("author").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author"), filteredTable); 
	}
	
	@Test
	@Transactional (value = "transactionManager")
	public void testShouldUpdateAuthor() throws Exception {
		Author updAuth = authorDao.getAuthorById(3l);
		updAuth.setFirstName("Michail");
		updAuth.setSecondName("Lelyakov");
		authorService.updateAuthor(updAuth);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author"), 
	            expectedData.getTable("author").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author"), filteredTable);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return this.dataSet;
	}
	
	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE_ALL;
	}

	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}

	@Override
	protected void closeConnection(IDatabaseConnection conn) {
	    // Empty body on purpose.
	}
}
