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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serko on 30.12.2015.
 */
@WebServlet("/bookManagement")
public class BookServlet extends HttpServlet {
    private static BookDAO bookDAO;
    private static CellDAO cellDAO;
    private static CategoryDAO categoryDAO;
    private static AuthorBookDAO authorBookDAO;
    private static AuthorDAO authorDAO;
    private static TicketDAO ticketDAO;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        bookDAO = daoFactory.getBookDAO();
        cellDAO = daoFactory.getCellDAO();
        categoryDAO = daoFactory.getCategoryDAO();
        authorBookDAO = daoFactory.getAuthorBookDAO();
        authorDAO = daoFactory.getAuthorDAO();
        ticketDAO = daoFactory.getTicketDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/findBook.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("searchCriteria")) {
            case "all":
                List<Ticket> tickets = ticketDAO.findAll();
                List<BeanBook> beanBooks = new ArrayList<>();
                List<Book> books = bookDAO.findAll();
                for (int i = 0; i < books.size(); i++) {
                    List<Author> authors = new ArrayList<>();
                    Book book =  books.get(i);
                    BeanBook beanBook = new BeanBook();
                    beanBook.setBook(book);
                    beanBook.setCategory(categoryDAO.findByID(book.getCategoryId()));
                    beanBook.setCell(cellDAO.findByID(book.getCellId()));
                    for (int j = 0; j < tickets.size(); j++) {
                        Ticket ticket =  tickets.get(j);
                        if (ticket.getBookId().equals(book.getBookId()) && ticket.getReturnDate() == null){
                            beanBook.setTicket(ticket);
                        }
                    }

                    List<AuthorBook> authorsBooks = authorBookDAO.findByBookID(book.getBookId());
                    for (int j = 0; j < authorsBooks.size(); j++) {
                        AuthorBook authorBook =  authorsBooks.get(j);
                        authors.add(authorDAO.findByID(authorBook.getAuthorId()));
                    }
                    beanBook.setAuthors(authors);

                    beanBooks.add(beanBook);
                }
                req.setAttribute("books", beanBooks);
                req.getRequestDispatcher("/WEB-INF/jsp/findBook.jsp").forward(req, resp);
                break;
            case "name":
                break;
            case "author":
                break;
            case "category":
                break;
            default:
                break;
        }
    }
}
