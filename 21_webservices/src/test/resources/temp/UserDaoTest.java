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
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.Cell;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:persistence-beans-test.xml")
@WebAppConfiguration
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
   TransactionalTestExecutionListener.class
    })
@Transactional
public class UserDaoTest extends DBTestCase {
	 protected IDatabaseTester tester;
	 protected IDataSet dataSet;
	 protected IDatabaseConnection dbConn;
	 
	@Autowired
	org.springframework.jdbc.datasource.DriverManagerDataSource dataSource;
	
	@Autowired
	UserDao userDao;
	@Autowired
	UserService userService;
	
	@Before public void setUp() throws Exception {
	dataSet = new FlatXmlDataFileLoader().load("/book/BookInitialDataSet.xml");
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
	
	//@Test
	@DirtiesContext
	@Rollback(false)
	public void testDAOShouldretrieveCellById() throws Exception {
		User usr = userDao.getUserById(1l);
		Assert.assertEquals(new Long(1), usr.getUserId());
		Assert.assertEquals("test", usr.getUserName());
	}

	@Test
	@DirtiesContext
	@Rollback(false)
	public void testServiceShouldretrieveCellById() throws Exception {
		User usr = userService.getUserById("1");
		Assert.assertEquals(new Long(1), usr.getUserId());
		Assert.assertEquals("test", usr.getUserName());
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
