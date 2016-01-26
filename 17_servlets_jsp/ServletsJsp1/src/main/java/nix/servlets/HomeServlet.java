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
import nix.jdbcworkshop.entities.WebRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/homepage"})
public class HomeServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (request.getSession().getAttribute("name") != null) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Home Page</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Home Page</h1>");
                out.println("Wlecome dear " + request.getSession().getAttribute("role") + " <b>"
                        + request.getSession().getAttribute("name") + "</b>");
                out.println("<p>" + (request.getAttribute("status") != null ? request
                        .getAttribute("status") : "") + "</p>");
                out.println("<ul>");
                out.println("<li><a href='index.html'>Login</a></li>");
                out.println("<li><a href='homepage'>Homepage</a></li>");
                if (request.getSession().getAttribute("role").equals("admin")) {
                    out.println("<li><a href='administration'>Administration</a></li>");
                }
                out.println("</ul>");
                out.println("</body>");
                out.println("</html>");
            } else {
                response.sendRedirect("index.html");
            }
        } catch (RuntimeException ex) {
            LOGGER.log(Level.FATAL, ex);
        }
    }

   
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
