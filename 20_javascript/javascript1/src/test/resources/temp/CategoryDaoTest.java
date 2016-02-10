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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.CategoryDao;
import com.nixsolutions.entity.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:persistence-beans-test.xml")
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class
   //TransactionalTestExecutionListener.class
    })
@Transactional
public class CategoryDaoTest extends DBTestCase {
	 protected IDatabaseTester tester;
	 protected IDataSet dataSet;
	 protected IDatabaseConnection dbConn;
	 
	@Autowired
	org.springframework.jdbc.datasource.DriverManagerDataSource dataSource;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Before public void setUp() throws Exception {
	dataSet = new FlatXmlDataFileLoader().load("/category/CategoryInitialDataSet.xml");
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
	public void testShouldretrieveCategoryById() throws Exception {
		Category category = categoryDao.getCategoryById(1l);
		Assert.assertEquals(new Long(1), category.getCategoryId());
		Assert.assertEquals("Horror", category.getName());
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldReturnNullIfQueryReturnedNoResultsCategoryById() throws Exception {
		Category auth = categoryDao.getCategoryById(5l);
		Assert.assertNull(auth);
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldRetrieveAllCategories() throws Exception {
		List<Category> categories = categoryDao.getAllCategories();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("category"), actualData.getTable("category"));
		Assert.assertEquals(expectedData.getTable("category").getRowCount(),categories.size());
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldDeleteCategory() throws Exception {
		Category category = categoryDao.getCategoryById(1l);
		categoryDao.deleteCategory(category);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("category"), actualData.getTable("category"));
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldCreateCategory() throws Exception {
		Category drama = new Category("Drama");
		categoryDao.createCategory(drama);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("category"), 
	            expectedData.getTable("category").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("category"), filteredTable); 
	}
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testShouldUpdateCategory() throws Exception {
		Category updCategory = categoryDao.getCategoryById(3l);
		updCategory.setName("SciFi");
		categoryDao.updateCategory(updCategory);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("category"), 
	            expectedData.getTable("category").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("category"), filteredTable);
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
