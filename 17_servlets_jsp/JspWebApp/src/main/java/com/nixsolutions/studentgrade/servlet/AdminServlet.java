package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.servlet.message.Message;
import com.nixsolutions.studentgrade.servlet.message.MessageType;

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
        List<User> users = daoFactory.getUserDao().findAll();

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

        User user = new User();
        Role role = roleDao.findByName(request.getParameter("role"));
        if (request.getParameter("id") != null && request.getParameter("id") != "") {
            user.setUserId(Long.valueOf(request.getParameter("id")));
        }
        user.setFirstName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setLogin(request.getParameter("login"));
        user.setUserPassword(request.getParameter("pass"));
        user.setEmail(request.getParameter("email"));
        user.setRoleId(role.getRoleId());

        Message m = new Message();
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
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("User with specified login OR e-mail already exists");
                }
            }
            break;

            case "update": {
                if (pageOwner.equals(user.getLogin()) == false) {
                    userDao.update(user);
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("User can't be updated by himself");
                }
            }
            break;

            case "delete": {
                if (pageOwner.equals(user.getLogin()) == false
                        && !"admin".equals(role.getRoleName())) {
                    userDao.delete(user);
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("User can't be deleted by himself");
                    if ("admin".equals(role.getRoleName())) {
                        m.setMessageType(MessageType.ERROR);
                        m.setMessageText("User with admin rights can't be deleted");
                    }
                }
            }
            break;
        }
        request.setAttribute("message", m);
        request.setAttribute("pageOwner", user);
        request.setAttribute("roles", roleDao.findAll());
        request.setAttribute("users", userDao.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
    }
}
