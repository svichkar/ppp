import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.entity.Grade;
import config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
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
                .getResourceAsStream("grade-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public DbGradeTest(String name) {
        super(name);
    }

    //http://devcolibri.com/3575
    @Test
    public void testFindAll () throws Exception {

        List<Grade> results = gradeDao.findAll();
        IDataSet expected = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
        .getResourceAsStream("grade-data.xml"));

        IDataSet actual = tester.getConnection().createDataSet();
        Assertion.assertEquals(expected, actual);
        Assert.assertEquals(expected.getTable("grade").getRowCount(), results.size());
    }

}
