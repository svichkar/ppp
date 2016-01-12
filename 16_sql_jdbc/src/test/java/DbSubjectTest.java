import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.entity.Subject;
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
public class DbSubjectTest extends DBUnitConfig {


    DaoFactory daoFactory = new DaoFactory();
    SubjectDao subjectDao = daoFactory.getSubjectDao();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("subject/subject-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public DbSubjectTest(String name) {
        super(name);
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<Subject> actualList = subjectDao.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("subject/subject-data-find-all.xml"));
        ITable expTable = expected.getTable("subject");
        ITable actTable = tester.getConnection().createTable("subject");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        Subject newSubject = new Subject("Chemistry", new Long(2));
        subjectDao.create(newSubject);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("subject/subject-data-create.xml"));
        ITable expTable = expected.getTable("subject");

        ITable actTable = tester.getConnection().createTable("subject");
        String[] ignore = new String[]{"subject_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        Subject update = subjectDao.findById(new Long(1));
        update.setTermId(new Long(2));
        subjectDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("subject/subject-data-update.xml"));
        ITable expTable = expected.getTable("subject");

        ITable actTable = tester.getConnection().createTable("subject");
        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(String.valueOf(update.getTermId()), String.valueOf(actTable.getValue(0, "term_id")));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        Subject delete = subjectDao.findById(new Long(3));
        subjectDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("subject/subject-data-delete.xml"));
        ITable expTable = expected.getTable("subject");

        ITable actTable = tester.getConnection().createTable("subject");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        Long subjectId =  new Long(4);
        Subject foundSubject = subjectDao.findById(subjectId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("subject/subject-data-find-by-id.xml"));
        ITable expTable = expected.getTable("subject");

        String sqlQuery = String.format("SELECT * FROM subject WHERE subject_id = %d", subjectId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, getConnection(), "subject", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "subject_name"), foundSubject.getSubjectName());
    }
}
