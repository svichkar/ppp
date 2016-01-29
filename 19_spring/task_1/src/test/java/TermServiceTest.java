import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.Term;
import com.nixsolutions.studentgrade.service.TermService;
import junit.framework.Assert;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
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
 * Created by konstantin on 1/27/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup(value = "/dbunit/term/term-data.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/term/term-data.xml")
public class TermServiceTest {

    @Autowired
    private TermService termService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setTermService(TermService termService) {
        this.termService = termService;
    }

    @Test
    public void findAllShouldReturnAllEntries() throws Exception {

        List<Term> termList = termService.findAll();
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/term/term-data-find-all.xml"));
        ITable expectedTable = dataSet.getTable("term");
        Assert.assertEquals(termList.size(), expectedTable.getRowCount());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/term/term-data-create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "term")
    public void createShouldAddNewEntry() throws Exception {

        Term term = new Term();
        term.setTermName("fifth");
        termService.create(term);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/term/term-data-update.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "term")
    public void updateShouldModifySpecifiedEntry() throws Exception {

        Term term = termService.findAll().get(1);
        term.setTermName("after first");
        termService.update(term);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/term/term-data-delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "term")
    public void deleteShouldRemoveSpecifiedEntry() throws Exception {

        Term term = termService.findAll().get(3);
        termService.delete(term);
    }

    @Test
    public void findByIdShouldReturnSpecifiedEntry() throws Exception {

        Term term = termService.findAll().get(1);
        Term foundTerm = termService.findById(term.getTermId());

        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/term/term-data-find-by-id.xml"));
        ITable expectedTable = dataSet.getTable("term");
        Assert.assertEquals(foundTerm.getTermName(), expectedTable.getValue(0, "term_name"));
    }

    @Test
    public void findByNameShouldReturnSpecifiedEntry() throws Exception {

        Term term = termService.findAll().get(1);
        Term foundTerm = termService.findByName(term.getTermName());

        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/term/term-data-find-by-name.xml"));
        ITable expectedTable = dataSet.getTable("term");
        Assert.assertEquals(foundTerm.getTermName(), expectedTable.getValue(0, "term_name"));
    }
}
