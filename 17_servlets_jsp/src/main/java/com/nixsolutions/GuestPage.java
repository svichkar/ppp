package com.nixsolutions;

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
import java.io.PrintWriter;

/**
 * Created by Serko on 30.12.2015.
 */
@WebServlet("/guestPage")
public class GuestPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        DaoFactory daoFactory = new DaoFactoryImpl();
        UserDAO dao = daoFactory.getUserDAO();
        User user = dao.findByLogin(req.getParameter("login"));
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        Role role = roleDAO.findByID(user.getRoleId());
        out.println("<html>\n" +
                "<head>\n" +
                "<title>login page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>You enter login " + req.getParameter("login") + "</p>\r\n" +
                "<p>Your user id " + user.getUserId() + "</p>\n" +
                "<p>Your role " + role.getName() + "</p>\n" +
                "</body>\n" +
                "</html>");
        out.close();
    }
}
