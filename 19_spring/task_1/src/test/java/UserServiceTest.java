import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.Role;
import com.nixsolutions.studentgrade.model.User;
import com.nixsolutions.studentgrade.service.UserService;
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
@DatabaseSetup(value = "/dbunit/user/user-data.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/user/user-data.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void findAllShouldReturnAllRows() throws Exception {

        List<User> userList = userService.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/user/user-data-find-all.xml"));
        ITable expectedTable = expected.getTable("user");
        Assert.assertEquals(expectedTable.getRowCount(), userList.size());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/user/user-data-create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "user")
    public void createShouldAddNewEntry() throws Exception {

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
        userService.create(newUser);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/user/user-data-update.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "user")
    public void updateShouldModifySpecifiedEntry() throws Exception {

        User update = userService.findAll().get(1);
        update.setEmail("teacher@gmail.com");
        userService.update(update);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/user/user-data-delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "user")
    public void deleteShouldRemoveSpecifiedEntity() throws Exception {

        User delete = userService.findAll().get(0);
        userService.delete(delete);
    }

    @Test
    public void findByIdShouldReturnRequestedEntity() throws Exception {

        Long userId = userService.findAll().get(1).getUserId();
        User foundUser = userService.findById(userId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/user/user-data-find-by-id.xml"));
        ITable expTable = expected.getTable("user");

        String sqlQuery = String.format("SELECT * FROM user WHERE user_id = %d", userId);
        String[] ignore = {"user_id"};
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "user", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "first_name"), foundUser.getFirstName());
    }

    @Test
    public void findByLoginShouldReturnRequestedEntity() throws Exception {

        String login = userService.findAll().get(1).getLogin();
        User foundUser = userService.findByLogin(login);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/user/user-data-find-by-login.xml"));
        ITable expTable = expected.getTable("user");

        String sqlQuery = String.format("SELECT * FROM user WHERE login = '%s'", login);
        String[] ignore = {"user_id"};
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "user", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "login"), foundUser.getLogin());
    }

    @Test
    public void getUserByLoginAndPasswordShouldReturnRequestedEntity() throws Exception {

        String login = userService.findAll().get(1).getLogin();
        String pass = userService.findAll().get(1).getUserPassword();
        User foundUser = userService.getUserByLoginAndPassword(login, pass);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/user/user-data-find-by-login-and-pass.xml"));
        ITable expTable = expected.getTable("user");

        String sqlQuery = String.format("SELECT * FROM user WHERE login = '%s' AND password = '%s'", login, pass);
        String[] ignore = {"user_id"};
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "user", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "login"), foundUser.getLogin());
        Assert.assertEquals(expTable.getValue(0, "password"), foundUser.getUserPassword());
    }

    @Test
    public void validateUserShouldReturnRightCondition() throws Exception {

        String login = userService.findAll().get(1).getLogin();
        boolean validUser = userService.validateUser(login);
        boolean invalidUser = userService.validateUser("doesn't exist");

        Assert.assertTrue(validUser == true);
        Assert.assertTrue(invalidUser == false);
    }
}
