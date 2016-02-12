package com.nixsolutions.servicestation.service;

import com.nixsolutions.servicestation.entity.EmployeeCategory;

import java.util.Set;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface EmployeeCategoryService {
    void create(EmployeeCategory entity);

    void update(EmployeeCategory entity);

    void delete(EmployeeCategory entity);

    EmployeeCategory findById(Long id);

    Set<EmployeeCategory> findAll();
}
