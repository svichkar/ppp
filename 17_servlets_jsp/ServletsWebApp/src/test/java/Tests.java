import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by konstantin on 1/10/2016.
 */
public class Tests {

    @Test
    public void test() {

        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        User user = daoFactory.getUserDao().getUserByLoginAndPassword("kos","123");


        Assert.assertEquals(daoFactory.getUserDao().validateUser("kos"), true);
        Assert.assertEquals(daoFactory.getUserDao().validateUser("kos1"), false);

        Assert.assertEquals(user.getLogin(),"kos");
        Assert.assertEquals(user.getUserPassword(),"123");

        Role role1 = daoFactory.getRoleDao().findById(1);
        Role role2 = daoFactory.getRoleDao().findByName("admin");

        Assert.assertEquals(role1.getRoleId(), role2.getRoleId());
        Assert.assertEquals(role1.getRoleName(), role2.getRoleName());

    }
}
