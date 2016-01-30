import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.StudentGroup;
import com.nixsolutions.studentgrade.service.StudentGroupService;
import org.dbunit.Assertion;
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

import javax.sql.DataSource;
import java.util.List;


/**
 * Created by konstantin on 1/28/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = "classpath:/test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup(value = "/dbunit/group/group-data.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/group/group-data.xml")
public class StudentGroupServiceTest {

    @Autowired
    private StudentGroupService groupService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setGroupService(StudentGroupService groupService) {

        this.groupService = groupService;
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<StudentGroup> actualList = groupService.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/group/group-data-find-all.xml"));
        ITable expTable = expected.getTable("student_group");
        ITable actTable = databaseTester.getConnection().createTable("student_group");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/group/group-data-create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "student_group")
    public void createShouldAddNewEntity() throws Exception {

        StudentGroup group = new StudentGroup();
        group.setGroupName("java 16-1");
        groupService.create(group);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/group/group-data-update.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "student_group")
    public void updateShouldModifySpecifiedEntity() throws Exception {

        StudentGroup update = new StudentGroup();
        update.setGroupId(4L);
        update.setGroupName("java 16-2");
        groupService.update(update);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/group/group-data-delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "student_group")
    public void deleteShouldRemoveSpecifiedEntity() throws Exception {

        StudentGroup delete = new StudentGroup();
        delete.setGroupId(4L);
        delete.setGroupName("java 15-4");
        groupService.delete(delete);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        Long groupId = 5L;
        StudentGroup foundTerm = groupService.findById(groupId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/group/group-data-find-by-id.xml"));
        ITable expTable = expected.getTable("student_group");

        String sqlQuery = String.format("SELECT * FROM student_group WHERE group_id = %d", groupId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "student_group", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "group_name"), foundTerm.getGroupName());
    }
}