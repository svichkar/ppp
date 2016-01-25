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
@WebServlet(name = "AdministrationServlet", urlPatterns = {"/administration"})
public class AdministrationServlet extends HttpServlet {

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
                    /*if ("edit".equals(request.getParameter("action"))) {
                        WebUser updatedUser = new WebUser(Long.valueOf(request.getParameter("user-id")), request.getParameter("new-login"), request.getParameter("new-password"), Short.valueOf(request.getParameter("new-role")));
                        DaoFactoryH2.getWebUserDaoH2().update(updatedUser);
                        response.sendRedirect("administration");
                    }
                    if ("delete".equals(request.getParameter("action"))) {
                        DaoFactoryH2.getWebUserDaoH2().delete(new WebUser(Long.valueOf(request.getParameter("user-id")), null, null, null));
                    }*/
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Administration</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Administration</h1>");
                    out.println("Wlecome dear " + request.getSession().getAttribute("role") + " <b>"
                            + request.getSession().getAttribute("name") + "</b>");
                    out.println("<p>" + (request.getAttribute("status") != null ? request.getAttribute("status") : "") + "</p>");
                    out.println("<ul>");
                    out.println("<li><a href='index.html'>Login</a></li>");
                    out.println("<li><a href='homepage'>Homepage</a></li>");
                    if (request.getSession().getAttribute("role").equals("admin")) {
                        out.println("<li><a href='administration'>Administration</a></li>");
                    }
                    out.println("</ul>");
                    out.println(getUsersTable(request));
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

    protected String getUsersTable(HttpServletRequest request) {
        String usersTable = "<form method='post'><table border='1px'>\n"
                + "<tr>\n"
                + "<th><button formaction='create-user' formmethod='get'>add</button></th>\n"
                + "<th>ID</th>\n"
                + "<th>Login</th> \n"
                + "<th>Password</th>\n"
                + "<th>Role</th>\n"
                + "  </tr>\n";
        for (WebUser user : DaoFactoryH2.getWebUserDaoH2().getWebUserList()) {
            if (user.getWebUserId().toString().equals(request.getParameter("edit"))) {
                String roles = "";
                for (WebRole role : DaoFactoryH2.getWebRoleDaoH2().getWebRoleList()) {
                    roles = roles.concat("<option " + (role.getWebRoleId().equals(user.getWebRoleId()) ? "selected" : "") + " value='" + role.getWebRoleId() + "'>"
                            + role.getWebRoleName() + "</option>\n");
                }

                usersTable = usersTable.concat("<tr>\n"
                        + "<td><button formaction='administration' method='post' name='action' value='edit'>ok</button>"
                        + "<button formaction='administration' method='post' name='action' value='cancel'>cancel</button></td>\n"
                        + "<td><input type='hidden' size='40' name='user-id' value='" + user.getWebUserId().toString() + "'/>" + user.getWebUserId().toString() + "</td>\n"
                        + "<td><input type='text' size='20' name='new-login' value='" + user.getWebUserLogin() + "'/></td> \n"
                        + "<td><input type='password' size='20' name='new-password' value='" + user.getWebUserPassword() + "'/></td>\n"
                        + "<td><select name='new-role'>" + roles + "</select></td>\n"
                        + "  </tr>\n");
            } else if (user.getWebUserId().toString().equals(request.getParameter("delete"))) {
                usersTable = usersTable.concat("<tr>\n"
                        + "<td><button formaction='administration' method='post' name='action' value='delete'>ok</button>"
                        + "<button formaction='administration' method='post' name='action' value='cancel'>cancel</button></td>\n"
                        + "<td><input type='hidden' size='40' name='user-id' value='" + user.getWebUserId().toString() + "'/>" + user.getWebUserId().toString() + "</td>\n"
                        + "<td>" + user.getWebUserLogin() + "</td> \n"
                        + "<td>" + user.getWebUserPassword().substring(0, 1) + "***</td>\n"
                        + "<td>" + DaoFactoryH2.getWebRoleDaoH2()
                        .findWebRoleById(user.getWebRoleId()).getWebRoleName() + "</td>\n"
                        + "  </tr>\n");
            } else {
                usersTable = usersTable.concat("<tr>\n"
                        + "<td><button formaction='administration' method='get' name='edit' value='" + user.getWebUserId().toString() + "'>edit</button>"
                        + "<button formaction='administration' method='get' name='delete' value='" + user.getWebUserId().toString() + "'>delete</button></td>\n"
                        + "<td>" + user.getWebUserId().toString() + "</td>\n"
                        + "<td>" + user.getWebUserLogin() + "</td> \n"
                        + "<td>" + user.getWebUserPassword().substring(0, 1) + "***</td>\n"
                        + "<td>" + DaoFactoryH2.getWebRoleDaoH2()
                        .findWebRoleById(user.getWebRoleId()).getWebRoleName() + "</td>\n"
                        + "  </tr>\n");
            }
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
        // processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getSession().getAttribute("name") != null) {
                if (request.getSession().getAttribute("role").equals("admin")) {
                    if ("edit".equals(request.getParameter("action"))) {
                        WebUser updatedUser = new WebUser(Long.valueOf(request.getParameter("user-id")), request.getParameter("new-login"), request.getParameter("new-password"), Short.valueOf(request.getParameter("new-role")));
                        DaoFactoryH2.getWebUserDaoH2().update(updatedUser);
                        response.sendRedirect("administration");
                    }
                    if ("delete".equals(request.getParameter("action"))) {
                        DaoFactoryH2.getWebUserDaoH2().delete(new WebUser(Long.valueOf(request.getParameter("user-id")), null, null, null));
                        response.sendRedirect("administration");
                    }
                } else {
                    response.sendRedirect("homepage");
                }
            } else {
                response.sendRedirect("index.html");
            }
            //response.sendRedirect("administration");
            doGet(request, response);
        } catch (RuntimeException ex) {
            LOGGER.log(Level.FATAL, ex);
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
