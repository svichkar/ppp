package com.nixsolutions.pages;

import com.nixsolutions.library.dao.DaoFactory;
import com.nixsolutions.library.dao.RoleDAO;
import com.nixsolutions.library.dao.UserDAO;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
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

/**
 * Created by Serko on 07.01.2016.
 */
@WebServlet("/mainPage")
public class LoginServlet extends HttpServlet {
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
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = userDAO.findByLogin(req.getParameter("userName").toLowerCase());

        if (req.getParameter("userName").equals("") | req.getParameter("userPassword").equals("")) {
            resp.sendRedirect("index.jsp?message=Please fill login and password");
        } else {
            if (req.getParameter("login") != null) {
                if (user != null) {
                    if (user.getPassword().equals(req.getParameter("userPassword"))) {
                        req.getSession().setAttribute("role", roleDAO.findByID(user.getRoleId()).getName());
                        req.getRequestDispatcher("/WEB-INF/jsp/homePage.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect("index.jsp?message=Login or password are wrong");
                    }
                }
            } else if (req.getParameter("registration") != null) {
                if (user == null) {
                    userDAO.create(new User(req.getParameter("userName"), req.getParameter("userPassword"),
                            roleDAO.findByName("LIBRARIAN").getRoleId()));
                    req.getSession().setAttribute("role", "LIBRARIAN");
                    req.getRequestDispatcher("/WEB-INF/jsp/homePage.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("index.jsp?message=User already exist choose another");
                }
            } else {
                resp.sendRedirect("index.jsp");
            }
        }
    }
}

