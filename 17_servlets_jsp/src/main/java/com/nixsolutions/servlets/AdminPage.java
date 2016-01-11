package com.nixsolutions.servlets;

import com.nixsolutions.servicestation.dao.UserDAO;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
@WebServlet("/admin")
public class AdminPage extends HttpServlet {
    public static Logger LOGGER = LogManager.getLogger(AdminPage.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        UserDAO userDAO = new ImplUserDAO();
        User user = new User();
        try (PrintWriter out = resp.getWriter()) {
            if (req.getParameter("deleteBtn") != null) {
                if (req.getParameter("currentUser").equals("newUser")) {
                    out.println("You can't delete not registered user");
                } else {
                    user = userDAO.findByLogin(req.getParameter("currentUser"));
                    Role role = new ImplRoleDAO().findById(user.getRoleId());
                    if (req.getSession().getAttribute("loginAdmin").equals(user.getLogin())) {
                        out.println("You can't delete yourself");
                    } else {
                        userDAO.delete(user);
                    }
                }
            }
            if (req.getParameter("saveBtn") != null) {
                    user.setUserId(Integer.valueOf(req.getParameter("uId")));
                    user.setLogin(req.getParameter("uLogin"));
                    user.setPassword(req.getParameter("uPass"));
                    user.setRoleId(Integer.valueOf(req.getParameter("uRole")));
                    if (!req.getParameter("currentUser").equals("newUser")) {
                        userDAO.update(user);
                    } else {
                        userDAO.create(user);
                    }
            }
            if (req.getParameter("currentUser") != null && req.getParameter("editBtn") != null) {
                out.println("<form action=\"admin\" method=\"post\"/>");
                if (req.getParameter("currentUser").equals("newUser")) {
                    out.println("ID:<input type=\"text\" name=\"uId\" ><br>");
                    out.println("Login:<input type=\"text\" name=\"uLogin\"><br>");
                    out.println("Password<input type=\"text\" name=\"uPass\"><br>");
                    out.println("RoleId(1-3)<input type=\"text\" name=\"uRole\"><br>");
                } else {
                    user = userDAO.findByLogin(req.getParameter("currentUser"));
                    out.println("ID:<input type=\"text\" value=\"" + user.getUserId() + "\" name=\"uId\" readonly=\"true\"><br>");
                    out.println("Login:<input type=\"text\" value=\"" + user.getLogin() + "\" name=\"uLogin\"><br>");
                    out.println("Password:<input type=\"text\" value=\"" + user.getPassword() + "\" name=\"uPass\"><br>");
                    out.println("RoleId(1-3):<input type=\"text\" value=\"" + user.getRoleId() + "\" name=\"uRole\"><br>");
                }
                out.println("<input type=\"hidden\" name=\"currentUser\" value=\"" + req.getParameter("currentUser") + "\">");
                out.println("<input type=\"submit\" value=\"Save\" name=\"saveBtn\">");
                out.println("</form>");
            }

            List<User> userList = userDAO.findAll();
            out.println("<title>Admin Page</title>");
            out.println("<form action=\"admin\" method=\"post\"/>");
            out.println("<select name=\"currentUser\">");
            out.println("<option value=\"newUser\">Add new user</option>");
            for (User u : userList) {
                out.println("<option value=\"" + u.getLogin() + "\">" + u.getLogin() + "</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"Add or Edit\" name=\"editBtn\">");
            out.println("<input type=\"submit\" value=\"Delete\" name=\"deleteBtn\">");
            out.println("</form>");
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            if (req.getSession().getAttribute("isAdmin") == null) {
                out.println("<title>Login Page</title>");
                out.println("You're must be an admin to do something on this page");
                out.println("<p><a href=\"index\">Please follow the link to login</a></p>");
            } else {
                doPost(req, resp);
            }
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }
}