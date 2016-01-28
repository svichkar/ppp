import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.Subject;
import com.nixsolutions.studentgrade.model.Term;
import com.nixsolutions.studentgrade.service.SubjectService;
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
@DatabaseSetup(value = "/subject/subject-data.xml")
@DatabaseTearDown(type = DatabaseOperation.CLEAN_INSERT, value = "/subject/subject-data.xml")
public class SubjectServiceTest {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setSubjectService(SubjectService subjectService) {

        this.subjectService = subjectService;
    }


    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<Subject> actualList = subjectService.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("subject/subject-data-find-all.xml"));
        ITable expTable = expected.getTable("subject");
        ITable actTable = databaseTester.getConnection().createTable("subject");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    @ExpectedDatabase(value = "/subject/subject-data-create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "subject")
    public void createShouldAddNewEntity() throws Exception {

        Subject newSubject = new Subject();
        newSubject.setSubjectName("Chemistry");
        Term term = new Term();
        term.setTermId(2L);
        term.setTermName("second");
        newSubject.setTerm(term);
        subjectService.create(newSubject);
    }

    @Test
    @ExpectedDatabase(value = "/subject/subject-data-update.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "subject")
    public void updateShouldModifySpecifiedEntity() throws Exception {

        Subject update = subjectService.findAll().get(0);
        Term term = new Term();
        term.setTermId(2L);
        term.setTermName("second");
        update.setTerm(term);
        subjectService.update(update);
    }

    @Test
    @ExpectedDatabase(value = "/subject/subject-data-delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "subject")
    public void deleteShouldRemoveSpecifiedEntity() throws Exception {

        Subject delete = subjectService.findAll().get(2);
        subjectService.delete(delete);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        Long subjectId = subjectService.findAll().get(3).getSubjectId();
        Subject foundSubject = subjectService.findById(subjectId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("subject/subject-data-find-by-id.xml"));
        ITable expTable = expected.getTable("subject");

        String sqlQuery = String.format("SELECT * FROM subject WHERE subject_id = %d", subjectId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "subject", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "subject_name"), foundSubject.getSubjectName());
    }

}
