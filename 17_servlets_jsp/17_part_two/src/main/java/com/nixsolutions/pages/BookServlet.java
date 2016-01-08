package com.nixsolutions.pages;

import com.nixsolutions.library.dao.BookDAO;
import com.nixsolutions.library.dao.DaoFactory;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.Book;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Serko on 30.12.2015.
 */
@WebServlet("/bookManagement")
public class BookServlet extends HttpServlet {
    private static BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        bookDAO = daoFactory.getBookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> result = bookDAO.findAll();
        req.setAttribute("books", result);
        req.getRequestDispatcher("/WEB-INF/jsp/findBook.jsp").forward(req,resp);
    }
}
