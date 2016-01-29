import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.Grade;
import com.nixsolutions.studentgrade.service.GradeService;
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
@DatabaseSetup(value = "/dbunit/grade/grade-data.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/grade/grade-data.xml")
public class GradeServiceTest {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @Test
    public void testFindAllShouldReturnAllRows() throws Exception {

        List<Grade> actualList = gradeService.findAll();

        IDataSet expected = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/grade/grade-data-find-all.xml"));
        ITable expTable = expected.getTable("grade");
        ITable actTable = databaseTester.getConnection().createTable("grade");

        Assertion.assertEquals(expTable, actTable);
        Assert.assertEquals(expTable.getRowCount(), actualList.size());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/grade/grade-data-create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "grade")
    public void testCreateShouldAddNewEntity() throws Exception {

        Grade newGrade = new Grade();
        newGrade.setGradeName("wunderkinder");
        gradeService.create(newGrade);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/grade/grade-data-update.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "grade")
    public void testUpdateShouldModifySpecifiedEntity() throws Exception {

        Grade update = new Grade();
        update.setGradeId(4L);
        update.setGradeName("not bad, man!");
        gradeService.update(update);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/grade/grade-data-delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "grade")
    public void testDeleteShouldRemoveSpecifiedEntity() throws Exception {

        Grade delete = new Grade();
        delete.setGradeId(4L);
        delete.setGradeName("good");
        gradeService.delete(delete);
    }

    @Test
    public void testFindByIdShouldReturnRequestedEntity() throws Exception {

        Long gradeId = 2L;
        Grade foundGrade = gradeService.findById(gradeId);

        IDataSet expected = new FlatXmlDataSetBuilder().build(getClass()
                .getResourceAsStream("dbunit/grade/grade-data-find-by-id.xml"));
        ITable expTable = expected.getTable("grade");

        String sqlQuery = String.format("SELECT * FROM grade WHERE grade_id = %d", gradeId);
        String[] ignore = new String[0];
        Assertion.assertEqualsByQuery(expTable, databaseTester.getConnection(), "grade", sqlQuery, ignore);
        Assert.assertEquals(expTable.getValue(0, "grade_name"), foundGrade.getGradeName());
    }

}
