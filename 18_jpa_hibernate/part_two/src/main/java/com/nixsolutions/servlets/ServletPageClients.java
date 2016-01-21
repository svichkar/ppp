
package com.nixsolutions.servlets;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import com.nixsolutions.servicestation.dao.impl.FactoryDAOImpl;
import com.nixsolutions.servicestation.entity.Client;
import com.nixsolutions.servicestation.entity.Role;
import com.nixsolutions.servicestation.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Created by rybkinrolla on 17.01.2016.
 */

@WebServlet("/clients")
public class ServletPageClients extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        List<User> ucbList = factoryDAO.getUserDAO().findClientsUsers();
        List<Role> roleList = factoryDAO.getRoleDAO().findAll();
        req.setAttribute("ucbList", ucbList);
        req.setAttribute("roleList", roleList);
        req.getRequestDispatcher("/WEB-INF/jsp/clients.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        boolean flag = true;
        User user = new User();
        Client client = new Client();
        List<User> userList = factoryDAO.getUserDAO().findAll();
        if ((req.getParameter("edit") != null) || (req.getParameter("add") != null)) {
            user.setLogin(req.getParameter("user_login"));
            user.setPassword(req.getParameter("user_password"));
            user.getRole().setRoleId(Long.valueOf(req.getParameter("roles")));
            if (req.getParameter("edit") != null) {
                user.setUserId(Long.valueOf(req.getParameter("user_id")));
                factoryDAO.getUserDAO().update(user);
            }
            if (req.getParameter("add") != null) {
                if (!userList.contains(user)) {
                    factoryDAO.getUserDAO().create(user);
                    userList = factoryDAO.getUserDAO().findAll();
                } else {
                    flag = false;
                    resp.sendRedirect("clients?message=This login is already in use");
                }
            }
            if (req.getParameter("add") != null && flag) {
                for (User u : userList) {
                    if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword()) && u.getRole().getRoleId().equals(user.getRole().getRoleId())) {
                        client.getUser().setUserId(u.getUserId());
                    }
                }
                client.setFirstName(req.getParameter("first_name"));
                client.setLastName(req.getParameter("last_name"));
                factoryDAO.getClientDAO().create(client);
                resp.sendRedirect("clients?message=Row was created");
            }
            if (req.getParameter("edit") != null) {
                client.setFirstName(req.getParameter("first_name"));
                client.setLastName(req.getParameter("last_name"));
                client.getUser().setUserId(Long.valueOf(req.getParameter("user_id")));
                client.setClientId(Long.valueOf(req.getParameter("client_id")));
                factoryDAO.getClientDAO().update(client);
                resp.sendRedirect("clients?message=Row was updated");
            }

        }
        if (req.getParameter("delete") != null) {
            if (!Integer.valueOf(req.getParameter("roles")).equals(1)) {
                client.setClientId(Long.valueOf(req.getParameter("client_id")));
                factoryDAO.getClientDAO().delete(client);
                user.setUserId(Long.valueOf(req.getParameter("user_id")));
                factoryDAO.getUserDAO().delete(user);
                resp.sendRedirect("clients?message=Row was deleted");
            } else {
                resp.sendRedirect("clients?message=You cant delete manager from here. Please enter database and delete it there");
            }
        }
    }
}

