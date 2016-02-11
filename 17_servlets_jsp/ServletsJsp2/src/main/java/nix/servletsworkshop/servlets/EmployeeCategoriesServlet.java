/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.servletsworkshop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nix.jdbcworkshop.entities.CarType;
import nix.jdbcworkshop.entities.EmployeeCategory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "EmployeeCategoriesServlet", urlPatterns = {"/employee-categories"})
public class EmployeeCategoriesServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("employeeCategories",
                DaoFactoryH2.getEmployeeCategoryDaoH2().getEmployeeCategoryList());
        request.getRequestDispatcher("WEB-INF/employee_categories.jsp").include(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("edit".equals(request.getParameter("action"))) {
            EmployeeCategory updatedEmloyeeCategory = new EmployeeCategory(Short.valueOf(
                    request.getParameter("employee-category-id")), 
                    request.getParameter("new-category-name"));
            DaoFactoryH2.getEmployeeCategoryDaoH2().update(updatedEmloyeeCategory);
            response.sendRedirect("employee-categories");
        }
        if ("delete".equals(request.getParameter("action"))) {
            DaoFactoryH2.getEmployeeCategoryDaoH2().delete(new EmployeeCategory(Short.valueOf(
                    request.getParameter("employee-category-id")), null));
            response.sendRedirect("employee-categories");
        }
        doGet(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
