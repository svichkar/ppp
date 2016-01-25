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
import javax.servlet.http.HttpSession;
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.getSession().invalidate();
            String name = request.getParameter("j_username");
            String password = request.getParameter("j_password");
            WebUser user = DaoFactoryH2.getWebUserDaoH2().findWebUserByLogin(name);
            if (user != null && name != null && password != null) {
                if (password.equals(user.getWebUserPassword())) {
                    out.print("Welcome, " + name);
                    HttpSession session = request.getSession();
                    session.setAttribute("name", user.getWebUserLogin());
                    session.setAttribute("role", DaoFactoryH2.getWebRoleDaoH2()
                            .findWebRoleById(user.getWebRoleId()).getWebRoleName());

                    //request.getRequestDispatcher("homepage").forward(request, response);
                    response.sendRedirect("homepage");
                } else {
                    out.print("Sorry, username or password error!");
                    request.getRequestDispatcher("index.html").include(request, response);
                    //response.sendRedirect("index.html");

                }
            } else {
                out.print("Sorry, username or password error!");
                request.getRequestDispatcher("index.html").include(request, response);
                //response.sendRedirect("index.html");
            }
            out.close();
        } catch (RuntimeException ex) {
            LOGGER.log(Level.FATAL, ex);
        }
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
        processRequest(request, response);
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
