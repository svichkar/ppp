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

/**
 * Created by Serko on 01.01.2016.
 */
@WebServlet("/editPage")
public class EditPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        DaoFactory daoFactory = new DaoFactoryImpl();
        UserDAO dao = daoFactory.getUserDAO();
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        if (req.getSession().getAttribute("isAdmin").equals(true)) {
            if (req.getParameter("edit") != null) {
                Integer id = Integer.parseInt(req.getParameter("id"));
                User user = dao.findByID(id);
                out.println("<html>");
                out.println("<head>");
                out.println("<title>admin page</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p>Welcome " + req.getParameter("login") + "</p>");
                out.println("<form action=\"adminPage\" method=\"post\">" +
                        "UserID: <input type=\"text\" readonly name=\"userId\" value=\"" + user.getUserId() + "\">" +
                        "Login: <input type=\"text\" name=\"userName\" value=\"" + user.getLogin() + "\">" +
                        "Password: <input type=\"text\" name=\"password\" value=\"" + user.getPassword() + "\">" +
                        "Role: <input type=\"text\" name=\"role\" value=\"" + roleDAO.findByID(user.getRoleId()).getName() + "\">" +
                        "<input type=\"hidden\" name=\"edit\" value=\"edit\">" +
                        "<input type=\"hidden\" name=\"login\" value=\"" + req.getParameter("login") + "\">" +
                        "<input type=\"submit\" value=\"save\"></form>");
                out.println("</body>");
                out.println("</html>");
            }
            out.close();
        } else {
            out.println("Sorry you are not admin");
        }
    }
}
