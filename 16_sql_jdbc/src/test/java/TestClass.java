import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.entity.Grade;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */

public class TestClass {

    @Test
    public void testGetAll() throws Exception {
        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        List<Grade> list = new ArrayList<>();

        try  {
            GradeDao dao = daoFactory.getGradeDao();
            list = dao.findAll();
        } catch (Exception e) {

        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
}
