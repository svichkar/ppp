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

@WebServlet(name = "AdminPage",
        description = "Admin page for creating/updating users",
        urlPatterns = "/admin",
        loadOnStartup = 0)
public class AdminPageServlet  extends HttpServlet {

    private String pageHtml;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        pageHtml = "<b><p>GET: Admin page</p></b>";
        PrintWriter out = response.getWriter();
        out.println(pageHtml);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        pageHtml = "<b><p>POST: Admin page</p></b>";
        PrintWriter out = response.getWriter();
        out.println(pageHtml);
    }
}
