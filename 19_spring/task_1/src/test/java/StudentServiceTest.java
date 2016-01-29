import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.nixsolutions.studentgrade.service.StudentService;
import org.dbunit.IDatabaseTester;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.sql.DataSource;

/**
 * Created by svichkar on 1/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup(value = "/dbunit/student/student-data.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/student/student-data.xml")
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseTester databaseTester;

    public void setStudentService(StudentService studentService) {

        this.studentService = studentService;
    }


}
