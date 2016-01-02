package com.nixsolutions;

import com.nixsolutions.library.dao.DaoFactory;
import com.nixsolutions.library.dao.RoleDAO;
import com.nixsolutions.library.dao.UserDAO;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.Role;
import com.nixsolutions.library.entity.User;
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
 * Created by Serko on 30.12.2015.
 */
@WebServlet("/adminPage")
public class AdminPage extends HttpServlet {
    public static Logger LOGGER = LogManager.getLogger(AdminPage.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            if (req.getSession(true).getAttribute("isAdmin").equals(true)) {
                out.println("You are admin");
            } else {
                out.println("You are not login as admin");
            }
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            DaoFactory daoFactory = new DaoFactoryImpl();
            UserDAO dao = daoFactory.getUserDAO();
            RoleDAO roleDAO = daoFactory.getRoleDAO();
            if (req.getSession().getAttribute("isAdmin").equals(true)) {
                if (req.getParameter("delete") != null) {
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    dao.delete(dao.findByID(id));
                }
                if (req.getParameter("edit") != null) {
                    Integer id = Integer.parseInt(req.getParameter("userId"));
                    if (roleDAO.findByName(req.getParameter("role").toLowerCase()) == null) {
                        Role role = new Role(req.getParameter("role").toLowerCase());
                        roleDAO.create(role);
                    }
                    Integer roleId = roleDAO.findByName(req.getParameter("role")).getRoleId();
                    if (req.getParameter("userName").equals(dao.findByID(id).getLogin())) {
                        User user = new User(id, req.getParameter("userName").toLowerCase(), req.getParameter("password"), roleId);
                        dao.update(user);
                    } else if (dao.findByLogin(req.getParameter("userName").toLowerCase()) == null) {
                        User user = new User(id, req.getParameter("userName").toLowerCase(), req.getParameter("password"), roleId);
                        dao.update(user);
                    } else {
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>admin page</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<p>name already exist<p>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
                if (req.getParameter("create") != null) {
                    if (roleDAO.findByName(req.getParameter("userRole").toLowerCase()) == null) {
                        Role role = new Role(req.getParameter("userRole").toLowerCase());
                        roleDAO.create(role);
                    }
                    if (dao.findByLogin(req.getParameter("userName").toLowerCase()) == null) {
                        User user = new User(req.getParameter("userName").toLowerCase(), req.getParameter("userPassword"),
                                roleDAO.findByName(req.getParameter("userRole")).getRoleId());
                        dao.create(user);
                    } else {
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>admin page</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<p>User already exist<p>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
                List<User> result = dao.findAll();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>admin page</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p>Welcome " + req.getParameter("login") + "</p>");
                out.println("<table border=\"1px\"><tr><td>User_ID</td><td>name</td><td>password</td><td>Role</td></tr>");
                for (int i = 0; i < result.size(); i++) {
                    User resultUser = result.get(i);
                    if (resultUser.getLogin().equals(req.getParameter("login"))) {
                        out.println("<tr><td>" + resultUser.getUserId() + "</td>" +
                                "<td>" + resultUser.getLogin() + "</td>" +
                                "<td>" + resultUser.getPassword() + "</td>" +
                                "<td>" + roleDAO.findByID(resultUser.getRoleId()).getName() + "</td>" +
                                "<td><form action=\"editPage\" method=\"post\">" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + resultUser.getUserId() + "\">" +
                                "<input type=\"hidden\" name=\"login\" value=\"" + req.getParameter("login") + "\">" +
                                "<input type=\"submit\" name=\"edit\" value=\"edit\"></form></td>" +
                                "</tr>");
                    } else {
                        out.println("<tr><td>" + resultUser.getUserId() + "</td>" +
                                "<td>" + resultUser.getLogin() + "</td>" +
                                "<td>" + resultUser.getPassword() + "</td>" +
                                "<td>" + roleDAO.findByID(resultUser.getRoleId()).getName() + "</td>" +

                                "<td><form action=\"editPage\" method=\"post\">" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + resultUser.getUserId() + "\">" +
                                "<input type=\"hidden\" name=\"login\" value=\"" + req.getParameter("login") + "\">" +
                                "<input type=\"submit\" name=\"edit\" value=\"edit\"></form></td>" +

                                "<td><form action=\"adminPage\" method=\"post\">" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + resultUser.getUserId() + "\">" +
                                "<input type=\"hidden\" name=\"login\" value=\"" + req.getParameter("login") + "\">" +
                                "<input type=\"submit\" name=\"delete\" value=\"delete\"></form></td>" +
                                "</tr>");
                    }
                }
                out.println("<tr><td><form action=\"adminPage\" method=\"post\"" +
                        "<input type=\"text\" readonly name=\"userId\"></td>" +
                        "<td><input type=\"text\" name=\"userName\"></td>" +
                        "<td><input type=\"text\" name=\"userPassword\"></td>" +
                        "<td><input type=\"text\" name=\"userRole\"></td>" +
                        "<td><input type=\"hidden\" name=\"login\" value=\"" + req.getParameter("login") + "\"></td>" +
                        "<td><input type=\"submit\" name=\"create\" value=\"create\"></form></td>" +
                        "</tr>"
                );
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("Sorry you are not admin");
            }
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }
}
