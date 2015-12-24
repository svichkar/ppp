package com.nixsolutions.studentgrade;

import com.nixsolutions.studentgrade.dao.StudentGradeDaoFactory;
import com.nixsolutions.studentgrade.dao.UserDao;

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

@WebServlet(name = "Admin Page",
        description = "This is my first annotated servlet",
        urlPatterns = "/admin",
        loadOnStartup = 1)
public class MainPageServlet extends HttpServlet {

    private boolean isAdmin;
    private String pageHtml;

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();
        UserDao userDao =  daoFactory.getUserDao();

        request.getParameter("login");
        request.getParameter("password");

        userDao.findById(1);

        if (isAdmin) {
            pageHtml = "admin";
        } else {
            pageHtml = "guest";
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("HELLO, " + pageHtml );
    }

}
