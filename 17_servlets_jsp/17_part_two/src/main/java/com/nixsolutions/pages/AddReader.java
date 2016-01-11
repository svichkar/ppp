package com.nixsolutions.pages;

import com.nixsolutions.library.dao.ClientDAO;
import com.nixsolutions.library.dao.DaoFactory;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.Client;

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
    private static ClientDAO clientDAO;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        clientDAO = daoFactory.getClientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = clientDAO.findAll();
        req.setAttribute("clients",clients);
        req.getRequestDispatcher("/WEB-INF/jsp/addReader.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = new Client(req.getParameter("clientFirstName"), req.getParameter("clientLastName"), req.getParameter("clientPhone"), req.getParameter("clientEmail"));
        client = clientDAO.create(client);
        resp.sendRedirect("addReader?message=Added user with id " + client.getClientId());
    }
}