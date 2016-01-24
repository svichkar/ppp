import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;
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
public class UserDaoTest extends DBUnitConfig {

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("user/user-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
        userDao = daoFactory.getUserDao();
    }

    public UserDaoTest(String name) {
        super(name);
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<User> actualList = userDao.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("user/user-data-find-all.xml"));
        ITable expTable = expected.getTable("user");
        ITable actTable = tester.getConnection().createTable("user");

        String[] ignore = new String[]{"user_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        User newUser = new User();
        newUser.setFirstName("Serg");
        newUser.setLastName("Kick");
        newUser.setUserPassword("654");
        newUser.setLogin("serg");
        newUser.setEmail("serg@gmail.com");
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("admin");
        newUser.setRole(role);
        userDao.create(newUser);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("user/user-data-create.xml"));
        ITable expTable = expected.getTable("user");

        ITable actTable = tester.getConnection().createTable("user");
        String[] ignore = new String[]{"user_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        User update = userDao.findAll().get(1);
        update.setEmail("teacher@gmail.com");
        userDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("user/user-data-update.xml"));
        ITable expTable = expected.getTable("user");

        ITable actTable = tester.getConnection().createTable("user");
        String[] ignore = new String[]{"user_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
        Assert.assertEquals(update.getEmail(), actTable.getValue(1, "email"));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        User delete = userDao.findAll().get(0);
        userDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("user/user-data-delete.xml"));
        ITable expTable = expected.getTable("user");

        ITable actTable = tester.getConnection().createTable("user");
        String[] ignore = {"user_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        Long userId = userDao.findAll().get(1).getUserId();
        User foundUser = userDao.findById(userId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("user/user-data-find-by-id.xml"));
        ITable expTable = expected.getTable("user");

        String sqlQuery = String.format("SELECT * FROM user WHERE user_id = %d", userId);
        String[] ignore = {"user_id"};
        Assertion.assertEqualsByQuery(expTable, getConnection(), "user", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "first_name"), foundUser.getFirstName());
    }
    }
