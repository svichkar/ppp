package com.nixsolutions.studentgrade.servlet;

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

    private String pageHtml;
    private HttpSession session;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = new DaoFactory();
        RoleDao roleDao = daoFactory.getRoleDao();
        List<Role> roles = roleDao.findAll();
        List<User> users = daoFactory.getUserDao().findAll();

        request.setAttribute("roles", roles);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(true);
        if (session.getAttribute("isAdmin").equals(true)) {
            DaoFactory daoFactory = new DaoFactory();

            RoleDao roleDao = daoFactory.getRoleDao();
            Role role = roleDao.findByName(request.getParameter("role"));

            UserDao userDao = daoFactory.getUserDao();
            User user = new User();

            if (request.getParameter("id") != null && request.getParameter("id") != "") {
                user.setUserId(Long.valueOf(request.getParameter("id")));
            }
            user.setFirstName(request.getParameter("fisrt_name"));
            user.setLastName(request.getParameter("last_name"));
            user.setLogin(request.getParameter("login"));
            user.setUserPassword(request.getParameter("pass"));
            user.setEmail(request.getParameter("email"));
            user.setRoleId(role.getRoleId());

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
                        message = "Success";
                    } else {

                        message = String.format("User with specified login OR e-mail already exists.", user.getLogin());
                    }
                }
                break;

                case "update": {
                    if (request.getSession(true).getAttribute("pageOwner").equals(user.getLogin()) == false) {
                        userDao.update(user);
                        message = "Success";

                    } else {

                        message = "User can't be updated by himself.";
                    }
                }
                break;

                case "delete": {
                    if (request.getSession(true).getAttribute("pageOwner").equals(user.getLogin()) == false) {
                        userDao.delete(user);
                        message = "Success";

                    } else {
                        message = "User can't be deleted by himself.";
                    }
                }
                break;
            }

        }
    }
}
