import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.entity.Role;
import config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by konstantin on 12/23/2015.
 */
public class RoleDaoTest extends DBUnitConfig {

    private RoleDao roleDao;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("role/role-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
        roleDao = daoFactory.getRoleDao();
    }

    public RoleDaoTest(String name) {
        super(name);
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<Role> actualList = roleDao.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("role/role-data-find-all.xml"));
        ITable expTable = expected.getTable("role");
        ITable actTable = tester.getConnection().createTable("role");

        String[] ignore = new String[]{"role_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        Role newRole = new Role();
        newRole.setRoleId(3L);
        newRole.setRoleName("guest");
        roleDao.create(newRole);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("role/role-data-create.xml"));
        ITable expTable = expected.getTable("role");

        ITable actTable = tester.getConnection().createTable("role");
        String[] ignore = new String[]{"role_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        Role update = roleDao.findAll().get(1);
        update.setRoleName("teacher");
        roleDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("role/role-data-update.xml"));
        ITable expTable = expected.getTable("role");

        ITable actTable = tester.getConnection().createTable("role");
        String[] ignore = new String[]{"role_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
        Assert.assertEquals(update.getRoleName(), actTable.getValue(1, "role_name"));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        Role delete = roleDao.findAll().get(0);
        roleDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("role/role-data-delete.xml"));
        ITable expTable = expected.getTable("role");

        ITable actTable = tester.getConnection().createTable("role");
        String[] ignore = {"role_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        Long roleId = roleDao.findAll().get(1).getRoleId();
        Role foundRole = roleDao.findById(roleId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("role/role-data-find-by-id.xml"));
        ITable expTable = expected.getTable("role");

        String sqlQuery = String.format("SELECT * FROM role WHERE role_id = %d", roleId);
        String[] ignore = {"role_id"};
        Assertion.assertEqualsByQuery(expTable, getConnection(), "role", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "role_name"), foundRole.getRoleName());
    }

    @Test
    public void testFindByNameShouldReturnRequestedEntity() throws Exception {

        String roleName = roleDao.findAll().get(0).getRoleName();
        Role foundRole = roleDao.findByName(roleName);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("role/role-data-find-by-name.xml"));
        ITable expTable = expected.getTable("role");

        String sqlQuery = String.format("SELECT * FROM role WHERE role_name = '%s'", roleName);
        String[] ignore = {"role_id"};
        Assertion.assertEqualsByQuery(expTable, getConnection(), "role", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "role_name"), foundRole.getRoleName());
    }

}
