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

/**
 * Created by svichkar on 12/24/2015.
 */

@WebServlet(name = "LoginPage",
        description = "This is my first annotated servlet",
        value = "/login")
public class MainPageServlet extends HttpServlet {

    private HttpSession session;
    private String pageHtml;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        if (userDao.validateUser(login)) {

            User user = userDao.getUserByLoginAndPassword(login, pass);

            if (user != null) {

                RoleDao roleDao = daoFactory.getRoleDao();
                Role role = roleDao.findById(user.getRoleId());
                session = request.getSession(true);
                session.setAttribute("pageOwner",  user.getLogin());

                String userInfo =  String.format("%s %s, </br>you are logged in with <b>%s</b> rights.",
                        user.getFirstName(),
                        user.getLastName(),
                        role.getRoleName());
                String adminLink = "";

                if (role.getRoleName().equals("admin")) {

                    session.setAttribute("isAdmin", true);
                    adminLink = "<a href=\"admin\" style=\"font-family: 'Courier New', Courier, monospace;" +
                            "font-weight: bold;font-size: 12px;text-align: left;\">Navigate to User Administration Page</a>";



                } else {

                    session.setAttribute("isAdmin", false);
                }

                response.setContentType("text/html");
                pageHtml = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<meta charset=\"UTF-8\">\n" +
                        "<title>Java Servlets App</title>\n" +
                        "<link href=\"favicon.png\" rel=\"shortcut icon\" type=\"shortcut/ico\">\n" +
                        "<link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div class=\"login-card\">\n" +
                        "<p class=\"link-login\" align=\"right\"><a href=\"index.html\">Logout</a></p>" +
                        "<h1>Welcome to Student Grade App!</h1></br></br>\n" +
                        "<h3>" + userInfo + "</h3></br>\n" +
                         adminLink +
                        "</div>\n" +
                        "</body>\n" +
                        "</html>";
                out = response.getWriter();
                out.println(pageHtml);

            } else {

                pageHtml = "<h5>Password is not valid. Please try again.</h5>";
                out.println(pageHtml);
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }

        } else {

            if (login.isEmpty() == false) {
                pageHtml = "<h5>User doesn't exist. Please contact admin to add new user.</h5>";
                out.println(pageHtml);
            }
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }

        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.include(request, response);
    }
}
