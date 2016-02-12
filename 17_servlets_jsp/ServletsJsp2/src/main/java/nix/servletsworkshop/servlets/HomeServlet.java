/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.servletsworkshop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nix.jdbcworkshop.bean.CarOrderBean;
import nix.jdbcworkshop.bean.WebUserBean;
import nix.jdbcworkshop.utils.BeanFactory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/homepage"})
public class HomeServlet extends HttpServlet {

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
        WebUserBean webUserBean = BeanFactory.getWebUserBean(DaoFactoryH2.getWebUserDaoH2()
                .findWebUserByLogin(request.getSession().getAttribute("name").toString()));
        if (webUserBean.getRole().getWebRoleName().equals("user")) {
            List<CarOrderBean> currentCarOrderBeans = new ArrayList<>();
            for (CarOrderBean carOrderBean : BeanFactory
                    .getCarOrderBeans(DaoFactoryH2.getCarOrderDaoH2().getCarOrderList())) {
                if (carOrderBean.getCarBean().getClientBean().getWebUserBean().getWebUserId()
                        .equals(webUserBean.getWebUserId())) {
                    currentCarOrderBeans.add(carOrderBean);
                }
            }
            request.setAttribute("carOrderBeans", currentCarOrderBeans);
        } else if (webUserBean.getRole().getWebRoleName().equals("manager")) {
            List<CarOrderBean> unassignedCarOrderBeans = new ArrayList<>();
            for (CarOrderBean carOrderBean : BeanFactory
                    .getCarOrderBeans(DaoFactoryH2.getCarOrderDaoH2().getCarOrderList())) {
                if (DaoFactoryH2.getEmployeeCarOrderDaoH2().findEmployeeCarOrderByCarOrderId(carOrderBean.getCarOrderId()) == null) {
                    unassignedCarOrderBeans.add(carOrderBean);
                }
            }
            request.setAttribute("unassignedCarOrderBeans", unassignedCarOrderBeans);

        } else if (webUserBean.getRole().getWebRoleName().equals("employee")) {

        }

        request.getRequestDispatcher("WEB-INF/home.jsp").include(request, response);
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
