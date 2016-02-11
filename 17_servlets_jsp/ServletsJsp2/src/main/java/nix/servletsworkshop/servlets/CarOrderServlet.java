/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.servletsworkshop.servlets;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nix.jdbcworkshop.entities.Car;
import nix.jdbcworkshop.entities.CarOrder;
import nix.jdbcworkshop.entities.Client;
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.BeanFactory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "CarOrderServlet", urlPatterns = {"/car-orders"})
public class CarOrderServlet extends HttpServlet {

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

        request.setAttribute("carOrderBeans",
                BeanFactory.getCarOrderBeans(DaoFactoryH2.getCarOrderDaoH2().getCarOrderList()));
        if (request.getParameter("edit") != null) {
            request.setAttribute("carBeans", BeanFactory.getCarBeans(DaoFactoryH2
                    .getCarDaoH2().getCarList()));
            request.setAttribute("carOrderStatuses", DaoFactoryH2
                    .getCarOrderStatusDaoH2().getCarOrderStatusList());
        }
        request.getRequestDispatcher("WEB-INF/car_orders.jsp").include(request, response);

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
            Calendar startDate = Calendar.getInstance();
            startDate.set(Integer.valueOf(request.getParameter("start-year")),
                    Integer.valueOf(request.getParameter("start-month")),
                    Integer.valueOf(request.getParameter("start-day")),
                    Integer.valueOf(request.getParameter("start-hour")),
                    Integer.valueOf(request.getParameter("start-minute")));
            startDate.set(Calendar.MILLISECOND, 0);

            Calendar endDate = null;
            if (!(request.getParameter("end-year").equals("")
                    && request.getParameter("end-month").equals("")
                    && request.getParameter("end-day").equals("")
                    && request.getParameter("end-hour").equals("")
                    && request.getParameter("end-minute").equals(""))) {
                endDate = Calendar.getInstance();
                endDate.set(Integer.valueOf(request.getParameter("end-year")),
                        Integer.valueOf(request.getParameter("end-month")),
                        Integer.valueOf(request.getParameter("end-day")),
                        Integer.valueOf(request.getParameter("end-hour")),
                        Integer.valueOf(request.getParameter("end-minute")));
                endDate.set(Calendar.MILLISECOND, 0);
            }

            CarOrder newCarOrder = new CarOrder(Long.valueOf(request.getParameter("car-order-id")),
                    Long.valueOf(request.getParameter("new-car-id")),
                    Short.valueOf(request.getParameter("new-car-order-status-id")),
                    startDate.getTime(),
                    endDate != null ? endDate.getTime() : null);
            DaoFactoryH2.getCarOrderDaoH2().update(newCarOrder);
            response.sendRedirect("car-orders");
        }
        if ("delete".equals(request.getParameter("action"))) {
            DaoFactoryH2.getCarOrderDaoH2().delete(new CarOrder(Long.valueOf(
                    request.getParameter("car-order-id")), null, null, null, null));
            response.sendRedirect("car-orders");
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
