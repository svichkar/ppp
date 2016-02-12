package com.nixsolutions.servicestation.service;

import com.nixsolutions.servicestation.entity.Employee;

import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Created by rybkinrolla on 29.12.2015.
 */


public interface EmployeeService {
    void create(Employee entity);

    void update(Employee entity);

    void delete(Long id);

    Employee findById(Long id);

    Set<Employee> findAll();
    Employee prepareEmployee(String firstName,String lastName,Long workerId,Long categoryId);
}
