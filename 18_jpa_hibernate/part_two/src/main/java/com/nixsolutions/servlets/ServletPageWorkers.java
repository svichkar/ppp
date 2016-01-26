
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
import java.util.Set;


/**
 * Created by rybkinrolla on 14.01.2016.
 */

@WebServlet("/workers")
public class ServletPageWorkers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        Set<Employee> employeeSet = factoryDAO.getEmployeeDAO().findAll();
        Set<EmployeeCategory> employeeCategorySet = factoryDAO.getEmployeeCategoryDAO().findAll();
        req.setAttribute("employeeSet", employeeSet);
        req.setAttribute("employeeCategorySet", employeeCategorySet);
        req.getRequestDispatcher("/WEB-INF/jsp/workers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        Employee employee = new Employee();
        EmployeeCategory employeeCategory = new EmployeeCategory();
        employeeCategory.setEmployeeCategoryId(Long.valueOf(req.getParameter("category")));
        employee.setEmployeeCategory(employeeCategory);
        employee.setFirstName(req.getParameter("first_name"));
        employee.setLastName(req.getParameter("last_name"));
        if (req.getParameter("delete") != null) {
            employee.setEmployeeId(Long.valueOf(req.getParameter("worker_id")));
            factoryDAO.getEmployeeDAO().delete(employee);
            resp.sendRedirect("workers?message=Row%20was%20deleted");
        }
        if (req.getParameter("edit") != null) {
            employee.setEmployeeId(Long.valueOf(req.getParameter("worker_id")));

            factoryDAO.getEmployeeDAO().update(employee);
            resp.sendRedirect("workers?message=Row%20was%20updated");
        }
        if (req.getParameter("add") != null) {
            employee.setFirstName(req.getParameter("first_name"));
            employee.setLastName(req.getParameter("last_name"));
            factoryDAO.getEmployeeDAO().create(employee);
            resp.sendRedirect("workers?message=Row%20was%20added");
        }
    }
}

