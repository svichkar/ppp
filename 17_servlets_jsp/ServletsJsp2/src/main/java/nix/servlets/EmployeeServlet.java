/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nix.jdbcworkshop.entities.Client;
import nix.jdbcworkshop.entities.Employee;
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.BeanFactory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employees"})
public class EmployeeServlet extends HttpServlet {

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
        request.setAttribute("employeeBeans",
                BeanFactory.getEmployeeBeans(DaoFactoryH2.getEmployeeDaoH2().getEmployeeList()));
        if (request.getParameter("edit") != null) {
            request.setAttribute("webUserBeans", BeanFactory.getWebUserBeans(DaoFactoryH2
                    .getWebUserDaoH2().getWebUserList()));
            request.setAttribute("employeeCategories", DaoFactoryH2
                    .getEmployeeCategoryDaoH2().getEmployeeCategoryList());
        }
        request.getRequestDispatcher("WEB-INF/employees.jsp").include(request, response);

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
        response.setContentType("text/html;charset=UTF-8");
        if ("edit".equals(request.getParameter("action"))) {
            Employee updatedEmployee = new Employee(Long.valueOf(
                    request.getParameter("employee-id")), request.getParameter("new-fname"),
                    request.getParameter("new-lname"),
                    Short.valueOf(request.getParameter("new-employee-category-id")),
                    Long.valueOf(request.getParameter("new-web-user")));
            DaoFactoryH2.getEmployeeDaoH2().update(updatedEmployee);
            response.sendRedirect("employees");
        }
        if ("delete".equals(request.getParameter("action"))) {
            DaoFactoryH2.getEmployeeDaoH2().delete(new Employee(Long.valueOf(
                    request.getParameter("employee-id")), null, null, null, null));
            response.sendRedirect("employees");
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
