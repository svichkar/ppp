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
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

public class RoleTest extends DBUnitConfig {
	@Autowired
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
        Role role = new Role();
        role.setRoleName("Student");
		roleDao.create(role);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Role/RoleCreate.xml"));
		ITable expectedTable = expectedData.getTable("role");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("role");
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTale, new String[]{"role_id"});
	}

	@Test
	public void testDelete() throws Exception {
        Role role = roleDao.getByRoleId(3);
        roleDao.delete(role);
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new FileInputStream("src/test/resources/Role/RoleDelete.xml"));
        ITable expectedTable = expectedData.getTable("role");
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTale = actualData.getTable("role");
        Assertion.assertEquals(expectedTable, actualTale);
    }

	@Test
	public void testUpdate() throws Exception {
        Role role = roleDao.getByRoleId(1);
        role.setRoleName("Administrator");
		roleDao.update(role);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/resources/Role/RoleUpdate.xml"));
		ITable expectedTable = expectedData.getTable("role");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable actualTale = actualData.getTable("role");
		Assertion.assertEquals(expectedTable, actualTale);
	}
	
	@Test
	public void testGetById() throws Exception {
        Role roleExpected = new Role();
        roleExpected.setRoleId(3);
        roleExpected.setRoleName("Teacher");
		//Role roleExpected = new Role(3, "Teacher");
		Role roleActual =  roleDao.getByRoleId(3);
		Assert.assertEquals(roleExpected.getRoleName(), roleActual.getRoleName());
		Assert.assertEquals(roleExpected.getRoleId(), roleActual.getRoleId());
	}
	
	@Test
	public void testGetByRoleName() throws Exception {
        Role roleExpected = new Role();
        roleExpected.setRoleId(1);
        roleExpected.setRoleName("Admin");
		//Role roleExpected = new Role(1, "Admin");
		Role roleActual =  roleDao.getByRoleName("Admin");
		Assert.assertEquals(roleExpected.getRoleName(), roleActual.getRoleName());
		Assert.assertEquals(roleExpected.getRoleId(), roleActual.getRoleId());
	}
}
