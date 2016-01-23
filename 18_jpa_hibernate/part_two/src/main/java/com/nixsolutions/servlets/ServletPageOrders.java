
package com.nixsolutions.servlets;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import com.nixsolutions.servicestation.dao.impl.FactoryDAOImpl;
import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.entity.CarOrderStatus;
import com.nixsolutions.servicestation.entity.Employee;

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
import java.util.Set;


/**
 * Created by rybkinrolla on 18.01.2016.
 */

@WebServlet("/orders")
public class ServletPageOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        Set<CarOrder> ucobList = factoryDAO.getCarOrderDAO().getUserCarOrders();
        Set<CarOrderStatus> cosList = factoryDAO.getCarOrderStatusDAO().findAll();
        Set<Car> cbList = factoryDAO.getCarDAO().getCarWithoutOrder();
        Set<Employee> employeeList = factoryDAO.getEmployeeDAO().findAll();
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

        /*if(req.getParameter("reOrder") != null){
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
        }*/
        if (req.getParameter("delete") != null) {
            carOrder.setCarOrderId(Long.valueOf(req.getParameter("carOrderId")));
            factoryDAO.getCarOrderDAO().delete(carOrder);
            resp.sendRedirect("orders?message=Row was deleted");
        }
        if (req.getParameter("add") != null) {
            carOrder.getCar().setCarId(Long.valueOf(req.getParameter("cars")));
            carOrder.getCarOrderStatus().setCarOrderStatusId(Long.valueOf(req.getParameter("statuses")));
            carOrder.setStartDate(new Date());
            factoryDAO.getCarOrderDAO().create(carOrder);
            resp.sendRedirect("orders?message=Row was created");
        }
        if (req.getParameter("edit") != null) {
            carOrder.setCarOrderId(Long.valueOf(req.getParameter("carOrderId")));
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if(req.getParameter("startDate") != null) {
                try {
                    carOrder.setStartDate(formatter.parse(req.getParameter("startDate")));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            carOrder.getCarOrderStatus().setCarOrderStatusId(Long.valueOf(req.getParameter("statuses")));
            carOrder.getCar().setCarId(Long.valueOf(req.getParameter("carId")));
            if (carOrder.getCarOrderStatus().getCarOrderStatusId().equals(1)) {
                carOrder.setEndDate(new Date());
            } else{
                carOrder.setEndDate(null);
            }
            factoryDAO.getCarOrderDAO().update(carOrder);
            resp.sendRedirect("orders?message=Row was updated");
        }
    }
}
