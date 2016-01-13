package com.nixsolutions.servlets;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import com.nixsolutions.servicestation.dao.impl.ImplFactoryDAO;
import com.nixsolutions.servicestation.entity.*;
import com.nixsolutions.servicestation.entity.extendedentity.UserCarOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by rybkinrolla on 12.01.2016.
 */
@WebServlet("/homepage")
public class ServletPageHome extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new ImplFactoryDAO();
        User user = factoryDAO.getUserDAO().findByLogin(req.getParameter("login"));
        if (user != null) {
            if (user.getPassword().equals(req.getParameter("pass"))) {
                Role role = factoryDAO.getRoleDAO().findById(user.getRoleId());
                req.setAttribute("login", req.getParameter("login"));
                req.setAttribute("role", role.getRoleName());
                if (role.getRoleName().equals("manager")) {
                    req.setAttribute("userCarOrders",factoryDAO.getCarOrderDAO().getUserCarOrders());
                    req.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(req, resp);
                } else {
                    req.setAttribute("userCarOrders",factoryDAO.getCarOrderDAO().getUserCarOrders(req.getParameter("login")));
                    req.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(req, resp);
                }
            } else {
                resp.sendRedirect("index.jsp?cantLogin=Your password is wrong");
            }
        } else {
            resp.sendRedirect("index.jsp?cantLogin=Your login is wrong or doesn't exist");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

}
