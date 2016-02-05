package com.nixsolutions;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
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
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.dao.CellDao;
import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.Cell;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:persistence-beans-test.xml")
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class
   //TransactionalTestExecutionListener.class
    })
@Transactional
public class AuthorDaoTest extends DBTestCase {
	 protected IDatabaseTester tester;
	 protected IDataSet dataSet;
	 protected IDatabaseConnection dbConn;
	 
	@Autowired
	org.springframework.jdbc.datasource.DriverManagerDataSource dataSource;
	
	@Autowired
	AuthorDao authorDao;
	
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
	@DirtiesContext
	@Rollback(false)
	public void testShouldretrieveAuthorById() throws Exception {
		Author auth = authorDao.getAuthorById(1l);
		Assert.assertEquals(new Long(1), auth.getAuthorId());
		Assert.assertEquals("Stephen", auth.getFirstName());
		Assert.assertEquals("King", auth.getSecondName());
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldReturnNullIfQueryReturnedNoResultsAuthorById() throws Exception {
		Author auth = authorDao.getAuthorById(5l);
		Assert.assertNull(auth);
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldRetrieveAllUthors() throws Exception {
		List<Author> authors = authorDao.getAllAuthors();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
		Assert.assertEquals(expectedData.getTable("author").getRowCount(),authors.size());
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldDeleteAuthor() throws Exception {
		Author author = authorDao.getAuthorById(1l);
		authorDao.deleteAuthor(author);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("author"), actualData.getTable("author"));
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldCreateAuthor() throws Exception {
		Author authMiha = new Author();
		authMiha.setFirstName("Michail");
		authMiha.setSecondName("Lelyakov");
		authorDao.createAuthor(authMiha);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/author/AuthorCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("author"), 
	            expectedData.getTable("author").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("author"), filteredTable); 
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldUpdateAuthor() throws Exception {
		Author updAuth = authorDao.getAuthorById(3l);
		updAuth.setFirstName("Michail");
		updAuth.setSecondName("Lelyakov");
		authorDao.updateAuthor(updAuth);
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
