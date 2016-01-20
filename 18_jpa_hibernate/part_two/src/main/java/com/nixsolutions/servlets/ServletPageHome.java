
package com.nixsolutions.servlets;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import com.nixsolutions.servicestation.dao.impl.FactoryDAOImpl;
import com.nixsolutions.servicestation.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/homepage")
public class ServletPageHome extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        User user = factoryDAO.getUserDAO().findByLogin(req.getParameter("login"));
        if (user != null) {
            if (user.getPassword().equals(req.getParameter("pass"))) {
                Role role = factoryDAO.getRoleDAO().findById(user.getRole().getRoleId());
                req.setAttribute("login", req.getParameter("login"));
                req.getSession().setAttribute("role", role.getRoleName());
                resp.sendRedirect("index.jsp");
            } else {
                resp.sendRedirect("index.jsp?cantLogin=Your password is wrong");
            }
        } else {
            resp.sendRedirect("index.jsp?cantLogin=Your login is wrong or doesn't exist");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        if (req.getSession().getAttribute("role") != null) {
            if (req.getSession().getAttribute("role").equals("manager")) {
                //req.setAttribute("userCarOrders", factoryDAO.getCarOrderDAO().getUserCarOrders());
                req.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(req, resp);
            } else {
                //req.setAttribute("userCarOrders", factoryDAO.getCarOrderDAO().getUserCarOrders(req.getParameter("login")));                req.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(req, resp);
           }
       } else {
           resp.sendRedirect("index.jsp?cantLogin=Please log in");
       }
   }

}

