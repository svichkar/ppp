
package com.nixsolutions.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 * Created by rybkinrolla on 19.01.2016.
 */

@WebServlet("/errorpage")
public class ServletError extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        out.println("We're sorry, status code =" + statusCode);
        if (exception instanceof RuntimeException) {
            out.println("We obtained critical error, please contact us");
        }
        if (exception instanceof SQLException) {
            out.println("We obtained database error, please contact us");
        }
        if (exception instanceof ServletException) {
            out.println("We obtained controller error, please contact us");
        }
        if (exception instanceof IOException) {
            out.println("We obtained input-output error, please contact us");
        }
        out.println("servicestation@gmail.com");

    }
}

