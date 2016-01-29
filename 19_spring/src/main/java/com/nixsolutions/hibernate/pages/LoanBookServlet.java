package com.nixsolutions.hibernate.pages;

import com.nixsolutions.hibernate.dao.*;
import com.nixsolutions.hibernate.entity.Book;
import com.nixsolutions.hibernate.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serko on 10.01.2016.
 */
@WebServlet("/loanManagement")
public class LoanBookServlet extends HttpServlet {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private TicketDAO ticketDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/loanBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("bookId") != null) {
            if (req.getParameterValues("giveTicket") != null) {
                for (String bookId : req.getParameterValues("bookId")) {
                    Ticket ticket = new Ticket();
                    ticket.setBook(bookDAO.findByID(Long.valueOf(bookId)));
                    ticket.setClient(clientDAO.findByID(Long.valueOf(req.getParameter("client"))));
                    ticket.setRentDate(new Timestamp(System.currentTimeMillis()));
                    ticket.setExpiredDate(new Timestamp(System.currentTimeMillis() + 345600000L));
                    ticketDAO.create(ticket);
                }
                resp.sendRedirect("loanManagement?message=new ticket added");
            } else {
                List<Book> books = new ArrayList<>();
                for (String bookId:req.getParameterValues("bookId")) {
                    books.add(bookDAO.findByID(Long.valueOf(bookId)));
                }
                req.setAttribute("books", books);
                req.setAttribute("clients", clientDAO.findAll());
                req.getRequestDispatcher("/WEB-INF/jsp/loanBook.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect("loanManagement?message=any books chosen");
        }
    }
}
