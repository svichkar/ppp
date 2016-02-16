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

import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration("classpath:test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("/dbunit/role/roleInit.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/role/roleInit.xml")
public class RoleServiceTest {
	@Autowired
	private RoleService roleService;

	@Autowired
	private IDatabaseTester databaseTester;

	@Test
	@ExpectedDatabase(value = "/dbunit/role/roleCreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "role")
	public void shouldCreateRole() {
		Role roleNew = new Role();
		roleNew.setRoleName("basic");
		roleService.createRole(roleNew);
	}
	
	@Test
	@ExpectedDatabase(value = "/dbunit/role/roleUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "role")
	public void shouldUpdateRole() {
        Role roleUpdate = roleService.findRoleById(Long.valueOf(2));
        roleUpdate.setRoleName("system_manager");
        roleService.updateRole(roleUpdate);
	}

	@Test
	@ExpectedDatabase(value = "/dbunit/role/roleDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "role")
	public void shouldDeleteRole() {
		roleService.deleteRole(roleService.findRoleById(Long.valueOf(2)));
	}

	@Test
	public void shouldGetAllRoles() throws SQLException, Exception {	        
		List<Role> roles = roleService.findAllRoles();
		IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/role/roleInit.xml"));
		ITable expected = ds.getTable("role");
		ITable actual = databaseTester.getConnection().createTable("role");
		Assertion.assertEquals(expected, actual);
		Assert.assertEquals(expected.getRowCount(), roles.size());
	}

	@Test
	public void shouldFindRoleById() throws DatabaseUnitException, SQLException, Exception {
        Role roleTest = roleService.findRoleById(Long.valueOf(2));
        IDataSet ds = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("/dbunit/role/roleFindById.xml"));
        ITable expected = ds.getTable("role");
        String sqlQuery = String.format("SELECT * FROM role WHERE role_id = %d", Long.valueOf(2));
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expected, databaseTester.getConnection(), "role", sqlQuery, ignore);
        Assert.assertEquals(expected.getValue(0, "role_name"), roleTest.getRoleName());
	}

}
