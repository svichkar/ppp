package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.EmployeeCategoryDAO;
import com.nixsolutions.servicestation.dao.EmployeeDAO;
import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.entity.Employees;
import com.nixsolutions.servicestation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
@Service("employeeService")
@Path("/worker")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private EmployeeCategoryDAO employeeCategoryDAO;

    @Override
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/create")
    public Response create(@RequestBody Employee employee) {
        if (employee != null) {
            employeeDAO.create(employee);
            return Response.status(200).entity(employee.getEmployeeId()).build();
        } else {
            return Response.status(500).entity(employee.getEmployeeId()).build();
        }
    }

    @Override
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/update")
    public Response update(@RequestBody Employee employee) {
        if (employee != null) {
            employeeDAO.update(employee);
            return Response.status(200).entity(employee.getEmployeeId()).build();
        } else {
            return Response.status(500).entity(employee.getEmployeeId()).build();
        }
    }

    @Override
    @DELETE
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Employee entity = employeeDAO.findById(id);
        if (id != null && id != 0) {
            employeeDAO.delete(entity);
            return Response.status(200).entity(entity.getEmployeeId()).build();
        } else {
            return Response.status(500).entity(entity.getEmployeeId()).build();
        }
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Employee findById(@PathParam("id") Long id) {
        return employeeDAO.findById(id);
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/findAll")
    public Employees findAll() {
        Employees employees = new Employees();
        employees.setEmployeeSet(employeeDAO.findAll());
        return employees;
    }

    public Employee prepareEmployee(String firstName, String lastName, Long workerId, Long categoryId) {
        Employee employee = new Employee();
        employee.setEmployeeCategory(employeeCategoryDAO.findById(categoryId));
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        if (null != workerId) {
            employee.setEmployeeId(workerId);
        }
        return employee;
    }
}