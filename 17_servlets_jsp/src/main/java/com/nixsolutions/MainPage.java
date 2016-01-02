package com.nixsolutions;

import com.nixsolutions.library.dao.DaoFactory;
import com.nixsolutions.library.dao.RoleDAO;
import com.nixsolutions.library.dao.UserDAO;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.User;
import com.nixsolutions.library.entity.Role;
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
 * Created by kozlovskij on 12/29/2015.
 */

@WebServlet("/main")
public class MainPage extends HttpServlet {
    public static Logger LOGGER = LogManager.getLogger(MainPage.class.getName());
    private HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>\n" +
                    "<head>\n" +
                    "<title>login page</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p>You are not login. Please go to login form</p>\n" +
                    "<p><a href=\"index.html\">login form</a></p>\n" +
                    "</body>\n" +
                    "</html>");
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            DaoFactory daoFactory = new DaoFactoryImpl();
            UserDAO dao = daoFactory.getUserDAO();
            User user = dao.findByLogin(req.getParameter("login").toLowerCase());

            if (user != null) {
                RoleDAO roleDAO = daoFactory.getRoleDAO();
                Role role = roleDAO.findByID(user.getRoleId());
                if (user.getPassword().equals(req.getParameter("password"))) {
                    session = req.getSession(true);
                    out.println("<html>\n" +
                            "<head>\n" +
                            "<title>login page</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<p>You enter login " + req.getParameter("login") + "</p>\r\n" +
                            "<p>Your user id " + user.getUserId() + "</p>\n" +
                            "<p>Your role " + role.getName() + "</p>\n");
                    if (role.getName().equals("admin")) {
                        session.setAttribute("isAdmin", true);
                        out.println("<form action=\"adminPage\" method=\"post\">" +
                                "<input type=\"hidden\" name=\"login\" value=\"" + req.getParameter("login") + "\">" +
                                "<input type=\"submit\" name=\"go to administration page\" value=\"go to administration page\"></form></td>");
                    } else {
                        session.setAttribute("isAdmin", false);
                    }
                    out.println("</body>\n" +
                            "</html>");
                } else {
                    out.println("<html>\n" +
                            "<head>\n" +
                            "<title>Wrong password</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<p>WrongPassword</p>\r\n" +
                            "<p><a href=\"index.html\">return to login form</a></p>" +
                            "</body>\n" +
                            "</html>");
                }
            } else {
                out.println("<html>\n" +
                        "<head>\n" +
                        "<title>User not found</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<p>User not found</p>\r\n" +
                        "<p><a href=\"index.html\">return to login form</a></p>" +
                        "</body>\n" +
                        "</html>");
            }
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }
}
