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
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "CreateUserServlet", urlPatterns = {"/create-user"})
public class CreateUserServlet extends HttpServlet {

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
            if (request.getSession().getAttribute("name") != null) {
                if (request.getSession().getAttribute("role").equals("admin")) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Create User</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Create User</h1>");
                    out.println("Wlecome dear " + request.getSession().getAttribute("role") + " <b>"
                            + request.getSession().getAttribute("name") + "</b>");
                    out.println("<p>" + (request.getAttribute("status") != null ? request.getAttribute("status") : "") + "</p>");
                    out.println("<ul>");
                    out.println("<li><a href='index.html'>Login</a></li>");
                    out.println("<li><a href='homepage'>Homepage</a></li>");

                    out.println("<li><a href='administration'>Administration</a></li>");

                    out.println("</ul>");

                    out.println("<form action='create-user' method='post'>\n<table>");
                    out.println("<tr><td>Login: </td><td><input type='text' size='40' name='new-login'/></td></tr>");
                    out.println("<tr><td>Password: </td><td><input type='password' size='40' name='new-password'/></td></tr>");
                    out.println("<tr><td>Role: </td><td>");

                    String roles = "";
                    for (WebRole role : DaoFactoryH2.getWebRoleDaoH2().getWebRoleList()) {
                        roles = roles.concat("<option value='" + role.getWebRoleId() + "'>"
                                + role.getWebRoleName() + "</option>\n");
                    }
                    out.println("<select name='new-role'>" + roles + "</select>");
                    out.println("</td></tr>");
                    out.println("<tr><td colspan='2'><input type='submit' name='status' value='create'></td></tr>");
                    out.println("</table>\n</form>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    response.sendRedirect("homepage");
                }

            } else {
                response.sendRedirect("index.html");
            }
        } catch (RuntimeException ex) {
            LOGGER.log(Level.FATAL, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        if (request.getSession().getAttribute("name") != null) {
            if (request.getSession().getAttribute("role").equals("admin")) {
                WebUser newUser = new WebUser(null,
                        request.getParameter("new-login"),
                        request.getParameter("new-password"),
                        Short.valueOf(request.getParameter("new-role")));
                DaoFactoryH2.getWebUserDaoH2().create(newUser);
                response.sendRedirect("administration");
            } else {
                response.sendRedirect("homepage");
            }

        } else {
            response.sendRedirect("index.html");
        }
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
