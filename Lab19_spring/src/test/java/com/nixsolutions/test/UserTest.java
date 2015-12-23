package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

public class UserTest extends DBUnitConfig {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	public UserTest() throws SQLException, ClassNotFoundException {
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/User/User.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
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
        User user = new User();
        user.setUserName("teacher2");
        user.setPassword("1111");
        user.setEmail("teacher2@test.com");
        user.setRole(roleDao.getByRoleId(3));
		userDao.create(user);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/User/UserCreate.xml"));
		ITable expectedTable = expectedData.getTable("user");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("user");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "user_id" });
	}

	@Test
	public void testDelete() throws Exception {
        User user = new User();
        user.setUserId(3);
        user.setUserName("teacher2");
        user.setPassword("1111");
        user.setEmail("teacher2@test.com");
        user.setRole(roleDao.getByRoleId(3));
		userDao.delete(user);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/User/UserDelete.xml"));
		ITable expectedTable = expectedData.getTable("user");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("user");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
        User user = new User();
        user.setUserId(3);
        user.setUserName("teacher");
        user.setPassword("1111");
        user.setEmail("teacher3@test.com");
        user.setRole(roleDao.getByRoleId(3));
		userDao.update(user);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/User/UserUpdate.xml"));
		ITable expectedTable = expectedData.getTable("user");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("user");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
        User userExpected = new User();
        userExpected.setUserId(3);
        userExpected.setUserName("teacher");
        userExpected.setPassword("1111");
        userExpected.setEmail("teacher@test.com");
        userExpected.setRole(roleDao.getByRoleId(3));
		//User userExpected = new User(3, "teacher", "1111", "teacher@test.com", 3);
		User userActual =  userDao.getByUserId(3);
		Assert.assertEquals(userExpected.getEmail(), userActual.getEmail());
		Assert.assertEquals(userExpected.getUserName(), userActual.getUserName());
		Assert.assertEquals(userExpected.getRole().getRoleId(), userActual.getRole().getRoleId());
		Assert.assertEquals(userExpected.getPassword(), userActual.getPassword());
		Assert.assertEquals(userExpected.getUserId(), userActual.getUserId());
	}
	
	@Test
	public void testGetByUserName() throws Exception {
        User userExpected = new User();
        userExpected.setUserId(3);
        userExpected.setUserName("teacher");
        userExpected.setPassword("1111");
        userExpected.setEmail("teacher@test.com");
        userExpected.setRole(roleDao.getByRoleId(3));
		//User userExpected = new User(3, "teacher", "1111", "teacher@test.com", 3);
		User userActual =  userDao.getByUserName("teacher");
        Assert.assertEquals(userExpected.getEmail(), userActual.getEmail());
        Assert.assertEquals(userExpected.getUserName(), userActual.getUserName());
        Assert.assertEquals(userExpected.getRole().getRoleId(), userActual.getRole().getRoleId());
        Assert.assertEquals(userExpected.getPassword(), userActual.getPassword());
        Assert.assertEquals(userExpected.getUserId(), userActual.getUserId());
	}
}
