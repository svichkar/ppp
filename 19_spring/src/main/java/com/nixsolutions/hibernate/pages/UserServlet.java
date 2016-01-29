package com.nixsolutions.hibernate.pages;

import com.nixsolutions.hibernate.dao.RoleDAO;
import com.nixsolutions.hibernate.dao.UserDAO;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kozlovskij on 1/11/2016.
 */
@WebServlet("/userManagement")
public class UserServlet extends HttpServlet {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDAO.findAll();
        List<Role> roles = roleDAO.findAll();
        req.setAttribute("users", users);
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("add") != null) {
            if (req.getParameter("newUserLogin") != "" && req.getParameter("newUserPassword") != null) {
                User user = new User();
                user.setLogin(req.getParameter("newUserLogin"));
                user.setPassword(req.getParameter("newUserPassword"));
                user.setRole(roleDAO.findByID(Long.valueOf(req.getParameter("newUserRole"))));
                userDAO.create(user);
                resp.sendRedirect("userManagement?message=User added with id " + user.getUserId());
            } else {
                resp.sendRedirect("userManagement?message=User and Password should be filled");
            }
        } else if (req.getParameter("edit") != null) {
            User user = userDAO.findByID(Long.valueOf(req.getParameter("userId")));
            user.setLogin(req.getParameter("userLogin"));
            user.setPassword(req.getParameter("userPassword"));
            user.setRole(roleDAO.findByID(Long.valueOf(req.getParameter("userRole"))));
            userDAO.update(user);
            resp.sendRedirect("userManagement?message=User updated with id " + user.getUserId());
        } else if (req.getParameter("delete") != null) {
            User user = userDAO.findByID(Long.valueOf(req.getParameter("userId")));
            userDAO.delete(user);
            resp.sendRedirect("userManagement?message=User deleted with id " + user.getUserId());
        } else {
            resp.sendRedirect("userManagement");
        }
    }
}
