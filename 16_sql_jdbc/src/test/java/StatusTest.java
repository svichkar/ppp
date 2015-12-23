import com.nixsolutions.studentgrade.dao.StatusDao;
import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.entity.Status;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */

public class StatusTest {

    @Test
    public void testGetAll() throws Exception {
        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        List<Status> list = new ArrayList<>();

        try  {
            StatusDao dao = daoFactory.getStatusDao();

            Status status = new Status(1, "active");
            dao.create(status);
            Status status2 = new Status(3, "expelled");
            dao.create(status2);
            dao.update(status);
            dao.delete(status2);
            list = dao.findAll();
            dao.findById(2);
        } catch (Exception e) {

        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
}
