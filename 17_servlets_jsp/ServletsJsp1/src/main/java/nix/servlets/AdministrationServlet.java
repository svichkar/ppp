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
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.DaoFactoryH2;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "AdministrationServlet", urlPatterns = {"/administration"})
public class AdministrationServlet extends HttpServlet {

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
                    out.println("<title>Home Page</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Home Page</h1>");
                    out.println("Wlecome dear " + request.getSession().getAttribute("role") + " <b>"
                            + request.getSession().getAttribute("name") + "</b>");
                    out.println("<ul>");
                    out.println("<li><a href='index.html'>Login</a></li>");
                    out.println("<li><a href='homepage'>Homepage</a></li>");
                    if (request.getSession().getAttribute("role").equals("admin")) {
                        out.println("<li><a href='administration'>Administration</a></li>");
                    }
                    out.println("</ul>");
                    out.println(getUsersTable());
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    response.sendRedirect("homepage");
                }

            } else {
                response.sendRedirect("index.html");
            }
        }
    }

    protected String getUsersTable() {
        String usersTable = "<form><table>\n"
                + "<tr>\n"
                + "<th><button formaction='homepage' method='post'>add</button></th>\n"
                + "<th>ID</th>\n"
                + "<th>Login</th> \n"
                + "<th>Password</th>\n"
                + "<th>Role</th>\n"
                + "  </tr>\n";
        for (WebUser user : DaoFactoryH2.getWebUserDaoH2().getWebUserList()) {
            usersTable = usersTable.concat("<tr>\n"
                    + "<td>Actions</td>\n"
                    + "<td>" + user.getWebUserId().toString() + "</td>\n"
                    + "<td>" + user.getWebUserLogin() + "</td> \n"
                    + "<td>" + user.getWebUserPassword() + "</td>\n"
                    + "<td>" + DaoFactoryH2.getWebRoleDaoH2()
                            .findWebRoleById(user.getWebRoleId()).getWebRoleName() + "</td>\n"
                    + "  </tr>\n");
        }
        usersTable = usersTable.concat("</table></form>");
        return usersTable;
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
