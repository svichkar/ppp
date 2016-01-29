package com.nixsolutions.hibernate.pages;

import com.nixsolutions.hibernate.dao.*;
import com.nixsolutions.hibernate.entity.Author;
import com.nixsolutions.hibernate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Serko on 10.01.2016.
 */
@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CellDAO cellDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private AuthorDAO authorDAO;

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
            Book book = new Book();
            book.setBookName(req.getParameter("bookName"));
            book.setCell(cellDAO.findByID(Long.valueOf(req.getParameter("cellId"))));
            book.setCategory(categoryDAO.findByID(Long.valueOf(req.getParameter("categoryId"))));

            List<Author> authors = new ArrayList<>();
            for (String author:req.getParameterValues("authorId")) {
                authors.add(authorDAO.findByID(Long.valueOf(author)));
            }
            book.setAuthors(authors);
            bookDAO.create(book);
        }
        resp.sendRedirect("addBook?message=Added");
    }
}
