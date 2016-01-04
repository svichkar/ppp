import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.StudentGroupDao;
import com.nixsolutions.studentgrade.entity.StudentGroup;
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
 * Created by svichkar on 1/4/2016.
 */
public class DbStudentGroupTest extends DBUnitConfig {


    StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
    StudentGroupDao groupDao = daoFactory.getStudentGroupDao();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("group/group-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public DbStudentGroupTest(String name) {
        super(name);
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<StudentGroup> actualList = groupDao.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("group/group-data-find-all.xml"));
        ITable expTable = expected.getTable("student_group");
        ITable actTable = tester.getConnection().createTable("student_group");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        StudentGroup newTerm = new StudentGroup(6, "java 16-1");
        groupDao.create(newTerm);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("group/group-data-create.xml"));
        ITable expTable = expected.getTable("student_group");

        ITable actTable = tester.getConnection().createTable("student_group");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        StudentGroup update = new StudentGroup(4, "java 15-4");
        update.setGroupName("java 16-2");
        groupDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("group/group-data-update.xml"));
        ITable expTable = expected.getTable("student_group");

        ITable actTable = tester.getConnection().createTable("student_group");
        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(update.getGroupName(), actTable.getValue(3, "group_name"));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        StudentGroup delete = new StudentGroup(4, "java 15-4");
        groupDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("group/group-data-delete.xml"));
        ITable expTable = expected.getTable("student_group");

        ITable actTable = tester.getConnection().createTable("student_group");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        int groupId = 5;
        StudentGroup foundTerm = groupDao.findById(groupId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("group/group-data-find-by-id.xml"));
        ITable expTable = expected.getTable("student_group");

        String sqlQuery = String.format("SELECT * FROM student_group WHERE group_id = %d", groupId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, getConnection(), "student_group", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "group_name"), foundTerm.getGroupName());
    }
}
