package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

public class UserTest extends DBUnitConfig {
	private UserDao userDao;

	public UserTest(String name) throws SQLException, ClassNotFoundException {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/User/User.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
		userDao = daoFactory.getUserDao();
	}

	@Test
	public void testGetAll() throws Exception {
		List<User> users = userDao.getAll();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/User/User.xml"));
		ITable expectedTable = expectedData.getTable("user");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("user");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("user").getRowCount(), users.size());
	}

	@Test
	public void testCreate() throws Exception {
		userDao.create("teacher2", "1111", "teacher2@test.com", 3);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/User/UserCreate.xml"));
		ITable expectedTable = expectedData.getTable("user");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("user");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "user_id" });
	}

	@Test
	public void testDelete() throws Exception {
		userDao.delete(new User(3, "teacher", "1111", "teacher@test.com", 3));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/User/UserDelete.xml"));
		ITable expectedTable = expectedData.getTable("user");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("user");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
		userDao.update(new User(3, "teacher", "1111", "teacher3@test.com", 3));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/User/UserUpdate.xml"));
		ITable expectedTable = expectedData.getTable("user");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("user");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
		User userExpected = new User(3, "teacher", "1111", "teacher@test.com", 3);
		User userActual =  userDao.getByUserId(3);
		Assert.assertEquals(userExpected.getEmail(), userActual.getEmail());
		Assert.assertEquals(userExpected.getUserName(), userActual.getUserName());
		Assert.assertEquals(userExpected.getRoleId(), userActual.getRoleId());
		Assert.assertEquals(userExpected.getPassword(), userActual.getPassword());
		Assert.assertEquals(userExpected.getId(), userActual.getId());
	}
	
	@Test
	public void testGetByUserName() throws Exception {
		User userExpected = new User(3, "teacher", "1111", "teacher@test.com", 3);
		User userActual =  userDao.getByUserName("teacher");
        Assert.assertEquals(userExpected.getEmail(), userActual.getEmail());
        Assert.assertEquals(userExpected.getUserName(), userActual.getUserName());
        Assert.assertEquals(userExpected.getRoleId(), userActual.getRoleId());
        Assert.assertEquals(userExpected.getPassword(), userActual.getPassword());
        Assert.assertEquals(userExpected.getId(), userActual.getId());
	}
}
