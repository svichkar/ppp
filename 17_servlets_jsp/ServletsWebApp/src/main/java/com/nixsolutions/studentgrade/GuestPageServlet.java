package com.nixsolutions.studentgrade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by svichkar on 12/29/2015.
 */

@WebServlet(name = "Login Page",
        description = "Guest first page",
        urlPatterns = "/guest",
        loadOnStartup = 0)
public class GuestPageServlet extends HttpServlet {

    private String pageHtml;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        pageHtml = "<i><p>GET: Guest page</p></i>";
        PrintWriter out = response.getWriter();
        out.println(pageHtml);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        pageHtml = "<i><p>POST: Guest page</p></i>";
        PrintWriter out = response.getWriter();
        out.println(pageHtml);
    }
}
