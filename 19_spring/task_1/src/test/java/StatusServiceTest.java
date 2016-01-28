import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.Status;
import com.nixsolutions.studentgrade.service.StatusService;
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
@DatabaseSetup(value = "/dbunit/status/status-data.xml")
@DatabaseTearDown(type = DatabaseOperation.CLEAN_INSERT, value = "/dbunit/status/status-data.xml")
public class StatusServiceTest {

    @Autowired
    private StatusService statusService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }

    @Test
    public void findAllShouldReturnAllRows() throws Exception {

        List<Status> actualList = statusService.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/status/status-data-find-all.xml"));
        ITable expTable = expected.getTable("status");
        ITable actTable = databaseTester.getConnection().createTable("status");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/status/status-data-create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "status")
    public void createShouldAddNewEntity() throws Exception {

        Status newStatus = new Status();
        newStatus.setStatusName("other");
        statusService.create(newStatus);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/status/status-data-update.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "status")
    public void updateShouldModifySpecifiedEntity() throws Exception {

        Status update = statusService.findAll().get(1);
        update.setStatusName("waiting");
        statusService.update(update);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/status/status-data-delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "status")
    public void deleteShouldRemoveSpecifiedEntity() throws Exception {

        Status delete = statusService.findAll().get(1);
        statusService.delete(delete);
    }

    @Test
    public void findByIdShouldReturnRequestedEntity() throws Exception {

        Long statusId = statusService.findAll().get(2).getStatusId();
        Status foundStatus = statusService.findById(statusId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbunit/status/status-data-find-by-id.xml"));
        ITable expTable = expected.getTable("status");

        String sqlQuery = String.format("SELECT * FROM status WHERE status_id = %d", statusId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "status", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "status_name"), foundStatus.getStatusName());
    }
}
