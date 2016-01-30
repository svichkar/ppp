import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.Status;
import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.model.StudentGroup;
import com.nixsolutions.studentgrade.model.Term;
import com.nixsolutions.studentgrade.service.StudentService;
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
import java.sql.Date;
import java.util.List;

/**
 * Created by svichkar on 1/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = "classpath:/test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup(value = "/dbunit/student/student-data.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/student/student-data.xml")
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setStudentService(StudentService studentService) {

        this.studentService = studentService;
    }

    @Test
    public void findAllShouldReturnAllRows() throws Exception {

        List<Student> actualList = studentService.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/student/student-data-find-all.xml"));
        ITable expTable = expected.getTable("student");
        ITable actTable = databaseTester.getConnection().createTable("student");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/student/student-data-create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "student")
    public void createShouldAddNewEntity() throws Exception {

        Student newStudent = new Student();
        newStudent.setFirstName("Mary");
        newStudent.setLastName("Kane");
        StudentGroup group = new StudentGroup();
        group.setGroupId(1L);
        group.setGroupName("java 15-1");
        newStudent.setStudentGroup(group);
        newStudent.setAdmissionDate(Date.valueOf("2015-06-02"));
        Status status = new Status();
        status.setStatusId(1L);
        status.setStatusName("active");
        newStudent.setStatus(status);
        Term term = new Term();
        term.setTermId(1L);
        term.setTermName("first");
        newStudent.setTerm(term);
        studentService.create(newStudent);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/student/student-data-update.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "student")
    public void updateShouldModifySpecifiedEntity() throws Exception {

        Student update = new Student();
        update.setStudentId(1L);
        update.setFirstName("Alex");
        update.setLastName("Ross");
        StudentGroup group = new StudentGroup();
        group.setGroupId(1L);
        group.setGroupName("java 15-1");
        update.setStudentGroup(group);
        update.setAdmissionDate(Date.valueOf("2015-03-25"));
        Status status = new Status();
        status.setStatusId(4L);
        status.setStatusName("graduated");
        update.setStatus(status);
        Term term = new Term();
        term.setTermId(1L);
        term.setTermName("first");
        update.setTerm(term);
        studentService.update(update);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/student/student-data-delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "student")
    public void deleteShouldRemoveSpecifiedEntity() throws Exception {

        Student delete = new Student();
        delete.setStudentId(3L);
        delete.setFirstName("Andrew");
        delete.setLastName("Galvan");
        delete.setAdmissionDate(Date.valueOf("2015-05-10"));
        StudentGroup group = new StudentGroup();
        group.setGroupId(3L);
        group.setGroupName("java 15-3");
        delete.setStudentGroup(group);
        Status status = new Status();
        status.setStatusId(1L);
        status.setStatusName("active");
        delete.setStatus(status);
        Term term = new Term();
        term.setTermId(1L);
        term.setTermName("first");
        delete.setTerm(term);
        studentService.delete(delete);
    }

    @Test
    public void findByIdShouldReturnRequestedEntity() throws Exception {

        Long studentId = 2L;
        Student foundStudent = studentService.findById(studentId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/student/student-data-find-by-id.xml"));
        ITable expTable = expected.getTable("student");

        String sqlQuery = String.format("SELECT * FROM student WHERE student_id = %d", studentId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "student", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "last_name"), foundStudent.getLastName());
    }

    @Test
    public void findByLastNameShouldReturnRequestedEntities() throws Exception {

        String lastName = "galvan";
        List<Student> studentList = studentService.findByLastName(lastName);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/student/student-data-find-by-last-name.xml"));
        ITable expTable = expected.getTable("student");

        String sqlQuery = String.format("SELECT * FROM student WHERE lower(last_name) = lower('%s')", lastName);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "student", sqlQuery, ignore);
        Assert.assertEquals(expTable.getRowCount(), studentList.size());
        for (Student s : studentList) {
            Assert.assertEquals(s.getLastName().toLowerCase(), lastName.toLowerCase());
        }
    }

    @Test
    public void findByGroupIdShouldReturnRequestedEntities() throws Exception {

        Long id = 2L;
        List<Student> studentList = studentService.findByGroupId(id);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/student/student-data-find-by-group-id.xml"));
        ITable expTable = expected.getTable("student");

        String sqlQuery = String.format("SELECT * FROM student WHERE group_id = %d", id);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "student", sqlQuery, ignore);
        Assert.assertEquals(expTable.getRowCount(), studentList.size());
        for (Student s : studentList) {
            Assert.assertEquals(s.getStudentGroup().getGroupId(), id);
        }
    }

    @Test
    public void findByNameAndLastNameShouldReturnRequestedEntity() throws Exception {

        String name = "Nick";
        String lastName = "Dodson";
        Student student = studentService.findByNameAndLastName(name, lastName);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/student/student-data-find-by-name-and-last-name.xml"));
        ITable expTable = expected.getTable("student");

        String sqlQuery = String.format("SELECT * FROM student WHERE first_name = '%s' and last_name = '%s'", name, lastName);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "student", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "first_name"), name);
        Assert.assertEquals(expTable.getValue(0, "last_name"), lastName);

        studentService.findByLastNameAndGroupId("", 1L);
    }

    @Test
    public void findByLastNameAndGroupIdShouldReturnRequestedEntities() throws Exception {

        String lastName = "dodson";
        Long groupId = 2L;
        List<Student> studentList = studentService.findByLastNameAndGroupId(lastName, groupId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/student/student-data-find-by-last-name-and-group.xml"));
        ITable expTable = expected.getTable("student");

        String sqlQuery = String.format("SELECT * FROM student WHERE  lower(last_name) = lower('%s') AND group_id = %d",
                lastName, groupId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "student", sqlQuery, ignore);
        Assert.assertEquals(expTable.getRowCount(), studentList.size());
        for (Student s : studentList) {
            Assert.assertEquals(s.getStudentGroup().getGroupId(), groupId);
            Assert.assertEquals(s.getLastName().toLowerCase(), lastName.toLowerCase());
        }

    }
}
