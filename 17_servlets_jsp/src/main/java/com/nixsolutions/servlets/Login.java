package com.nixsolutions.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by rybkinrolla on 07.01.2016.
 */
@WebServlet("/index")
public class Login extends HttpServlet{
    public static Logger LOGGER = LogManager.getLogger(Login.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<title>Login Page</title>");
            out.println("<form action=\"profile\" method=\"post\"/>");
            out.println("Login:<input type=\"text\" name=\"login\"><br>");
            out.println("Password:<input type=\"password\" name=\"pass\"><br>");
            out.println("<input type=\"submit\" value=\"Sign in\"><br>");
            out.println("</form>");
            if(req.getAttribute("userLoggedOut").equals(true)){
                out.println("You're successfully logged out");
                req.getSession().invalidate();
            }
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }
}
