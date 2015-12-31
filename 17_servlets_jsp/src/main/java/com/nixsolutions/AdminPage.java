package com.nixsolutions;

import com.nixsolutions.library.dao.DaoFactory;
import com.nixsolutions.library.dao.RoleDAO;
import com.nixsolutions.library.dao.UserDAO;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Serko on 30.12.2015.
 */
@WebServlet("/adminPage")
public class AdminPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        if (LoginProcess.getSession() != null) {
            out.println("You are admin");
        } else {
            out.println("You are not login");
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        DaoFactory daoFactory = new DaoFactoryImpl();
        UserDAO dao = daoFactory.getUserDAO();
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        if (LoginProcess.getSession() != null) {
            if (req.getParameter("delete") != null) {
                Integer id = Integer.parseInt(req.getParameter("id"));
                dao.delete(dao.findByID(id));
            }
            List<User> result = dao.findAll();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>admin page</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Welcome " + req.getParameter("login") + "</p>");
            out.println("<table border=\"1px\"><tr><td>User_ID</td><td>name</td><td>Role</td></tr>");
            for (int i = 0; i < result.size(); i++) {
                User resultUser = result.get(i);
                if (resultUser.getLogin().equals(req.getParameter("login"))) {
                    out.println("<tr><td>" + resultUser.getUserId() + "</td><td>" + resultUser.getLogin() + "</td><td>" +
                            roleDAO.findByID(resultUser.getRoleId()).getName() +
                            "</td><td><form><input type=\"button\" value=\"edit\"></form></td></tr>");
                } else {
                    out.println("<tr><td>" + resultUser.getUserId() + "</td><td>" + resultUser.getLogin() + "</td><td>" +
                            roleDAO.findByID(resultUser.getRoleId()).getName() +
                            "</td><td><form action=\"adminPage\" method=\"post\">" +
                            "<input type=\"hidden\" name=\"id\" value=\"" + resultUser.getUserId() + "\">" +
                            "<input type=\"hidden\" name=\"login\" value=\"" + req.getParameter("login") + "\">" +
                            "<input type=\"submit\" name=\"delete\" value=\"delete\">" +
                            "</form></td><td><form><input type=\"button\" value=\"edit\"></form></td></tr>");
                }
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
        out.close();
    }
}
