package com.nixsolutions.hibernate.pages;

import com.nixsolutions.hibernate.dao.CategoryDAO;
import com.nixsolutions.hibernate.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryDAO.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/jsp/addCategory.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = new Category();
        category.setCategoryName(req.getParameter("categoryName"));
        categoryDAO.create(category);
        resp.sendRedirect("categoryManagement?message=Added category with id " + category.getCategoryId());
    }
}
