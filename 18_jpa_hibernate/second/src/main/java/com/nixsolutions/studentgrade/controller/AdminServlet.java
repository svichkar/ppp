package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by svichkar on 12/29/2015.
 */

@WebServlet(name = "AdminPage",
        description = "Admin page for creating/updating users",
        urlPatterns = "/admin",
        loadOnStartup = 0)
public class AdminServlet extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = new DaoFactory();
        RoleDao roleDao = daoFactory.getRoleDao();
        UserDao userDao = daoFactory.getUserDao();

        List<Role> roles = roleDao.findAll();
        List<User> users = userDao.findAll();

        session = request.getSession(false);
        String pageOwner = (String) session.getAttribute("user");
        User user = userDao.findByLogin(pageOwner);

        request.setAttribute("pageOwner", user);
        request.setAttribute("roles", roles);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(false);
        String pageOwner = (String) session.getAttribute("user");

        DaoFactory daoFactory = new DaoFactory();
        RoleDao roleDao = daoFactory.getRoleDao();
        UserDao userDao = daoFactory.getUserDao();

        String roleName = request.getParameter("role");
        String userId = request.getParameter("id");

        User user = new User();
        Role role = roleDao.findByName(roleName);
        if (userId != null && userId != "") {
            user.setUserId(Long.valueOf(userId));
        }
        user.setFirstName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setLogin(request.getParameter("login"));
        user.setUserPassword(request.getParameter("pass"));
        user.setEmail(request.getParameter("email"));
        user.setRole(role);

        String message = "";

        switch (request.getParameter("operation")) {

            case "add": {

                boolean isUnique = true;
                for (User u : userDao.findAll()) {

                    if (user.getLogin().equals(u.getLogin()) || user.getEmail().equals(u.getEmail())) {
                        isUnique = false;
                    }
                }

                if (isUnique) {
                    userDao.create(user);
                    message = String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");
                } else {

                    message = String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "User with specified login OR e-mail already exists</h4></p>");
                }
                break;
            }

            case "update": {

                if (pageOwner.equals(user.getLogin()) == false) {

                    User update = userDao.findById(Long.valueOf(userId));

                    update.setFirstName(user.getFirstName());
                    update.setLastName(user.getLastName());
                    update.setEmail(user.getEmail());
                    update.setLogin(user.getLogin());
                    update.setUserPassword(user.getUserPassword());
                    update.setRole(user.getRole());

                    boolean isUnique = true;
                    for (User u : userDao.findAll()) {

                        if (update.getUserId() != u.getUserId() &&
                                (update.getLogin().equals(u.getLogin()) || update.getEmail().equals(u.getEmail()))) {
                            isUnique = false;
                        }
                    }

                    if (isUnique) {
                        userDao.update(update);
                        message = String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                                "Success</h4></p>");
                    } else {
                        message = String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                                "User with specified login OR e-mail already exists</h4></p>");
                    }
                } else {
                    message = String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "User can't be updated by himself</h4></p>");
                }
                break;
            }

            case "delete": {

                if (pageOwner.equals(user.getLogin()) == false
                        && !role.getRoleName().equals("admin")) {

                    User delete = userDao.findById(Long.valueOf(userId));
                    userDao.delete(delete);
                    message = String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");

                } else {
                    message = String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "User can't be deleted by himself</h4></p>");

                    if (role.getRoleName().equals("admin")) {
                        message = String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                                "User with <b>admin</b> rights can't be deleted</h4></p>");
                    }
                }
            }
            break;
        }

        request.setAttribute("message", message);
        request.setAttribute("pageOwner", user);
        request.setAttribute("roles", roleDao.findAll());
        request.setAttribute("users", userDao.findAll());

        request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
    }
}
