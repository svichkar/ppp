/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nix.jdbcworkshop.entities.Employee;
import nix.jdbcworkshop.entities.WebRole;
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "AddEmployeeServlet", urlPatterns = {"/add-employee"})
public class AddEmployeeServlet extends HttpServlet {

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
        for (WebRole webRole : DaoFactoryH2.getWebRoleDaoH2().getWebRoleList()) {
            if (webRole.getWebRoleName().equals("employee")) {
                request.setAttribute("webRole", webRole);
            }
        }
        request.setAttribute("employeeCategories", DaoFactoryH2.getEmployeeCategoryDaoH2()
                .getEmployeeCategoryList());
        request.getRequestDispatcher("WEB-INF/add_employee.jsp")
                .include(request, response);
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

        WebUser newUser = new WebUser(null,
                request.getParameter("new-login"),
                request.getParameter("new-password"),
                Short.valueOf(request.getParameter("new-role")));
        DaoFactoryH2.getWebUserDaoH2().create(newUser);

        Employee newEmployee = new Employee(null,
                request.getParameter("new-fname"),
                request.getParameter("new-lname"),
                Short.valueOf(request.getParameter("new-employee-category-id")),
                newUser.getWebUserId());
        DaoFactoryH2.getEmployeeDaoH2().create(newEmployee);
        response.sendRedirect("employees");

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
