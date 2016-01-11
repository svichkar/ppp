package com.nixsolutions.pages;

import com.nixsolutions.library.bean.BeanBook;
import com.nixsolutions.library.dao.*;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.Author;
import com.nixsolutions.library.entity.AuthorBook;
import com.nixsolutions.library.entity.Book;
import com.nixsolutions.library.entity.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Serko on 10.01.2016.
 */
@WebServlet("/loanManagement")
public class LoanBookServlet extends HttpServlet{
    private static BookDAO bookDAO;
    private static CellDAO cellDAO;
    private static CategoryDAO categoryDAO;
    private static AuthorBookDAO authorBookDAO;
    private static AuthorDAO authorDAO;
    private static ClientDAO clientDAO;
    private static TicketDAO ticketDAO;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        bookDAO = daoFactory.getBookDAO();
        cellDAO = daoFactory.getCellDAO();
        categoryDAO = daoFactory.getCategoryDAO();
        authorBookDAO = daoFactory.getAuthorBookDAO();
        authorDAO = daoFactory.getAuthorDAO();
        clientDAO = daoFactory.getClientDAO();
        ticketDAO = daoFactory.getTicketDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/loanBook.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("giveBook") != null) {
            List<BeanBook> beanBooks = new ArrayList<>();
            for (String bookId : req.getParameterValues("bookId")) {
                List<Author> authors = new ArrayList<>();
                Book book = bookDAO.findByID(Integer.valueOf(bookId));
                BeanBook beanBook = new BeanBook();
                beanBook.setBook(book);
                beanBook.setCategory(categoryDAO.findByID(book.getCategoryId()));
                beanBook.setCell(cellDAO.findByID(book.getCellId()));
                List<AuthorBook> authorsBooks = authorBookDAO.findByBookID(book.getBookId());
                for (int j = 0; j < authorsBooks.size(); j++) {
                    AuthorBook authorBook = authorsBooks.get(j);
                    authors.add(authorDAO.findByID(authorBook.getAuthorId()));
                }
                beanBook.setAuthors(authors);

                beanBooks.add(beanBook);
            }
            req.setAttribute("clients", clientDAO.findAll());
            req.setAttribute("books", beanBooks);
            req.getRequestDispatcher("/WEB-INF/jsp/loanBook.jsp").forward(req, resp);
        } else if (req.getParameter("giveTicket") != null) {
            if (req.getParameterValues("bookId") != null) {
                for (String bookId : req.getParameterValues("bookId")) {
                    Ticket ticket = new Ticket();
                    ticket.setBookId(Integer.valueOf(bookId));
                    ticket.setClientId(Integer.valueOf(req.getParameter("client")));
                    ticket.setRentDate(new Date(System.currentTimeMillis()));
                    ticket.setExpiredDate(new Date(System.currentTimeMillis() + 345600000L));
                    ticketDAO.create(ticket);
                }
                resp.sendRedirect("loanManagement?message=new ticket added");
            } else {
                resp.sendRedirect("loanManagement?message=any books checked");
            }
        } else {
            resp.sendRedirect("loanManagement");
        }
    }
}
