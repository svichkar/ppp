package com.nixsolutions;

import java.sql.SQLException;
import java.util.List;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration("classpath:test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("/dbunit/user/userInit.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/user/userInit.xml")
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Autowired
	private IDatabaseTester databaseTester;

	@Test
	@ExpectedDatabase(value = "/dbunit/user/userCreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "user")
	public void shouldCreateUser() {
		Long userId = userService.createUser("basic", "basic", "basic@mail.com", Long.valueOf(2));
	}
	
	@Test
	@ExpectedDatabase(value = "/dbunit/user/userUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "user")
	public void shouldUpdateUser() {
        userService.updateUser(Long.valueOf(2), "system_manager", "manager", "manager@mail.com", Long.valueOf(2));
	}

	@Test
	@ExpectedDatabase(value = "/dbunit/user/userDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "user")
	public void shouldDeleteUser() {
        userService.deleteUser(Long.valueOf(2));
	}

	@Test
	public void shouldGetAllUsers() throws SQLException, Exception {	        
		List<User> users = userService.findAllUsers();
		IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/user/userInit.xml"));
		ITable expected = ds.getTable("user");
		ITable actual = databaseTester.getConnection().createTable("user");
		Assertion.assertEquals(expected, actual);
		Assert.assertEquals(expected.getRowCount(), users.size());
	}

	@Test
	public void shouldFindUserById() throws DatabaseUnitException, SQLException, Exception {
        User userTest = userService.findUserById(Long.valueOf(1));
        IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/user/userFindById.xml"));
        ITable expected = ds.getTable("user");
        String sqlQuery = String.format("SELECT * FROM user WHERE user_id = %d", Long.valueOf(1));
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expected, databaseTester.getConnection(), "user", sqlQuery, ignore);
        Assert.assertEquals(expected.getValue(0, "login"), userTest.getLogin());
        Assert.assertEquals(expected.getValue(0, "password"), userTest.getPassword());
        Assert.assertEquals(expected.getValue(0, "email"), userTest.getEmail());
	}

}
