package com.nixsolutions.pages;

import com.nixsolutions.library.dao.DaoFactory;
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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public static Logger LOGGER = LogManager.getLogger(MainPage.class.getName());
    private DaoFactory daoFactory = new DaoFactoryImpl();
    private UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = userDAO.findByLogin(req.getParameter("userName").toLowerCase());
        try (PrintWriter out = resp.getWriter()) {
            if (req.getParameter("userName").equals("") | req.getParameter("userPassword").equals("")) {
                resp.sendRedirect("index.jsp?message=Login or Password is empty. Please fill them and try again");
                /*req.setAttribute("message", "Login or Password is empty. Please fill them and try again");
                req.getRequestDispatcher("index.jsp").forward(req,resp);*/
            }
            if (req.getParameter("login") != null) {
                out.println("You are click on login button");
            } else if (req.getParameter("registration") != null) {
                out.println("You are click on registration button");
            } else {
                out.println("Hi, how you doing?");
            }
            if (user != null) {
                out.println("your id is: " + user.getUserId());
            } else {
                out.println("user not found");
            }
        } catch (IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }
}
