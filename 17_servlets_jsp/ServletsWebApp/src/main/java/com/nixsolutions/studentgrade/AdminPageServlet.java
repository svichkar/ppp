package com.nixsolutions.studentgrade;

import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class AdminPageServlet extends HttpServlet {

    private String pageHtml;
    private HttpSession session;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(true);
        if (session.getAttribute("isAdmin").equals(true)) {
            StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();

            RoleDao roleDao = daoFactory.getRoleDao();
            List<Role> roles = roleDao.findAll();
            List<User> users = daoFactory.getUserDao().findAll();

            StringBuilder updateUser = new StringBuilder();
            for (User u : users) {

                updateUser.append("<form action=\"admin\" method=\"post\">\n" +
                        "        <tr class=\"rows\">\n" +
                        "<td>\n" +
                        "<input type=\"submit\" name=\"operation\" value=\"update\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
                        "<input type=\"submit\" name=\"operation\" value=\"delete\" style=\"width:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
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
                    if (u.getRoleId() == r.getRoleId()) {
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
                    "<input type=\"submit\" name=\"operation\" value=\"add\" style=\"width:100%;height:100%;color:#fffff;background-color: #e5ffff;\"/>\n" +
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
                    "<link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p class=\"link-login\" align=\"right\"><a href=\"index.html\">Logout</a></p>" +
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
                    "</body>\n" +
                    "</html>");

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(pageHtml.toString());
            out.close();
        } else {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<p><h5>You can't access to admin page! Please login again!</h5></p>");

            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(true);
        if (session.getAttribute("isAdmin").equals(true)) {
            StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();

            RoleDao roleDao = daoFactory.getRoleDao();
            Role role = roleDao.findByName(request.getParameter("role"));

            UserDao userDao = daoFactory.getUserDao();
            User user = new User();

            if (request.getParameter("id") != null && request.getParameter("id") != "") {
                user.setUserId(Integer.valueOf(request.getParameter("id")));
            }
            user.setFirstName(request.getParameter("fisrt_name"));
            user.setLastName(request.getParameter("last_name"));
            user.setLogin(request.getParameter("login"));
            user.setUserPassword(request.getParameter("pass"));
            user.setEmail(request.getParameter("email"));
            user.setRoleId(role.getRoleId());

            String message = "";

            switch (request.getParameter("operation")) {

                case "add": {

                    boolean isUnique = true;
                    for (User u : userDao.findAll()) {

                        if (user.getLogin().equals(u.getLogin())) {
                         isUnique = false;
                        }
                    }

                    if(isUnique) {
                        userDao.create(user);
                        message = "Success";
                    } else {

                        message = String.format("User with '<b>%s</b>' login already exists.", user.getLogin());
                    }
                }
                break;

                case "update": {
                    if (request.getSession(true).getAttribute("pageOwner").equals(user.getLogin()) == false) {
                        userDao.update(user);
                        message = "Success";

                    } else {

                        message = "User can't be updated by himself.";
                    }
                }
                break;

                case "delete": {
                    if (request.getSession(true).getAttribute("pageOwner").equals(user.getLogin()) == false) {
                        userDao.delete(user);
                        message = "Success";

                    } else {
                        message = "User can't be deleted by himself.";
                    }
                }
                break;
            }

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<p><h5>" + message + "</h5></p>");

            doGet(request, response);

        }  else  {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<p><h5>You can't access to admin page! Please login again!</h5></p>");

            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
            out.close();
        }
    }
}
