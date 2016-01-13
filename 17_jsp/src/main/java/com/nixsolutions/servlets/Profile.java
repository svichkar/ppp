package com.nixsolutions.servlets;


import com.nixsolutions.servicestation.dao.impl.ImplRoleDAO;
import com.nixsolutions.servicestation.dao.impl.ImplUserDAO;
import com.nixsolutions.servicestation.entity.Role;
import com.nixsolutions.servicestation.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by rybkinrolla on 06.01.2016.
 */

@WebServlet("/profile")
public class Profile extends HttpServlet {
    public static Logger LOGGER = LogManager.getLogger(Profile.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession httpSession = req.getSession();
        try (PrintWriter out = resp.getWriter()) {
            if (httpSession.getAttribute("userLoggedIn") == null) {
                out.println("<title>Login Page</title>");
                out.println("<p><a href=\"index\">Please follow the link to login</a></p>");
            } else {
                User user = (User) httpSession.getAttribute("userLoggedIn");
                Role role = (Role) httpSession.getAttribute("userRole");
                out.println("<title>Profile page</title>");
                out.println("ID:<input type=\"text\" value=\"" + user.getUserId() + "\" name=\"uId\" readonly=\"true\"><br>");
                out.println("Login:<input type=\"text\" value=\"" + user.getLogin() + "\" name=\"uLogin\" readonly=\"true\"><br>");
                out.println("Password:<input type=\"text\" value=\"" + user.getPassword() + "\" name=\"uPass\" readonly=\"true\"><br>");
                out.println("RoleName<input type=\"text\" value=\"" + role.getRoleName() + "\" name=\"uRole\" readonly=\"true\"><br>");
                out.println("<form action=\"logout\" method=\"get\"/>" +
                        "<input type=\"submit\" value=\"Logout\"/>" +
                        "</form>");
                if(httpSession.getAttribute("isAdmin").equals(true)) {
                    out.println("<form action=\"admin\" method=\"post\"/>" +
                            "<input type=\"submit\" value=\"admin\"/>" +
                            "</form>");
                }
            }
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        User user = new ImplUserDAO().findByLogin(login);
        HttpSession httpSession = req.getSession();
        try (PrintWriter out = resp.getWriter()) {
            if (user != null && user.getPassword().equals(req.getParameter("pass"))) {
                Role role = new ImplRoleDAO().findById(user.getRoleId());
                httpSession.setAttribute("userLoggedIn", user);
                httpSession.setAttribute("userRole", role);
                if (role.getRoleName().equals("admin")) {
                    httpSession.setAttribute("isAdmin", true);
                    httpSession.setAttribute("loginAdmin", user.getLogin());
                }
                doGet(req, resp);
            } else {
                out.println("<p>Wrong login or password<p>");
                out.println("<a href=\"index\">Please follow the link to login</a>");
            }
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }
}