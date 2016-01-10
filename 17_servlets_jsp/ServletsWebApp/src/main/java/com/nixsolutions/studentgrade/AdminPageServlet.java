package com.nixsolutions.studentgrade;

import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by svichkar on 12/29/2015.
 */

@WebServlet(name = "AdminPage",
        description = "Admin page for creating/updating users",
        urlPatterns = "/admin",
        loadOnStartup = 0)
public class AdminPageServlet  extends HttpServlet {

    private String pageHtml;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();

        RoleDao roleDao = daoFactory.getRoleDao();
        List<Role> roles = roleDao.findAll();

        StringBuilder role = new StringBuilder("<td>\n" +
                "<select name=\"role\" required style=\"width:100%;border: none;\">\n" +
                "<option selected disabled></option>\n");
        for (Role r : roles) {
            role.append("<option>" + r.getRoleName() + "</option>");
        }
        role.append("</select>\n" +
                "</td>");



        UserDao userDao = daoFactory.getUserDao();
        User user = new User();



        response.setContentType("text/html");
        //pageHtml = "<b><p>GET: Admin page</p></b>";
        pageHtml = "<!DOCTYPE html>\n" +
                "<html >\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Admin page</title>   \n" +
                "\t<link href=\"favicon.png\" rel=\"shortcut icon\" type=\"shortcut/ico\">\n" +
                "<link rel=\"stylesheet\" href=\"css/style.css\">  \n" +
                "  </head>\n" +
                "  <body>\n" +
                "  <h1>User Administration</h1>\n" +
                "   <div class=\"divTable\">\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "\t\t\t<td width=\"1%\">COMMANDS</td>\t\n" +
                "            <td width=\"5%\">USER ID</td>\n" +
                "            <td width=\"18%\">FIRST NAME</td>\n" +
                "\t\t\t<td width=\"18%\">LAST NAME</td>\n" +
                "\t\t\t<td width=\"15%\">LOGIN</td>\n" +
                "\t\t\t<td width=\"15%\">PASSWORD</td>\n" +
                "\t\t\t<td width=\"20%\">E-MAIL</td>\n" +
                "\t\t\t<td width=\"7%\">ROLE</td>\n" +
                "        </tr>\n" +
                "\t\t\n" +
                "\t\t<form action=\"admin\" method=\"post\" id=\"update\">\n" +
                "        <tr class=\"rows\">\n" +
                "\t\t\t<td>\n" +
                "\t\t\t\t<input type=\"submit\" name=\"operation\" value=\"Update\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                "\t\t\t\t<input type=\"submit\" name=\"operation\" value=\"Delete\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                "\t\t\t</td>\n" +
                "            <td><input type=\"text\" name=\"id\" value=\"1\" readonly/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"fisrt_name\" value=\"Svichkar\" maxlength=\"30\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"last_name\" value=\"Konstantin\" maxlength=\"30\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"login\" value=\"kos\" maxlength=\"20\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"pass\" value=\"123\" maxlength=\"20\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"email\" value=\"konstantin.svichkar@nixsolutions.com\" maxlength=\"50\" required/></td>\n" +
                "\t\t\t<td>\n" +
                "\t\t\t\t<select name=\"role\" required style=\"width:100%;border: none;\">\n" +
                "\t\t\t\t\t<option>admin</option>\n" +
                "\t\t\t\t\t<option>guest</option>\n" +
                "\t\t\t\t</select>\n" +
                "\t\t\t</td>\n" +
                "        </tr>\n" +
                "\t\t</form>\n" +
                "\n" +
                "\t\t<form action=\"admin\" method=\"post\" id=\"update\">\n" +
                "        <tr class=\"rows\">\n" +
                "\t\t\t<td>\n" +
                "\t\t\t\t<input type=\"submit\" name=\"operation\" value=\"Update\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                "\t\t\t\t<input type=\"submit\" name=\"operation\" value=\"Delete\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                "\t\t\t</td>\n" +
                "            <td><input type=\"text\" name=\"id\" value=\"2\" readonly/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"fisrt_name\" value=\"Ivanov\" maxlength=\"30\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"last_name\" value=\"Serg\" maxlength=\"30\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"login\" value=\"serg\" maxlength=\"20\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"pass\" value=\"321\" maxlength=\"20\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"email\" value=\"serg@nixsolutions.com\" maxlength=\"50\" required/></td>\n" +
                "\t\t\t<td>\n" +
                "\t\t\t\t<select name=\"role\" required style=\"width:100%;border: none;\">\n" +
                "\t\t\t\t\t<option>admin</option>\n" +
                "\t\t\t\t\t<option>guest</option>\n" +
                "\t\t\t\t</select>\n" +
                "\t\t\t</td>\n" +
                "        </tr>\n" +
                "\t\t</form>\t\n" +
                "\n" +
                "\t\t<form action=\"admin\" method=\"post\" id=\"update\">\n" +
                "        <tr class=\"rows\">\n" +
                "\t\t\t<td>\n" +
                "\t\t\t\t<input type=\"submit\" name=\"operation\" value=\"Update\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                "\t\t\t\t<input type=\"submit\" name=\"operation\" value=\"Delete\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                "\t\t\t</td>\n" +
                "            <td><input type=\"text\" name=\"id\" value=\"3\" readonly/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"fisrt_name\" value=\"Petrov\" maxlength=\"30\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"last_name\" value=\"Oleg\" maxlength=\"30\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"login\" value=\"oleg\" maxlength=\"20\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"pass\" value=\"789\" maxlength=\"20\" required/></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"email\" value=\"oleg@nixsolutions.com\" maxlength=\"50\" required/></td>\n" +
                "\t\t\t<td>\n" +
                "\t\t\t\t<select name=\"role\" required style=\"width:100%;border: none;\">\n" +
                "\t\t\t\t\t<option>admin</option>\n" +
                "\t\t\t\t\t<option>guest</option>\n" +
                "\t\t\t\t</select>\n" +
                "\t\t\t</td>\n" +
                "        </tr>\n" +
                "\t\t</form>\t\n" +
                "\t\t\n" +
                "\t\t<form action=\"admin\" method=\"post\" id=\"newUser\">\n" +
                "\t\t<tr class=\"rows\">\n" +
                "\t\t\t<td>\n" +
                "\t\t\t\t<input type=\"submit\" name=\"operation\" value=\"Add\" style=\"width:100%;height:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                "\t\t\t</td>\n" +
                "            <td><input type=\"text\" name=\"id\" hidden placeholder=\"id\"></input></td>\n" +
                "            <td><input type=\"text\" name=\"fisrt_name\" required placeholder=\"first name\"></input></td>\n" +
                "            <td><input type=\"text\" name=\"last_name\" required placeholder=\"second name\"></input></td>\n" +
                "            <td><input type=\"text\" name=\"login\" required placeholder=\"login\"></input></td>\n" +
                "            <td><input type=\"text\" name=\"pass\" required placeholder=\"password\"></input></td>\n" +
                "\t\t\t<td><input type=\"text\" name=\"email\" required placeholder=\"e-mail\"></input></td>\n" +
                "\t\t\t<td>\n" +
                "\t\t\t\t<select name=\"role\" required style=\"width:100%;border: none;\">\n" +
                "\t\t\t\t\t<option selected disabled></option>\n" +
                "\t\t\t\t\t<option>admin</option>\n" +
                "\t\t\t\t\t<option>guest</option>\n" +
                "\t\t\t\t</select>\n" +
                "\t\t\t</td>\n" +
                "        </tr>\n" +
                "\t\t</form>\n" +
                "\t\t\n" +
                "    </table>\n" +
                "</div>\n" +
                "</br>\n" +
                " </body>\n" +
                "</html>";
        PrintWriter out = response.getWriter();
        out.println(pageHtml);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();

        RoleDao roleDao = daoFactory.getRoleDao();
        Role role = roleDao.findByName(request.getParameter("role"));

        UserDao userDao = daoFactory.getUserDao();
        User user = new User();

        if(request.getParameter("id") != null) {
            user.setUserId(Integer.valueOf(request.getParameter("id")));
        }
        user.setFirstName(request.getParameter("fisrt_name"));
        user.setLastName(request.getParameter("last_name"));
        user.setLogin(request.getParameter("login"));
        user.setUserPassword(request.getParameter("pass"));
        user.setEmail(request.getParameter("email"));
        user.setRoleId(role.getRoleId());

        switch (request.getParameter("operation")) {

            case "Add": {
                userDao.create(user);
                pageHtml ="added";
            }
            break;

            case "Update": {
                userDao.update(user);
                pageHtml ="updated";
            }
            break;

            case "Delete": {
                userDao.delete(user);
                pageHtml = "deleted";
            }
            break;
        }



        response.setContentType("text/html");
        //pageHtml = "<b><p>POST: Admin page</p></b>";
        //pageHtml = request.getParameter("operation");
        PrintWriter out = response.getWriter();
        out.println(pageHtml);
    }
}
