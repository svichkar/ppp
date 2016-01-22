
package com.nixsolutions.servlets;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import com.nixsolutions.servicestation.dao.impl.FactoryDAOImpl;
import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.entity.EmployeeCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rybkinrolla on 14.01.2016.
 */

@WebServlet("/workers")
public class ServletPageWorkers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        List<Employee> employeeList = factoryDAO.getEmployeeDAO().findAll();
        List<EmployeeCategory> employeeCategoryList = factoryDAO.getEmployeeCategoryDAO().findAll();
        req.setAttribute("employeeList", employeeList);
        req.setAttribute("employeeCategoryList", employeeCategoryList);
        req.getRequestDispatcher("/WEB-INF/jsp/workers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        Employee employee = new Employee();
        if (req.getParameter("delete") != null) {
            employee.setEmployeeId(Long.valueOf(req.getParameter("worker_id")));
            employee.getEmployeeCategory().setEmployeeCategoryId(Long.valueOf(req.getParameter("category")));
            /*for (Employee eco:factoryDAO.getEmployeeCarOrderDAO().findAll()) {
                if(eco.getEmployeeId().equals(employee.getEmployeeId())) {
                    factoryDAO.getEmployeeCarOrderDAO().delete(eco);
                }
            }*/
            factoryDAO.getEmployeeDAO().delete(employee);
            resp.sendRedirect("workers?message=Row was deleted");
        }
        if (req.getParameter("edit") != null) {
            employee.setEmployeeId(Long.valueOf(req.getParameter("worker_id")));
            employee.setFirstName(req.getParameter("first_name"));
            employee.setLastName(req.getParameter("last_name"));
            employee.getEmployeeCategory().setEmployeeCategoryId(Long.valueOf(req.getParameter("category")));
            factoryDAO.getEmployeeDAO().update(employee);
            resp.sendRedirect("workers?message=Row was updated");
        }
        if (req.getParameter("add") != null) {
            employee.setFirstName(req.getParameter("first_name"));
            employee.setLastName(req.getParameter("last_name"));
            employee.getEmployeeCategory().setEmployeeCategoryId(Long.valueOf(req.getParameter("category")));
            factoryDAO.getEmployeeDAO().create(employee);
            resp.sendRedirect("workers?message=Row was added");
        }
    }
}

