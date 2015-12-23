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

public class GradeTest {

    @Test
    public void testGetAll() throws Exception {
        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        List<Grade> list = new ArrayList<>();

        try  {
            GradeDao dao = daoFactory.getGradeDao();

            Grade grade = new Grade(22, "dsfsd");
            dao.create(grade);
            dao.update(grade);
            dao.delete(new Grade(12, "another one"));
            list = dao.findAll();
            dao.findById(9);
        } catch (Exception e) {

        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
}
