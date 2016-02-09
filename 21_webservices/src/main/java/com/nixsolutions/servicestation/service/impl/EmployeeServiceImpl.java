package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.EmployeeDAO;
import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;

    @Override
    public void create(Employee entity) {
        employeeDAO.create(entity);
    }

    @Override
    public void update(Employee entity) {
        employeeDAO.update(entity);
    }

    @Override
    public void delete(Employee entity) {
        employeeDAO.delete(entity);
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = employeeDAO.findById(id);
        return employee;
    }

    @Override
    public Set<Employee> findAll() {
        Set<Employee> employeeSet = employeeDAO.findAll();
        return employeeSet;
    }
}