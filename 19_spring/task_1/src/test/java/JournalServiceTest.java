import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.*;
import com.nixsolutions.studentgrade.service.JournalService;
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
import java.sql.Date;
import java.util.List;

/**
 * Created by konstantin on 1/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup(value = "/dbunit/journal/journal-data.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/journal/journal-data.xml")
public class JournalServiceTest {

    @Autowired
    private JournalService journalService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setJournalService(JournalService journalService) {

        this.journalService = journalService;
    }

    @Test
    public void findAllShouldReturnAllRows() throws Exception {

        List<Journal> actualList = journalService.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/journal/journal-data-find-all.xml"));
        ITable expTable = expected.getTable("journal");
        ITable actTable = databaseTester.getConnection().createTable("journal");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/journal/journal-data-create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "journal")
    public void createShouldAddNewEntity() throws Exception {

        Journal newJournal = new Journal();
        Grade grade = new Grade();
        grade.setGradeId(3L);
        grade.setGradeName("average");
        newJournal.setGrade(grade);
        Subject subject = new Subject();
        subject.setSubjectId(4L);
        subject.setSubjectName("Databases");
        Term term = new Term();
        term.setTermId(1L);
        term.setTermName("first");
        subject.setTerm(term);
        newJournal.setSubject(subject);
        Student student = new Student();
        student.setStudentId(3L);
        student.setFirstName("Andrew");
        student.setLastName("Galvan");
        student.setAdmissionDate(Date.valueOf("2015-05-10"));
        Status status = new Status();
        status.setStatusId(1L);
        status.setStatusName("active");
        student.setStatus(status);
        StudentGroup group = new StudentGroup();
        group.setGroupId(3L);
        group.setGroupName("java 15-3");
        student.setStudentGroup(group);
        student.setTerm(term);
        newJournal.setStudent(student);
        journalService.create(newJournal);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/journal/journal-data-update.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "journal")
    public void updateShouldModifySpecifiedEntity() throws Exception {

        Journal update = journalService.findAll().get(3);
        Grade grade = new Grade();
        grade.setGradeId(4L);
        grade.setGradeName("good");
        update.setGrade(grade);
        journalService.update(update);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/journal/journal-data-delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "journal")
    public void deleteShouldRemoveSpecifiedEntity() throws Exception {

        Journal delete = journalService.findAll().get(4);
        journalService.delete(delete);
    }

    @Test
    public void findByIdShouldReturnRequestedEntity() throws Exception {

        Long journalId = journalService.findAll().get(6).getJournalId();
        Journal foundJournal = journalService.findById(journalId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/journal/journal-data-find-by-id.xml"));
        ITable expTable = expected.getTable("journal");

        String sqlQuery = String.format("SELECT * FROM journal WHERE journal_id = %d", journalId);
        String[] ignore = new String[]{"journal_id"};
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "journal", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "student_id"), String.valueOf(foundJournal.getStudent().getStudentId()));
        Assert.assertEquals(expTable.getValue(0, "subject_id"), String.valueOf(foundJournal.getSubject().getSubjectId()));
        Assert.assertEquals(expTable.getValue(0, "grade_id"), String.valueOf(foundJournal.getGrade().getGradeId()));
    }

}
