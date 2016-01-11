import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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

        List<Role> roles = daoFactory.getRoleDao().findAll();
        List<User> users = daoFactory.getUserDao().findAll();

        StringBuilder updateUser = new StringBuilder();
        for (User u : users) {

            updateUser.append("<form action=\"admin\" method=\"post\">\n" +
                    "        <tr class=\"rows\">\n" +
                    "<td>\n" +
                    "<input type=\"submit\" name=\"operation\" value=\"Update\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                    "<input type=\"submit\" name=\"operation\" value=\"Delete\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                    "</td>\n");

            updateUser.append("<td><input type=\"text\" name=\"id\" value=\"" + u.getUserId() + "\" readonly/></td>\n");
            updateUser.append("<td><input type=\"text\" name=\"fisrt_name\" value=\"" + u.getFirstName() + "\" maxlength=\"30\" pattern=\"[A-Za-z]{3,30}\" required/></td>\n");
            updateUser.append("<td><input type=\"text\" name=\"last_name\" value=\"" + u.getLastName() + "\" maxlength=\"30\" pattern=\"[A-Za-z]{3,30}\" required/></td>\n");
            updateUser.append("<td><input type=\"text\" name=\"login\" value=\"" + u.getLogin() + "\" maxlength=\"20\" pattern=\"[^А-Яа-яёЁ]{3,20}\" required/></td>\n");
            updateUser.append("<td><input type=\"text\" name=\"pass\" value=\"" + u.getUserPassword() + "\" maxlength=\"20\" pattern=\"[^А-Яа-яёЁ]{3,20}\" required/></td>\n");
            updateUser.append("<td><input type=\"text\" name=\"email\" value=\"" + u.getEmail() + "\" maxlength=\"50\" pattern=\"\\S+@[a-z]+.[a-z]+\" required/></td>\n");

            StringBuilder updateRole = new StringBuilder("<td>\n" +
                    "<select name=\"role\" required style=\"width:100%;border: none;\">\n");
            for (Role r : roles) {
                if (u.getRoleId()== r.getRoleId()) {
                    updateRole.append("<option selected>" + r.getRoleName() + "</option>\n");
                } else {
                    updateRole.append("<option>" + r.getRoleName() + "</option>\n");
                }
            }
            updateRole.append("</select>\n" +
                    "</td>");
            updateUser.append(updateRole);
            updateUser.append("</tr>\n" +
                    "</form>\n");
        }

        StringBuilder newUser = new StringBuilder("<form action=\"admin\" method=\"post\">\n" +
                "<tr class=\"rows\">\n" +
                "<td>\n" +
                "<input type=\"submit\" name=\"operation\" value=\"Add\" style=\"width:100%;height:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                "</td>\n" +
                "<td><input type=\"text\" name=\"id\" hidden placeholder=\"id\"></input></td>\n" +
                "<td><input type=\"text\" name=\"fisrt_name\" pattern=\"[A-Za-z]{3,30}\" required placeholder=\"first name\"></input></td>\n" +
                "<td><input type=\"text\" name=\"last_name\" pattern=\"[A-Za-z]{3,30}\" required placeholder=\"second name\"></input></td>\n" +
                "<td><input type=\"text\" name=\"login\" pattern=\"[^А-Яа-яёЁ]{3,20}\" required placeholder=\"login\"></input></td>\n" +
                "<td><input type=\"text\" name=\"pass\" pattern=\"[^А-Яа-яёЁ]{3,20}\" required placeholder=\"password\"></input></td>\n" +
                "<td><input type=\"text\" name=\"email\" pattern=\"\\S+@[a-z]+.[a-z]+\" required placeholder=\"e-mail\"></input></td>");

        StringBuilder newRole = new StringBuilder("<td>\n" +
                "<select name=\"role\" required style=\"width:100%;border: none;\">\n" +
                "<option selected disabled></option>\n");
        for (Role r : roles) {
            newRole.append("<option>" + r.getRoleName() + "</option>\n");
        }
        newUser.append(newRole);
        newUser.append("</tr>\n" +
                "</form>\n");


        StringBuilder pageHtml = new StringBuilder();
        pageHtml.append("<!DOCTYPE html>\n" +
                "<html >\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>Admin page</title>   \n" +
                "<link href=\"favicon.png\" rel=\"shortcut icon\" type=\"shortcut/ico\">\n" +
                "<link rel=\"stylesheet\" href=\"css/style.css\">  \n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>User Administration</h1>\n" +
                "<div class=\"divTable\">\n" +
                "<table>\n" +
                "<tr>\n" +
                "<td width=\"1%\">COMMANDS</td>\n" +
                "<td width=\"5%\">USER ID</td>\n" +
                "<td width=\"18%\">FIRST NAME</td>\n" +
                "<td width=\"18%\">LAST NAME</td>\n" +
                "<td width=\"15%\">LOGIN</td>\n" +
                "<td width=\"15%\">PASSWORD</td>\n" +
                "<td width=\"20%\">E-MAIL</td>\n" +
                "<td width=\"7%\">ROLE</td>\n" +
                "</tr>");
        pageHtml.append(updateUser);
        pageHtml.append(newUser);
        pageHtml.append("</table>\n" +
                "</div>\n" +
                "</br>\n" +
                "</body>\n" +
                "</html>");

        pageHtml.toString();


        Assert.assertEquals(role1.getRoleId(), role2.getRoleId());
        Assert.assertEquals(role1.getRoleName(), role2.getRoleName());

    }
}
