package com.nixsolutions.servicestation.service;

import com.nixsolutions.servicestation.entity.Employee;

/**
 * Created by rybkinrolla on 29.12.2015.
 */


public interface EmployeeService extends GenericService<Employee> {
    Employee prepareEmployee(String firstName,String lastName,Long workerId,Long categoryId);
    Employee reorderEmployee(Long employeeId,Long orderId);
}
