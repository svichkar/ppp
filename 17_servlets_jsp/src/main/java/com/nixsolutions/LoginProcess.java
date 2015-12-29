package com.nixsolutions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kozlovskij on 12/29/2015.
 */

@WebServlet("/login")
public class LoginProcess extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>\n" +
                "<head>\n" +
                "<title>login page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>You are not login. Please go to login form</p>\n" +
                "<p><a href=\"index.html\">login form</a></p>\n" +
                "</body>\n" +
                "</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>\n" +
                "<head>\n" +
                "<title>login page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p1>You enter login " + req.getParameter("login") + "</p1>\n" +
                "<p1>You enter password " + req.getParameter("password") + "</p1>\n" +
                "</body>\n" +
                "</html>");
        out.close();
    }
}
