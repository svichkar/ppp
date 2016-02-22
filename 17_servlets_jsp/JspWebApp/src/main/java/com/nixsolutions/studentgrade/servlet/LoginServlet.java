package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.servlet.message.Message;
import com.nixsolutions.studentgrade.servlet.message.MessageType;

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

        Message m = new Message();
        m.setMessageType(MessageType.ERROR);

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        if (userDao.validateUser(login)) {

            User user = userDao.getUserByLoginAndPassword(login, pass);

            if (user != null) {

                RoleDao roleDao = daoFactory.getRoleDao();
                Role role = roleDao.findById(user.getRoleId());
                HttpSession session = request.getSession();
                session.setAttribute("user", user.getLogin());

                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30 * 60);

                if ("admin".equals(role.getRoleName())) {
                    session.setAttribute("isAdmin", true);
                    response.sendRedirect("admin");
                } else {
                    session.setAttribute("isAdmin", false);
                    response.sendRedirect("home");
                }
            } else {
                m.setMessageText("Password is not valid. Please try again");
                request.setAttribute("message", m);
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").include(request, response);
            }
        } else {
            if (login.isEmpty() == false) {
                m.setMessageText("User doesn't exist. Please contact admin to add new user");
                request.setAttribute("message", m);
            }
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
