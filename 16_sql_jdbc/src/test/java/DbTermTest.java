import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Term;
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
public class DbTermTest extends DBUnitConfig {


    DaoFactory daoFactory = new DaoFactory();
    TermDao termDao = daoFactory.getTermDao();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("term/term-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public DbTermTest(String name) {
        super(name);
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<Term> actualList = termDao.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("term/term-data-find-all.xml"));
        ITable expTable = expected.getTable("term");
        ITable actTable = tester.getConnection().createTable("term");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        Term newTerm = new Term("external");
        termDao.create(newTerm);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("term/term-data-create.xml"));
        ITable expTable = expected.getTable("term");

        ITable actTable = tester.getConnection().createTable("term");
        String[] ignore = new String[]{"term_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        Term update = termDao.findById(new Long(1));
        update.setTermName("spring");
        termDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("term/term-data-update.xml"));
        ITable expTable = expected.getTable("term");

        ITable actTable = tester.getConnection().createTable("term");
        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(update.getTermName(), actTable.getValue(0, "term_name"));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        Term delete = termDao.findById(new Long(2));
        termDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("term/term-data-delete.xml"));
        ITable expTable = expected.getTable("term");

        ITable actTable = tester.getConnection().createTable("term");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        Long termId = new Long(1);
        Term foundTerm = termDao.findById(termId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("term/term-data-find-by-id.xml"));
        ITable expTable = expected.getTable("term");

        String sqlQuery = String.format("SELECT * FROM term WHERE term_id = %d", termId);
        String[] ignore = new String[]{"term_id"};
        Assertion.assertEqualsByQuery(expTable, getConnection(), "term", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "term_name"), foundTerm.getTermName());
    }
}
