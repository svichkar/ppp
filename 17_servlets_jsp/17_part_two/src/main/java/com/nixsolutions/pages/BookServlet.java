package com.nixsolutions.pages;

import com.nixsolutions.library.bean.BeanBook;
import com.nixsolutions.library.dao.*;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.*;


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
        List<Book> books = null;
        switch (req.getParameter("searchCriteria")) {
            case "all":
                books = bookDAO.findAll();
                break;
            case "name":
                books = bookDAO.findByName(req.getParameter("searchWord"));
                break;
            case "author":
                books = bookDAO.findByAuthor(req.getParameter("searchWord"));
                break;
            case "category":
                Category category = categoryDAO.findByName(req.getParameter("searchWord"));
                if (category != null) {
                    books = bookDAO.findByCategory(category.getCategoryId());
                }
                break;
            default:
                break;
        }
        if (books != null && books.size() > 0) {
            List<BeanBook> beanBooks = new ArrayList<>();
            for (int i = 0; i < books.size(); i++) {
                List<Author> authors = new ArrayList<>();
                Book book =  books.get(i);
                BeanBook beanBook = new BeanBook();
                beanBook.setBook(book);
                beanBook.setCategory(categoryDAO.findByID(book.getCategoryId()));
                beanBook.setCell(cellDAO.findByID(book.getCellId()));
                List<Ticket> tickets = ticketDAO.findByBookID(book.getBookId());
                if (tickets != null) {
                    for (int j = 0; j < tickets.size(); j++) {
                        Ticket ticket =  tickets.get(j);
                        if (ticket.getReturnDate() == null){
                            beanBook.setTicket(ticket);
                        }
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
        } else {
            resp.sendRedirect("bookManagement?message=Sorry, not found");
        }
    }
}
