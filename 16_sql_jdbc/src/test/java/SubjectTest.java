import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.entity.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */

public class SubjectTest {

    @Test
    public void testGetAll() throws Exception {
        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        List<Subject> list = new ArrayList<>();

        try  {
            SubjectDao dao = daoFactory.getSubjectDao();

            Subject subject = new Subject(57, "again", 2);
            dao.create(subject);
            Subject subject2 = new Subject(58, "again2", 1);
            dao.create(subject2);
            dao.update(subject ,new Subject(59, "upd", 1));
            dao.delete(subject2);
            list = dao.findAll();
            dao.findById(2);
        } catch (Exception e) {

        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
}
