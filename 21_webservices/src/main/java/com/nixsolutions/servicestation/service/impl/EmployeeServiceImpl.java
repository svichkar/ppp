package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.EmployeeCategoryDAO;
import com.nixsolutions.servicestation.dao.EmployeeDAO;
import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public void create(Employee entity) {
       employeeDAO.create(entity);
    }

    @Override
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/update")
    public void update(Employee entity) {
        employeeDAO.update(entity);
    }

    @Override
    @DELETE
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        employeeDAO.delete(employeeDAO.findById(id));
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
    @Path("/getAll")
    public Set<Employee> findAll() {
        Set<Employee> employeeSet = employeeDAO.findAll();
        return employeeSet;
    }

    public Employee prepareEmployee(String firstName,String lastName,Long workerId,Long categoryId){
        Employee employee = new Employee();
        employee.setEmployeeCategory(employeeCategoryDAO.findById(categoryId));
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        if(null != workerId){
            employee.setEmployeeId(workerId);
        }
        return employee;
    }
}