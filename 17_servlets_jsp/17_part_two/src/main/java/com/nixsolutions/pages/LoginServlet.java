package com.nixsolutions.pages;

import com.nixsolutions.library.bean.OverdueBook;
import com.nixsolutions.library.dao.*;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.Book;
import com.nixsolutions.library.entity.Client;
import com.nixsolutions.library.entity.Ticket;
import com.nixsolutions.library.entity.User;

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
 * Created by Serko on 07.01.2016.
 */
@WebServlet("/mainPage")
public class LoginServlet extends HttpServlet {
    private static UserDAO userDAO;
    private static RoleDAO roleDAO;
    private static BookDAO bookDAO;
    private static TicketDAO ticketDAO;
    private static ClientDAO clientDAO;


    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        userDAO = daoFactory.getUserDAO();
        roleDAO = daoFactory.getRoleDAO();
        ticketDAO = daoFactory.getTicketDAO();
        bookDAO = daoFactory.getBookDAO();
        clientDAO = daoFactory.getClientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = userDAO.findByLogin(req.getParameter("userName").toLowerCase());

        if (req.getParameter("userName").equals("") | req.getParameter("userPassword").equals("")) {
            resp.sendRedirect("index.jsp?message=Please fill login and password");
        } else {
            if (req.getParameter("login") != null) {
                if (user != null) {
                    if (user.getPassword().equals(req.getParameter("userPassword"))) {
                        req.getSession().setAttribute("role", roleDAO.findByID(user.getRoleId()).getName());
                        req.getSession().setAttribute("currentUserId", user.getUserId());
                        req.setAttribute("overdueBooks", this.displayOverdueBook());
                        req.getRequestDispatcher("/WEB-INF/jsp/homePage.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect("index.jsp?message=Login or password are wrong");
                    }
                }
            } else if (req.getParameter("registration") != null) {
                if (user == null) {
                    user = userDAO.create(new User(req.getParameter("userName"), req.getParameter("userPassword"),
                            roleDAO.findByName("LIBRARIAN").getRoleId()));
                    req.getSession().setAttribute("role", "LIBRARIAN");
                    req.getSession().setAttribute("currentUserId", user.getUserId());
                    req.setAttribute("overdueBooks", this.displayOverdueBook());
                    req.getRequestDispatcher("/WEB-INF/jsp/homePage.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("index.jsp?message=User already exist choose another");
                }
            } else {
                resp.sendRedirect("index.jsp");
            }
        }
    }
    private List<OverdueBook> displayOverdueBook (){
        List<OverdueBook> overdueBookList = new ArrayList<>();
        List<Ticket> tickets = ticketDAO.findAll();
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket =  tickets.get(i);
            if (ticket.getExpiredDate().before(new Date(System.currentTimeMillis())) && ticket.getReturnDate() == null) {
                OverdueBook overdueBook = new OverdueBook();
                overdueBook.setTicket(ticket);
                overdueBook.setClient(clientDAO.findByID(ticket.getClientId()));
                overdueBook.setBook(bookDAO.findByID(ticket.getBookId()));
                overdueBookList.add(overdueBook);
            }
        }
        return overdueBookList;
    }
}

