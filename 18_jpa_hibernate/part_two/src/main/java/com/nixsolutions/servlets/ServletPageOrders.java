
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by rybkinrolla on 18.01.2016.
 */

@WebServlet("/orders")
public class ServletPageOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        Set<CarOrder> coSet = factoryDAO.getCarOrderDAO().getUserCarOrders();
        Set<CarOrderStatus> cosSet = factoryDAO.getCarOrderStatusDAO().findAll();
        Set<Employee> employeeSet = factoryDAO.getEmployeeDAO().findAll();
        req.setAttribute("employeeSet", employeeSet);
        req.setAttribute("cosSet", cosSet);
        req.setAttribute("coSet", coSet);
        Set<Car> cSet = factoryDAO.getCarDAO().getCarWithoutOrder();
        for (Car c:cSet) {
            System.out.println(c);
            //System.out.println(c.getCarId() + " "+c.getCarType() + " " +c.getSerialVIN());
        }
        req.setAttribute("cSet", cSet);
        req.getRequestDispatcher("/WEB-INF/jsp/orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        if(req.getParameter("reOrder") != null){
            Employee employee = factoryDAO.getEmployeeDAO().findById(Long.valueOf(req.getParameter("employees")));
            CarOrder carOrder = factoryDAO.getCarOrderDAO().findById(Long.valueOf(req.getParameter("orders")));
            Set<CarOrder> carOrderSet = employee.getCarOrderSet();
            carOrderSet.add(carOrder);
            employee.setCarOrderSet(carOrderSet);
            factoryDAO.getEmployeeDAO().update(employee);
            resp.sendRedirect("orders?message=This%20worker%20added%20to%20that%20order");
        }
        if (req.getParameter("delete") != null) {
            CarOrder carOrder = factoryDAO.getCarOrderDAO().findById(Long.valueOf(req.getParameter("carOrderId")));
            factoryDAO.getCarOrderDAO().delete(carOrder);
            resp.sendRedirect("orders?message=Row%20was%20deleted");
        }
        if (req.getParameter("add") != null) {
            CarOrder carOrder = new CarOrder();
            Car car = factoryDAO.getCarDAO().findById(Long.valueOf(req.getParameter("cars")));
            carOrder.setCar(car);
            carOrder.setStartDate(new Date());
            CarOrderStatus carOrderStatus = factoryDAO.getCarOrderStatusDAO().findById(Long.valueOf(req.getParameter("statuses2")));
            carOrder.setCarOrderStatus(carOrderStatus);
            factoryDAO.getCarOrderDAO().create(carOrder);
            resp.sendRedirect("orders?message=Row%20was%20created");
        }
        if (req.getParameter("edit") != null) {
            CarOrder carOrder = factoryDAO.getCarOrderDAO().findById(Long.valueOf(req.getParameter("carOrderId")));
            CarOrderStatus carOrderStatus = factoryDAO.getCarOrderStatusDAO().findById(Long.valueOf(req.getParameter("statuses")));
            carOrder.setCarOrderStatus(carOrderStatus);
            if (carOrder.getCarOrderStatus().getCarOrderStatusName().equals("Complete")) {
                carOrder.setEndDate(new Date());
            } else{
                carOrder.setEndDate(null);
            }
            factoryDAO.getCarOrderDAO().update(carOrder);
            resp.sendRedirect("orders?message=Row%20was%20updated");
        }
    }
}
