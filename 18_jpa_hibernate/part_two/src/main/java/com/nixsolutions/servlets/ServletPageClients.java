
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
import java.util.Set;


/**
 * Created by rybkinrolla on 17.01.2016.
 */

@WebServlet("/clients")
public class ServletPageClients extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        Set<Client> ucSet = factoryDAO.getClientDAO().findClientsUsers();
        Set<Role> roleSet = factoryDAO.getRoleDAO().findAll();
        req.setAttribute("ucSet", ucSet);
        req.setAttribute("roleSet", roleSet);
        req.getRequestDispatcher("/WEB-INF/jsp/clients.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        boolean flag = true;
        User user = new User();
        Client client = new Client();
        Role role = new Role();
        Set<User> userSet = factoryDAO.getUserDAO().findAll();
        user.setLogin(req.getParameter("user_login"));
        user.setPassword(req.getParameter("user_password"));
        role.setRoleId(Long.valueOf(req.getParameter("roles")));
        user.setRole(role);
        client.setFirstName(req.getParameter("first_name"));
        client.setLastName(req.getParameter("last_name"));
        if ((req.getParameter("edit") != null) || (req.getParameter("add") != null)) {
            if (req.getParameter("edit") != null) {
                user.setUserId(Long.valueOf(req.getParameter("user_id")));
                factoryDAO.getUserDAO().update(user);
            }
            if (req.getParameter("add") != null) {
                if (!userSet.contains(user)) {
                    factoryDAO.getUserDAO().create(user);
                    userSet = factoryDAO.getUserDAO().findAll();
                } else {
                    flag = false;
                    resp.sendRedirect("clients?message=This%20login%20is%20already%20in%20use");
                }
            }
            if (req.getParameter("add") != null && flag) {
                for (User u : userSet) {
                    if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword()) && u.getRole().getRoleId().equals(user.getRole().getRoleId())) {
                        client.setUser(u);
                    }
                }
                factoryDAO.getClientDAO().create(client);
                resp.sendRedirect("clients?message=Row%20was%20created");
            }
            if (req.getParameter("edit") != null) {
                user.setUserId(Long.valueOf(req.getParameter("user_id")));
                client.setUser(user);
                client.setClientId(Long.valueOf(req.getParameter("client_id")));
                factoryDAO.getClientDAO().update(client);
                resp.sendRedirect("clients?message=Row%20was%20updated");
            }

        }
        if (req.getParameter("delete") != null) {
            if (!Integer.valueOf(req.getParameter("roles")).equals(1)) {
                client.setClientId(Long.valueOf(req.getParameter("client_id")));
                factoryDAO.getClientDAO().delete(client);
                user.setUserId(Long.valueOf(req.getParameter("user_id")));
                factoryDAO.getUserDAO().delete(user);
                resp.sendRedirect("clients?message=Row%20was%20deleted");
            } else {
                resp.sendRedirect("clients?message=You%20cant%20delete%20manager%20from%20here." +
                        "%20Please%20enter%20database%20and%20delete%20it%20there");
            }
        }
    }
}

