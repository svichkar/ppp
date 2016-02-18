
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
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/create")
    public Response create(@RequestBody Employee employee) {
        if (employee.getLastName().equals("") && employee.getFirstName().equals("")) {
            employeeDAO.create(employee);
            return Response.ok(employee.getEmployeeId()).build();
        } else {
            return Response.serverError().entity("Can't create such user").build();
        }
    }

    @Override
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/update")
    public Response update(@RequestBody Employee employee) {
        if (employee.getLastName().equals("") && employee.getFirstName().equals("")) {
            employeeDAO.update(employee);
            return Response.ok(employee.getEmployeeId()).build();
        } else {
            return Response.serverError().entity("Can't update such user").build();
        }
    }

    @Override
    @DELETE
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if (id != null && id != 0) {
            Employee employee = employeeDAO.findById(id);
            if(employee != null) {
                employeeDAO.delete(employee);
                return Response.ok(employee.getEmployeeId()).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).entity("Worker not found for id: " + id).build();
            }
        } else {
            return Response.serverError().entity("id can't be empty or 0").build();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{id}")
    public Employee findById(@PathParam("id") Long id) {
        return employeeDAO.findById(id);
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_XML)
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
