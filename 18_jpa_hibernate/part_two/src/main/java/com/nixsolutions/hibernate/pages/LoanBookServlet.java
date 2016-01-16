package com.nixsolutions.hibernate.pages;

import com.nixsolutions.hibernate.dao.*;
import com.nixsolutions.hibernate.dao.impl.DaoFactoryImpl;
import com.nixsolutions.hibernate.entity.Author;
import com.nixsolutions.hibernate.entity.Book;
import com.nixsolutions.hibernate.entity.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Serko on 10.01.2016.
 */
@WebServlet("/loanManagement")
public class LoanBookServlet extends HttpServlet {
    private static BookDAO bookDAO;
    private static CellDAO cellDAO;
    private static CategoryDAO categoryDAO;
    private static AuthorDAO authorDAO;
    private static ClientDAO clientDAO;
    private static TicketDAO ticketDAO;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        bookDAO = daoFactory.getBookDAO();
        cellDAO = daoFactory.getCellDAO();
        categoryDAO = daoFactory.getCategoryDAO();
        authorDAO = daoFactory.getAuthorDAO();
        clientDAO = daoFactory.getClientDAO();
        ticketDAO = daoFactory.getTicketDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", clientDAO.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/loanBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("giveBook") != null) {
            if (req.getParameterValues("bookId") != null) {
                for (String bookId : req.getParameterValues("bookId")) {
                    Ticket ticket = new Ticket();
                    ticket.setBook(bookDAO.findByID(Long.valueOf(bookId)));
                    ticket.setClient(clientDAO.findByID(Long.valueOf(req.getParameter("client"))));
                    ticket.setRentDate(new Date(System.currentTimeMillis()));
                    ticket.setExpiredDate(new Date(System.currentTimeMillis() + 345600000L));
                    ticketDAO.create(ticket);
                    resp.sendRedirect("loanManagement?message=new ticket added");
                }
            } else {
                resp.sendRedirect("loanManagement?message=please choose the client");
            }
        } else {
            resp.sendRedirect("loanManagement?message=any books chosen");
        }
    }
}
