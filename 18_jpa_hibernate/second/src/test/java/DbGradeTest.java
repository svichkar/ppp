import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.entity.Grade;
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
 * Created by konstantin on 12/23/2015.
 */
public class DbGradeTest extends DBUnitConfig {

    private GradeDao gradeDao;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
        gradeDao = daoFactory.getGradeDao();
    }

    public DbGradeTest(String name) {
        super(name);
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<Grade> actualList = gradeDao.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data-find-all.xml"));
        ITable expTable = expected.getTable("grade");
        ITable actTable = tester.getConnection().createTable("grade");

        String[] ignore = new String[]{"grade_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        Grade newGrade = new Grade();
        newGrade.setGradeName("wunderkinder");
        gradeDao.create(newGrade);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data-create.xml"));
        ITable expTable = expected.getTable("grade");

        ITable actTable = tester.getConnection().createTable("grade");
        String[] ignore = new String[]{"grade_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        Grade update = gradeDao.findAll().get(1);
        update.setGradeName("not bad, man!");
        gradeDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data-update.xml"));
        ITable expTable = expected.getTable("grade");

        ITable actTable = tester.getConnection().createTable("grade");
        String[] ignore = new String[]{"grade_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
        Assert.assertEquals(update.getGradeName(), actTable.getValue(1, "grade_name"));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        Grade delete = gradeDao.findAll().get(0);
        gradeDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data-delete.xml"));
        ITable expTable = expected.getTable("grade");

        ITable actTable = tester.getConnection().createTable("grade");
        String[] ignore = {"grade_id"};
        Assertion.assertEqualsIgnoreCols(expTable, actTable, ignore);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        Long gradeId = gradeDao.findAll().get(2).getGradeId();
        Grade foundGrade = gradeDao.findById(gradeId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data-find-by-id.xml"));
        ITable expTable = expected.getTable("grade");

        String sqlQuery = String.format("SELECT * FROM grade WHERE grade_id = %d", gradeId);
        String[] ignore = {"grade_id"};
        Assertion.assertEqualsByQuery(expTable, getConnection(), "grade", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "grade_name"), foundGrade.getGradeName());
    }

}
