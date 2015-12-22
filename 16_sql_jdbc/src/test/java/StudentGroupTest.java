import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.StudentGroupDao;
import com.nixsolutions.studentgrade.entity.StudentGroup;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */

public class StudentGroupTest {

    @Test
    public void testGetAll() throws Exception {
        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        List<StudentGroup> list = new ArrayList<>();

        try  {
            StudentGroupDao dao = daoFactory.getStudentGroupDao();

            StudentGroup group = new StudentGroup(25, "group 25");
            dao.create(group);
            StudentGroup group2 = new StudentGroup();
            dao.create(group2);
            dao.update(group ,new StudentGroup(25,"googogog"));
            dao.delete(group2);
            list = dao.findAll();
            dao.findById(2);
        } catch (Exception e) {

        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
}
