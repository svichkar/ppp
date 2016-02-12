package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.EmployeeDAO;
import com.nixsolutions.servicestation.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by rybkinrolla on 04.01.2016.
 */

@Repository("employeeDao")
public class EmployeeDAOImpl extends GenericAbstractDAO<Employee> implements EmployeeDAO {
}