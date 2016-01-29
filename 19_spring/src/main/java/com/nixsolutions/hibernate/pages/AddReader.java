package com.nixsolutions.hibernate.pages;

import com.nixsolutions.hibernate.dao.ClientDAO;
import com.nixsolutions.hibernate.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kozlovskij on 1/11/2016.
 */
@WebServlet("/addReader")
public class AddReader extends HttpServlet {
    @Autowired
    private ClientDAO clientDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = clientDAO.findAll();
        req.setAttribute("clients",clients);
        req.getRequestDispatcher("/WEB-INF/jsp/addReader.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = new Client();
        client.setClientFirstName(req.getParameter("clientFirstName"));
        client.setClientLastName(req.getParameter("clientLastName"));
        client.setClientPhone(req.getParameter("clientPhone"));
        client.setClientEmail(req.getParameter("clientEmail"));
        clientDAO.create(client);
        resp.sendRedirect("addReader?message=Added user with id " + client.getClientId());
    }
}