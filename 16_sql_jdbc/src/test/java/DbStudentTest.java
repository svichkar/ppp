import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.entity.Student;
import config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

/**
 * Created by svichkar on 1/4/2016.
 */
public class DbStudentTest extends DBUnitConfig {


    StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
    StudentDao studentDao = daoFactory.getStudentDao();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("student/student-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public DbStudentTest(String name) {
        super(name);
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<Student> actualList = studentDao.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("student/student-data-find-all.xml"));
        ITable expTable = expected.getTable("student");
        ITable actTable = tester.getConnection().createTable("student");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        Student newStudent = new Student(6, "Mary", "Kane", 1, Date.valueOf("2015-06-02"), 1, 1);
        studentDao.create(newStudent);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("student/student-data-create.xml"));
        ITable expTable = expected.getTable("student");

        ITable actTable = tester.getConnection().createTable("student");
        String[] ignore = new String[]{"student_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        Student update = new Student(1, "Alex", "Ross", 1, Date.valueOf("2015-03-25"), 1, 1);
        update.setStatusId(4);
        studentDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("student/student-data-update.xml"));
        ITable expTable = expected.getTable("student");

        ITable actTable = tester.getConnection().createTable("student");
        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(update.getStatusId(), actTable.getValue(0, "status_id"));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        Student delete = new Student(3, "Andrew", "Galvan", 3, Date.valueOf("2015-05-10"), 1, 1);
        studentDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("student/student-data-delete.xml"));
        ITable expTable = expected.getTable("student");

        ITable actTable = tester.getConnection().createTable("student");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        int studentId = 2;
        Student foundStudent = studentDao.findById(studentId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("student/student-data-find-by-id.xml"));
        ITable expTable = expected.getTable("student");

        String sqlQuery = String.format("SELECT * FROM student WHERE student_id = %d", studentId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, getConnection(), "student", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "last_name"), foundStudent.getLastName());
    }
}
