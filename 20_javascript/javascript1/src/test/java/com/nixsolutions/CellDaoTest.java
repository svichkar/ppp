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
public class CellDaoTest extends DBTestCase {
	 protected IDatabaseTester tester;
	 protected IDataSet dataSet;
	 protected IDatabaseConnection dbConn;
	 
	@Autowired
	org.springframework.jdbc.datasource.DriverManagerDataSource dataSource;
	
	@Autowired
	CellDao cellDao;
	
	@Before public void setUp() throws Exception {
	dataSet = new FlatXmlDataFileLoader().load("/cell/CellInitialDataSet.xml");
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
	public void testShouldretrieveCellById() throws Exception {
		Cell cell = cellDao.getCellById(1l);
		Assert.assertEquals(new Long(1), cell.getCellId());
		Assert.assertEquals("cell A", cell.getName());
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldReturnNullIfQueryReturnedNoResultsCellById() throws Exception {
		Cell auth = cellDao.getCellById(5l);
		Assert.assertNull(auth);
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldRetrieveAllCells() throws Exception {
		List<Cell> cells = cellDao.getAllCells();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/cell/CellInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("cell"), actualData.getTable("cell"));
		Assert.assertEquals(expectedData.getTable("cell").getRowCount(),cells.size());
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldDeleteCell() throws Exception {
		Cell cell = cellDao.getCellById(1l);
		cellDao.deleteCell(cell);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/cell/CellDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("cell"), actualData.getTable("cell"));
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldCreateCell() throws Exception {
		Cell drama = new Cell("cell D");
		cellDao.createCell(drama);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/cell/CellCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("cell"), 
	            expectedData.getTable("cell").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("cell"), filteredTable); 
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldUpdateCell() throws Exception {
		Cell updCell = cellDao.getCellById(3l);
		updCell.setName("cell E");
		cellDao.updateCell(updCell);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/cell/CellUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("cell"), 
	            expectedData.getTable("cell").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("cell"), filteredTable);
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
