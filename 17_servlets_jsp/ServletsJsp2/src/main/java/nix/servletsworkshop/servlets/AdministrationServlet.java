/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.servletsworkshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.BeanFactory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "AdministrationServlet", urlPatterns = {"/administration"})
public class AdministrationServlet extends HttpServlet {

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
        request.setAttribute("webUserBeans",
                BeanFactory.getWebUserBeans(DaoFactoryH2.getWebUserDaoH2().getWebUserList()));
        if (request.getParameter("edit") != null) {
            request.setAttribute("webRoles", DaoFactoryH2.getWebRoleDaoH2().getWebRoleList());
        }
        request.getRequestDispatcher("WEB-INF/administration.jsp").include(request, response);

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
            WebUser updatedUser = new WebUser(Long.valueOf(
                    request.getParameter("user-id")), request.getParameter("new-login"),
                    request.getParameter("new-password"), Short.valueOf(request
                    .getParameter("new-role")));
            DaoFactoryH2.getWebUserDaoH2().update(updatedUser);
            response.sendRedirect("administration");
        }
        if ("delete".equals(request.getParameter("action"))) {
            DaoFactoryH2.getWebUserDaoH2().delete(new WebUser(Long.valueOf(
                    request.getParameter("user-id")), null, null, null));
            response.sendRedirect("administration");
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
