import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Term;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/21/2015.
 */
public class TermTest {
    @Test
    public void testGetAll() throws Exception {
        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        List<Term> list = new ArrayList<>();

        try  {
            TermDao dao = daoFactory.getTermDao();

            Term term = new Term(1, "first");
            dao.create(term);
            Term term2 = new Term(2, "third");
            dao.create(term2);
            dao.update(term2 ,new Term(2, "second"));
            dao.delete(term);
            list = dao.findAll();
            dao.findById(2);
        } catch (Exception e) {

        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
}
