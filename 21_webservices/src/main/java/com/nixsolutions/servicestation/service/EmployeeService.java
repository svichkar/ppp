package com.nixsolutions.servicestation.service;

import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.entity.Employees;

import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Created by rybkinrolla on 29.12.2015.
 */


public interface EmployeeService {
    Response create(Employee employee);

    Response update(Employee employee);

    Response delete(Long id);

    Employee findById(Long id);

    Employees findAll();

    Employee prepareEmployee(String firstName,String lastName,Long workerId,Long categoryId);
}
