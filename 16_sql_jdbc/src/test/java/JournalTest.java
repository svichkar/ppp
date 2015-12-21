import com.nixsolutions.studentgrade.dao.JournalDao;
import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.entity.Journal;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/21/2015.
 */
public class JournalTest {

    @Test
    public void testGetAll() throws Exception {
        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        List<Journal> list = new ArrayList<>();

        try  {
            JournalDao dao = daoFactory.getJournalDao();

            Journal journal = new Journal(56, 1, 5, 4);
            dao.create(journal);
            Journal journal2 = new Journal(77, 5, 1, 5);
            dao.create(journal2);
            dao.update(journal2 ,new Journal(77, 6, 1, 3));
            dao.update(journal2 ,new Journal(99, 5, 2, 2));
            dao.delete(journal);
            Journal journal3 = new Journal(100, 10, 6, 1);
            dao.create(journal3);
            list = dao.findAll();
            dao.findById(2);
        } catch (Exception e) {

        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
}
