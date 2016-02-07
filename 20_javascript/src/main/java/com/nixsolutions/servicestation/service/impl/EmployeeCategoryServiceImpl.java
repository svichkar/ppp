package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.EmployeeCategoryDAO;
import com.nixsolutions.servicestation.entity.EmployeeCategory;
import com.nixsolutions.servicestation.service.EmployeeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
@Service("employeeCategoryService")
@Transactional
public class EmployeeCategoryServiceImpl implements EmployeeCategoryService {
    @Autowired
    EmployeeCategoryDAO employeeCategoryDAO;

    @Override
    public void create(EmployeeCategory entity) {
        employeeCategoryDAO.create(entity);
    }

    @Override
    public void update(EmployeeCategory entity) {
        employeeCategoryDAO.update(entity);
    }

    @Override
    public void delete(EmployeeCategory entity) {
        employeeCategoryDAO.delete(entity);
    }

    @Override
    public EmployeeCategory findById(Long id) {
        EmployeeCategory employeeCategory = employeeCategoryDAO.findById(id);
        return employeeCategory;
    }

    @Override
    public Set<EmployeeCategory> findAll() {
        Set<EmployeeCategory> employeeCategorySet = employeeCategoryDAO.findAll();
        return employeeCategorySet;
    }
}