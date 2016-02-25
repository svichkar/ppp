/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.servletsworkshop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nix.jdbcworkshop.bean.AssignmentBean;
import nix.jdbcworkshop.entities.CarOrder;
import nix.jdbcworkshop.entities.CarOrderStatus;
import nix.jdbcworkshop.entities.EmployeeCarOrder;
import nix.jdbcworkshop.utils.BeanFactory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "AssignmentsServlet", urlPatterns = {"/assignments"})
public class AssignmentServlet extends HttpServlet {

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
        Long carOrderId = Long.valueOf(request.getParameter("car-order-id"));
        request.setAttribute("carOrderBean",
                BeanFactory.getCarOrderBean(DaoFactoryH2.getCarOrderDaoH2()
                        .findCarOrderById(carOrderId)));

        List<AssignmentBean> currentOrderAssignmetns = new ArrayList<>();
        for (AssignmentBean assignmentBean : BeanFactory.getAssignmentBeans(
                DaoFactoryH2.getEmployeeCarOrderDaoH2().getEmployeeCarOrderList())) {
            if (assignmentBean.getCarOrderBean().getCarOrderId().equals(carOrderId)) {
                currentOrderAssignmetns.add(assignmentBean);
            }
        }
        
        request.setAttribute("assignmentBeans", currentOrderAssignmetns);
        request.getRequestDispatcher("WEB-INF/assignments.jsp").include(request, response);

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
        if ("delete".equals(request.getParameter("action"))) {
            DaoFactoryH2.getEmployeeCarOrderDaoH2().delete(new EmployeeCarOrder(
                    Long.valueOf(request.getParameter("employee-id")),
                    Long.valueOf(request.getParameter("car-order-id"))));
            if (DaoFactoryH2.getEmployeeCarOrderDaoH2().findEmployeeCarOrderByCarOrderId(
                    Long.valueOf(request.getParameter("car-order-id"))) == null) {
                Short doneStatusId = null;
                for (CarOrderStatus orderStatus : DaoFactoryH2.getCarOrderStatusDaoH2()
                        .getCarOrderStatusList()) {
                    if (orderStatus.getName().equals("done")) {
                        doneStatusId = orderStatus.getCarOrderStatusId();
                    }
                }
                CarOrder doneOrder = DaoFactoryH2.getCarOrderDaoH2().findCarOrderById(Long.valueOf(request
                        .getParameter("car-order-id")));
                doneOrder.setEndDate(new Date());
                doneOrder.setCarOrderStatusId(doneStatusId);
                DaoFactoryH2.getCarOrderDaoH2().update(doneOrder);
            }
            response.sendRedirect("assignments?car-order-id="
                    + request.getParameter("car-order-id"));
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
