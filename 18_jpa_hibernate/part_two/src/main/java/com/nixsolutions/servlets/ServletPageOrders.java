/*
package com.nixsolutions.servlets;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import com.nixsolutions.servicestation.dao.impl.FactoryDAOImpl;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.entity.CarOrderStatus;
import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.entity.EmployeeCarOrder;
import com.nixsolutions.servicestation.entity.extendedentity.CarBean;
import com.nixsolutions.servicestation.entity.extendedentity.UserCarOrderBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

*/
/**
 * Created by rybkinrolla on 18.01.2016.
 *//*

@WebServlet("/orders")
public class ServletPageOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        List<UserCarOrderBean> ucobList = factoryDAO.getCarOrderDAO().getUserCarOrders();
        List<CarOrderStatus> cosList = factoryDAO.getCarOrderStatusDAO().findAll();
        List<CarBean> cbList = factoryDAO.getCarDAO().getCarWithoutOrder();
        List<Employee> employeeList = factoryDAO.getEmployeeDAO().findAll();
        req.setAttribute("employeeList", employeeList);
        req.setAttribute("cosList", cosList);
        req.setAttribute("ucobList", ucobList);
        req.setAttribute("cbList", cbList);
        req.getRequestDispatcher("/WEB-INF/jsp/orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        CarOrder carOrder = new CarOrder();

        if(req.getParameter("reOrder") != null){
            List<EmployeeCarOrder> employeeCarOrderList = factoryDAO.getEmployeeCarOrderDAO().findAll();
            EmployeeCarOrder employeeCarOrder = new EmployeeCarOrder();
            employeeCarOrder.setEmployeeId(Integer.valueOf(req.getParameter("employees")));
            employeeCarOrder.setCarOrderId(Integer.valueOf(req.getParameter("orders")));
            if(employeeCarOrderList.contains(employeeCarOrder)){
                resp.sendRedirect("orders?message=That worker is already attached to that order");
            } else {
                factoryDAO.getEmployeeCarOrderDAO().create(employeeCarOrder);
                resp.sendRedirect("orders?message=This worker added to that order");
            }
        }
        if (req.getParameter("delete") != null) {
            carOrder.setCarOrderId(Integer.valueOf(req.getParameter("carOrderId")));
            factoryDAO.getCarOrderDAO().delete(carOrder);
            resp.sendRedirect("orders?message=Row was deleted");
        }
        if (req.getParameter("add") != null) {
            carOrder.setCarId(Integer.valueOf(req.getParameter("cars")));
            carOrder.setCarOrderStatusId(Integer.valueOf(req.getParameter("statuses")));
            carOrder.setStartDate(new Date());
            factoryDAO.getCarOrderDAO().create(carOrder);
            resp.sendRedirect("orders?message=Row was created");
        }
        if (req.getParameter("edit") != null) {
            carOrder.setCarOrderId(Integer.valueOf(req.getParameter("carOrderId")));
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if(req.getParameter("startDate") != null) {
                try {
                    carOrder.setStartDate(formatter.parse(req.getParameter("startDate")));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            carOrder.setCarOrderStatusId(Integer.valueOf(req.getParameter("statuses")));
            carOrder.setCarId(Integer.valueOf(req.getParameter("carId")));
            if (carOrder.getCarOrderStatusId().equals(1)) {
                carOrder.setEndDate(new Date());
            } else{
                carOrder.setEndDate(null);
            }
            factoryDAO.getCarOrderDAO().update(carOrder);
            resp.sendRedirect("orders?message=Row was updated");
        }
    }
}
*/
