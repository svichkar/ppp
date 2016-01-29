import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.Role;
import com.nixsolutions.studentgrade.service.RoleService;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup(value = "/dbunit/role/role-data.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/role/role-data.xml")
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setTermService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    public void findAllShouldReturnAllRows() throws Exception {

        List<Role> roleList = roleService.findAll();
        IDataSet expected = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/role/role-data-find-all.xml"));
        ITable expTable = expected.getTable("role");
        ITable actTable = databaseTester.getConnection().createTable("role");

        String[] ignore = new String[]{"role_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
        Assert.assertEquals(expTable.getRowCount(), roleList.size());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/role/role-data-create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "role")
    public void createShouldAddNewEntity() throws Exception {

        Role newRole = new Role();
        newRole.setRoleId(3L);
        newRole.setRoleName("guest");
        roleService.create(newRole);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/role/role-data-update.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "role")
    public void updateShouldModifySpecifiedEntity() throws Exception {

        Role update = new Role();
        update.setRoleId(2L);
        update.setRoleName("teacher");
        roleService.update(update);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/role/role-data-delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "role")
    public void deleteShouldRemoveSpecifiedEntity() throws Exception {

        Role delete = new Role();
        delete.setRoleId(1L);
        delete.setRoleName("admin");
        roleService.delete(delete);
    }

    @Test
    public void findByIdShouldReturnRequestedEntity() throws Exception {

        Long roleId = 2L;
        Role foundRole = roleService.findById(roleId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/role/role-data-find-by-id.xml"));
        ITable expTable = expected.getTable("role");

        String sqlQuery = String.format("SELECT * FROM role WHERE role_id = %d", roleId);
        String[] ignore = {"role_id"};
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "role", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "role_name"), foundRole.getRoleName());
    }

    @Test
    public void testFindByNameShouldReturnRequestedEntity() throws Exception {

        String roleName = "admin";
        Role foundRole = roleService.findByName(roleName);

        IDataSet expected = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/role/role-data-find-by-name.xml"));
        ITable expTable = expected.getTable("role");

        String sqlQuery = String.format("SELECT * FROM role WHERE role_name = '%s'", roleName);
        String[] ignore = {"role_id"};
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "role", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "role_name"), foundRole.getRoleName());
    }
}
