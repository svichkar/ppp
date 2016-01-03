import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
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

    StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
    GradeDao gradeDao = daoFactory.getGradeDao();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
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

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    public void testCreateShouldAddNewEntity() throws Exception {

        Grade newGrade = new Grade(6, "wunderkinder");
        gradeDao.create(newGrade);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data-create.xml"));
        ITable expTable = expected.getTable("grade");

        ITable actTable = tester.getConnection().createTable("grade");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        Grade update = new Grade(4, "good");
        update.setGradeName("not bad, man!");
        gradeDao.update(update);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data-update.xml"));
        ITable expTable = expected.getTable("grade");

        ITable actTable = tester.getConnection().createTable("grade");
        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(update.getGradeName(), actTable.getValue(3, "grade_name"));
    }

    @Test
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        Grade delete = new Grade(4, "good");
        gradeDao.delete(delete);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data-delete.xml"));
        ITable expTable = expected.getTable("grade");

        ITable actTable = tester.getConnection().createTable("grade");
        Assertion.assertEquals(expTable, actTable);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        int gradeId = 2;
        Grade foundGrade = gradeDao.findById(gradeId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("grade/grade-data-find-by-id.xml"));
        ITable expTable = expected.getTable("grade");

        String sqlQuery = String.format("SELECT * FROM grade WHERE grade_id = %d", gradeId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, getConnection(), "grade", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "grade_name"), foundGrade.getGradeName());
    }

}
