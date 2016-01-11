package com.nixsolutions.pages;

import com.nixsolutions.library.bean.BeanUser;
import com.nixsolutions.library.dao.DaoFactory;
import com.nixsolutions.library.dao.RoleDAO;
import com.nixsolutions.library.dao.UserDAO;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.Role;
import com.nixsolutions.library.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozlovskij on 1/11/2016.
 */
@WebServlet("/userManagement")
public class UserServlet extends HttpServlet {
    private static UserDAO userDAO;
    private static RoleDAO roleDAO;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        userDAO = daoFactory.getUserDAO();
        roleDAO = daoFactory.getRoleDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDAO.findAll();
        List<BeanUser> beanUsers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user =  users.get(i);
            Role role = roleDAO.findByID(user.getRoleId());
            BeanUser beanUser = new BeanUser();
            beanUser.setUser(user);
            beanUser.setRole(role);
            beanUsers.add(beanUser);
        }
        List<Role> roles = roleDAO.findAll();
        req.setAttribute("users", beanUsers);
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("add") != null) {
            if (req.getParameter("newUserLogin") != "" && req.getParameter("newUserPassword") != null) {
                User user = new User(req.getParameter("newUserLogin"), req.getParameter("newUserPassword"), Integer.valueOf(req.getParameter("newUserRole")));
                user = userDAO.create(user);
                resp.sendRedirect("userManagement?message=User added with id " + user.getUserId());
            } else {
                resp.sendRedirect("userManagement?message=User and Password should be filled");
            }
        } else if (req.getParameter("edit") != null) {
            User user = new User(Integer.valueOf(req.getParameter("userId")), req.getParameter("userLogin"),
                    req.getParameter("userPassword"), Integer.valueOf(req.getParameter("userRole")));
            userDAO.update(user);
            resp.sendRedirect("userManagement?message=User updated with id " + user.getUserId());
        } else if (req.getParameter("delete") != null) {
            User user = new User(Integer.valueOf(req.getParameter("userId")), req.getParameter("userLogin"),
                    req.getParameter("userPassword"), Integer.valueOf(req.getParameter("userRole")));
            userDAO.delete(user);
            resp.sendRedirect("userManagement?message=User deleted with id " + user.getUserId());
        } else {
            resp.sendRedirect("userManagement");
        }
    }
}
