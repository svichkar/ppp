package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.dao.DaoFactory;
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

/**
 * Created by svichkar on 12/24/2015.
 */

@WebServlet(name = "LoginPage",
        description = "login page",
        urlPatterns = {"/index.html", "/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        if (userDao.validateUser(login)) {

            User user = userDao.getUserByLoginAndPassword(login, pass);

            if (user != null) {

                Role role = user.getRole();
                HttpSession session = request.getSession();
                session.setAttribute("user", user.getLogin());

                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30 * 60);

                if (role.getRoleName().equals("admin")) {
                    session.setAttribute("isAdmin", true);
                    response.sendRedirect("admin");
                } else {
                    session.setAttribute("isAdmin", false);
                    response.sendRedirect("home");
                }

            } else {
                request.setAttribute("error", "<h5>Password is not valid. Please try again.</h5>");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
                rd.include(request, response);

            }

        } else {
            if (login.isEmpty() == false) {
                request.setAttribute("error", "<h5>User doesn't exist. Please contact admin to add new user.</h5>");
            }
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            rd.include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        rd.forward(request, response);
    }
}
