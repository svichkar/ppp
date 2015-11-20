package com.nixsolutions.test;

import com.nixsolutions.config.DBUnitConfig;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;
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

public class RoleTest extends DBUnitConfig {
	private RoleDao roleDao;

	public RoleTest(String name) throws SQLException, ClassNotFoundException {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		FlatXmlDataSetBuilder flatXmlProducer = new FlatXmlDataSetBuilder();
		flatXmlProducer.setColumnSensing(false);
		beforeData = flatXmlProducer.build(new FileInputStream("src/test/resources/Role/Role.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
		roleDao = daoFactory.getRoleDao();
	}

	@Test
	public void testGetAll() throws Exception {
		List<Role> roles = roleDao.getAll();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Role/Role.xml"));
		ITable expectedTable = expectedData.getTable("role");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("role");
		Assertion.assertEquals(expectedTable, actualTale);
		Assert.assertEquals(expectedData.getTable("role").getRowCount(), roles.size());
	}

	@Test
	public void testCreate() throws Exception {
		roleDao.create("Student");
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Role/RoleCreate.xml"));
		ITable expectedTable = expectedData.getTable("role");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("role");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[] { "role_id" });
	}

	@Test
	public void testDelete() throws Exception {
		roleDao.delete(new Role(3, "Teacher"));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Role/RoleDelete.xml"));
		ITable expectedTable = expectedData.getTable("role");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("role");
		Assertion.assertEquals(expectedTable, actualTale);
	}

	@Test
	public void testUpdate() throws Exception {
		roleDao.update(new Role(1, "Administrator"));
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Role/RoleUpdate.xml"));
		ITable expectedTable = expectedData.getTable("role");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("role");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
		Role roleExpected = new Role(3, "Teacher");
		Role roleActual =  roleDao.getByRoleId(3);
		Assert.assertEquals(roleExpected.getRoleName(), roleActual.getRoleName());
		Assert.assertEquals(roleExpected.getId(), roleActual.getId());
	}
	
	@Test
	public void testGetByRoleName() throws Exception {
		Role roleExpected = new Role(1, "Admin");
		Role roleActual =  roleDao.getByRoleName("Admin");
		Assert.assertEquals(roleExpected.getRoleName(), roleActual.getRoleName());
		Assert.assertEquals(roleExpected.getId(), roleActual.getId());
	}
}
