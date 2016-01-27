import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.studentgrade.model.Term;
import com.nixsolutions.studentgrade.service.TermService;
import junit.framework.Assert;
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
 * Created by konstantin on 1/27/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-config.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup(value = "/term/term-data.xml")
@DatabaseTearDown(type = DatabaseOperation.CLEAN_INSERT, value = "/term/term-data.xml")
public class TermServiceTest {

    @Autowired
    private TermService termService;

    public void setTermService(TermService termService) {
        this.termService = termService;
    }

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Test
    @ExpectedDatabase(value = "/term/term-data-find-all.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "term")
    public void findAll() {

        List<Term>  termList = termService.findAll();
        Assert.assertEquals(termList.size(), 4);

    }
}
