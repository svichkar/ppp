import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.JournalDao;
import com.nixsolutions.studentgrade.entity.Journal;
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
public class DbJournalTest extends DBUnitConfig {


    StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
    JournalDao journalDao = daoFactory.getJournalDao();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("journal/journal-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public DbJournalTest(String name) {
        super(name);
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<Journal> actualList = journalDao.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("journal/journal-data-find-all.xml"));
        ITable expTable = expected.getTable("journal");
        ITable actTable = tester.getConnection().createTable("journal");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        Journal newJournal = new Journal(8, 3, 4, 3);
        journalDao.create(newJournal);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("journal/journal-data-create.xml"));
        ITable expTable = expected.getTable("journal");

        ITable actTable = tester.getConnection().createTable("journal");
        String[] ignore = new String[]{"journal_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        Journal update = new Journal(4, 2, 2, 2);
        update.setGradeId(4);
        journalDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("journal/journal-data-update.xml"));
        ITable expTable = expected.getTable("journal");

        ITable actTable = tester.getConnection().createTable("journal");
        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(update.getGradeId(), actTable.getValue(3, "grade_id"));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        Journal delete = new Journal(5, 1, 4, 4);
        journalDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("journal/journal-data-delete.xml"));
        ITable expTable = expected.getTable("journal");

        ITable actTable = tester.getConnection().createTable("journal");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        int journalId = 7;
        Journal foundJournal = journalDao.findById(journalId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("journal/journal-data-find-by-id.xml"));
        ITable expTable = expected.getTable("journal");

        String sqlQuery = String.format("SELECT * FROM journal WHERE journal_id = %d", journalId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, getConnection(), "journal", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "student_id"), String.valueOf(foundJournal.getStudentId()));
        Assert.assertEquals(expTable.getValue(0, "subject_id"), String.valueOf(foundJournal.getSubjectId()));
        Assert.assertEquals(expTable.getValue(0, "grade_id"), String.valueOf(foundJournal.getGradeId()));
    }
}
