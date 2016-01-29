package com.nixsolutions.hibernate.pages;

import com.nixsolutions.hibernate.dao.*;
import com.nixsolutions.hibernate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Serko on 07.01.2016.
 */
@WebServlet("/mainPage")
public class LoginServlet extends HttpServlet {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private TicketDAO ticketDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("overdueTickets", ticketDAO.findOverdueTicket());
        req.getRequestDispatcher("/WEB-INF/jsp/homePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = userDAO.findByLogin(req.getParameter("userName"));

        if (req.getParameter("userName").equals("") | req.getParameter("userPassword").equals("")) {
            resp.sendRedirect("index.jsp?message=Please fill login and password");
        } else {
            if (req.getParameter("login") != null) {
                if (user != null) {
                    if (user.getPassword().equals(req.getParameter("userPassword"))) {
                        req.getSession().setAttribute("role", user.getRole().getRoleName());
                        req.getSession().setAttribute("currentUserId", user.getUserId());
                        req.setAttribute("overdueTickets", ticketDAO.findOverdueTicket());
                        req.getRequestDispatcher("/WEB-INF/jsp/homePage.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect("index.jsp?message=Login or password are wrong");
                    }
                } else {
                    resp.sendRedirect("index.jsp?message=Login or password are wrong");
                }
            } else if (req.getParameter("registration") != null) {
                if (user == null) {
                    user = new User();
                    user.setLogin(req.getParameter("userName"));
                    user.setPassword(req.getParameter("userPassword"));
                    user.setRole(roleDAO.findByName("librarian"));
                    userDAO.create(user);
                    req.getSession().setAttribute("role", "LIBRARIAN");
                    req.getSession().setAttribute("currentUserId", user.getUserId());
                    req.setAttribute("overdueTickets", ticketDAO.findOverdueTicket());
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

