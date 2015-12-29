package com.nixsolutions.studentgrade;

import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by svichkar on 12/24/2015.
 */

@WebServlet(name = "Login Page",
        description = "This is my first annotated servlet",
        urlPatterns = "/login",
        loadOnStartup = 1)
public class MainPageServlet extends HttpServlet {

    private boolean isAdmin;
    private String pageHtml;


    public MainPageServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.getParameter("password");
        request.getParameter("login");
/*
        if (isUserPresent(request.getParameter("login"))== false) {

            pageHtml = "User with current login/email doesn't exist";
        } else {
            if (true){}
        }
*/

        if (true) {
            pageHtml = "admin";
        } else {
            pageHtml = "guest";
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("HELLO, " + pageHtml);
    }

    protected boolean isUserPresent(String input) {

        boolean result = false;
        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        User user = userDao.findByLoginOrEmail(input);
        if (user != null) {
            result = true;
        }
        return result;
    }

    protected User getUserByLoginAndPassword (String login, String password) {

        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.findByLoginAndPassword(login, password);
        return user;
    }

    protected boolean isUserAdmin(User user) {

        boolean result = false;
        if (user.getUserId() == 1) {
            result = true;
        }
        return result;
    }

}
