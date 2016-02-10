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
import nix.jdbcworkshop.entities.Car;
import nix.jdbcworkshop.entities.Client;
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.BeanFactory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "CarServlet", urlPatterns = {"/cars"})
public class CarServlet extends HttpServlet {

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
        request.setAttribute("carBeans",
                BeanFactory.getCarBeans(DaoFactoryH2.getCarDaoH2().getCarList()));
        if (request.getParameter("edit") != null) {
            request.setAttribute("clientBeans", BeanFactory.getClientBeans(DaoFactoryH2
                    .getClientDaoH2().getClientList()));
            request.setAttribute("carTypes", DaoFactoryH2
                    .getCarTypeDaoH2().getCarTypeList());
        }
        request.getRequestDispatcher("WEB-INF/cars.jsp").include(request, response);

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
            Car updatedCar = new Car(Long.valueOf(
                    request.getParameter("car-id")), request.getParameter("new-sid"),
                    Long.valueOf(request.getParameter("new-car-type-id")), 
                    Long.valueOf(request.getParameter("new-client-id")));
            DaoFactoryH2.getCarDaoH2().update(updatedCar);
            response.sendRedirect("cars");
        }
        if ("delete".equals(request.getParameter("action"))) {
            DaoFactoryH2.getCarDaoH2().delete(new Car(Long.valueOf(
                    request.getParameter("car-id")), null, null, null));
            response.sendRedirect("cars");
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
