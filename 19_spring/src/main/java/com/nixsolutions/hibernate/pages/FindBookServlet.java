package com.nixsolutions.hibernate.pages;


import com.nixsolutions.hibernate.dao.*;
import com.nixsolutions.hibernate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

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
public class FindBookServlet extends HttpServlet {
    @Autowired
    private BookDAO bookDAO;

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
                books = bookDAO.findByCategory(req.getParameter("searchWord"));
                break;
            default:
                break;
        }
        if (books != null) {
            req.setAttribute("books", books);
            req.getRequestDispatcher("/WEB-INF/jsp/findBook.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("bookManagement?message=Sorry, not found");
        }
    }
}
