package com.nixsolutions.pages;

import com.nixsolutions.library.dao.CategoryDAO;
import com.nixsolutions.library.dao.DaoFactory;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.Category;
import com.nixsolutions.library.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kozlovskij on 1/11/2016.
 */
@WebServlet("/categoryManagement")
public class AddCategoryServlet extends HttpServlet{
    private static CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        categoryDAO = daoFactory.getCategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryDAO.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/jsp/addCategory.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = new Category(req.getParameter("categoryName"));
        category = categoryDAO.create(category);
        resp.sendRedirect("categoryManagement?message=Added category with id " + category.getCategoryId());
    }
}
