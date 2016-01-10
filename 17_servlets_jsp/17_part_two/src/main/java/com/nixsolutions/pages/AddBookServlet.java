package com.nixsolutions.pages;

import com.nixsolutions.library.dao.*;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.AuthorBook;
import com.nixsolutions.library.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Serko on 10.01.2016.
 */
@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    private static BookDAO bookDAO;
    private static CellDAO cellDAO;
    private static CategoryDAO categoryDAO;
    private static AuthorBookDAO authorBookDAO;
    private static AuthorDAO authorDAO;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        bookDAO = daoFactory.getBookDAO();
        cellDAO = daoFactory.getCellDAO();
        categoryDAO = daoFactory.getCategoryDAO();
        authorBookDAO = daoFactory.getAuthorBookDAO();
        authorDAO = daoFactory.getAuthorDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryDAO.findAll());
        req.setAttribute("cells", cellDAO.findAll());
        req.setAttribute("authors", authorDAO.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/addBook.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (int i = 0; i <Integer.valueOf(req.getParameter("bookQuantity")) ; i++) {
            Book book = new Book(req.getParameter("bookName"), cellDAO.findByName(req.getParameter("bookCell")).getCellId(),
                    categoryDAO.findByName(req.getParameter("bookCategory")).getCategoryId());
            book = bookDAO.create(book);
            for (String author:req.getParameterValues("bookAuthor")) {
                authorBookDAO.create(new AuthorBook(authorDAO.findByName(author).getAuthorId(), book.getBookId()));
            }
        }
        resp.sendRedirect("addBook?message=Added");
    }
}
