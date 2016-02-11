/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.servletsworkshop.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nix.jdbcworkshop.entities.CarOrder;
import nix.jdbcworkshop.utils.BeanFactory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "AddCarOrderServlet", urlPatterns = {"/add-car-order"})
public class AddCarOrderServlet extends HttpServlet {

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
        request.setAttribute("carOrderStatuses", DaoFactoryH2.getCarOrderStatusDaoH2().getCarOrderStatusList());
        request.setAttribute("carBeans", BeanFactory.getCarBeans(DaoFactoryH2.getCarDaoH2().getCarList()));
        request.setAttribute("dateNow", new Date());
        request.getRequestDispatcher("WEB-INF/add_car_order.jsp").include(request, response);
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

        Calendar startDate = Calendar.getInstance();
        startDate.set(Integer.valueOf(request.getParameter("start-year")),
                Integer.valueOf(request.getParameter("start-month")),
                Integer.valueOf(request.getParameter("start-day")),
                Integer.valueOf(request.getParameter("start-hour")),
                Integer.valueOf(request.getParameter("start-minute")));
        startDate.set(Calendar.MILLISECOND, 0);
        /*
        Calendar endDate = Calendar.getInstance();
        endDate.set(Integer.valueOf(request.getParameter("end-year")),
                Integer.valueOf(request.getParameter("end-month")),
                Integer.valueOf(request.getParameter("end-day")),
                Integer.valueOf(request.getParameter("end-hour")),
                Integer.valueOf(request.getParameter("end-minute")));
        endDate.set(Calendar.MILLISECOND, 0);
         */
        CarOrder newCarOrder = new CarOrder(null,
                Long.valueOf(request.getParameter("new-car-id")),
                Short.valueOf(request.getParameter("new-car-order-status-id")),
                startDate.getTime(),
                null);
        DaoFactoryH2.getCarOrderDaoH2().create(newCarOrder);
        response.sendRedirect("car-orders");

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
