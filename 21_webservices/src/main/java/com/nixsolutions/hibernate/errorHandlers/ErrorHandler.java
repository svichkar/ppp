package com.nixsolutions.hibernate.errorHandlers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kozlovskij on 1/12/2016.
 */
@WebServlet("/errorPage")
public class ErrorHandler  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer statusCode = (Integer)req.getAttribute("javax.servlet.error.status_code");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<p><img src=\"webContent/images/errorPage.png\" alt=\"sorry, we have error here\"></p>");
        out.println("Status code " + statusCode);
    }
}
