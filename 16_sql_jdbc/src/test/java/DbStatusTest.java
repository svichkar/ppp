import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.StatusDao;
import com.nixsolutions.studentgrade.entity.Status;
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
public class DbStatusTest extends DBUnitConfig {


    StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
    StatusDao statusDao = daoFactory.getStatusDao();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("status/status-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public DbStatusTest(String name) {
        super(name);
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<Status> actualList = statusDao.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("status/status-data-find-all.xml"));
        ITable expTable = expected.getTable("status");
        ITable actTable = tester.getConnection().createTable("status");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        Status newStatus = new Status(5, "other");
        statusDao.create(newStatus);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("status/status-data-create.xml"));
        ITable expTable = expected.getTable("status");

        ITable actTable = tester.getConnection().createTable("status");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        Status update = new Status(2, "academic vacation");
        update.setStatusName("waiting");
        statusDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("status/status-data-update.xml"));
        ITable expTable = expected.getTable("status");

        ITable actTable = tester.getConnection().createTable("status");
        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(update.getStatusName(), actTable.getValue(1, "status_name"));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        Status delete = new Status(2, "academic vacation");
        statusDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("status/status-data-delete.xml"));
        ITable expTable = expected.getTable("status");

        ITable actTable = tester.getConnection().createTable("status");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        int statusId = 3;
        Status foundStatus = statusDao.findById(statusId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("status/status-data-find-by-id.xml"));
        ITable expTable = expected.getTable("status");

        String sqlQuery = String.format("SELECT * FROM status WHERE status_id = %d", statusId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, getConnection(), "status", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "status_name"), foundStatus.getStatusName());
    }
}
