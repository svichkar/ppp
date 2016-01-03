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

            Journal journal = new Journal(123, 1, 5, 4);
            dao.create(journal);
            Journal journal2 = new Journal(103, 5, 1, 5);
            dao.create(journal2);
            dao.update(journal2);
            dao.update(journal2);
            dao.delete(journal);
            Journal journal3 = new Journal(100, 10, 6, 1);
            dao.create(journal3);
            list = dao.findAll();
            dao.findById(2);
        } catch (Exception e) {

        }
        Assert.assertNotNull(list);
    }
}
