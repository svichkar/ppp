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

    private boolean isAdmin;
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

                if (role.getRoleName().equals("admin")) {

                    session.setAttribute("isAdmin", true);
                    pageHtml = "You are logged with Admin rights.";
                    out.println(pageHtml);
                    response.sendRedirect("admin");

                } else {

                    session.setAttribute("isAdmin", false);
                    pageHtml = "You are logged with Guest rights.";
                    out.println(pageHtml);
                    response.sendRedirect("guest");

                }

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
        rd.forward(request, response);
    }
}
