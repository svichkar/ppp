import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.entity.Student;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */

public class StudentTest {

    @Test
    public void testGetAll() throws Exception {
        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        List<Student> list = new ArrayList<>();

        try  {
            StudentDao dao = daoFactory.getStudentDao();

            Student student = new Student(1, "S", "K", 5, new Date(2015 - 1900,9,25), 1, 2);
            dao.create(student);
            Student student2 = new Student(130, "student2", "student2", 2, new Date(2015 - 1900,9,25), 1, 2);
            dao.create(student2);

            dao.update(student2 );

            dao.delete(student2);
            list = dao.findAll();
            dao.findById(2);
        } catch (Exception e) {

        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
}
