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
import nix.jdbcworkshop.entities.CarType;
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.BeanFactory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "CarModelServlet", urlPatterns = {"/car-models"})
public class CarModelsServlet extends HttpServlet {

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
        request.setAttribute("carModels",
                DaoFactoryH2.getCarTypeDaoH2().getCarTypeList());
        request.getRequestDispatcher("WEB-INF/car_models.jsp").include(request, response);

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
            CarType updatedCarType = new CarType(Long.valueOf(
                    request.getParameter("car-model-id")), request.getParameter("new-brand"),
                    request.getParameter("new-model"));
            DaoFactoryH2.getCarTypeDaoH2().update(updatedCarType);
            response.sendRedirect("car-models");
        }
        if ("delete".equals(request.getParameter("action"))) {
            DaoFactoryH2.getCarTypeDaoH2().delete(new CarType(Long.valueOf(
                    request.getParameter("car-model-id")), null, null));
            response.sendRedirect("car-models");
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
