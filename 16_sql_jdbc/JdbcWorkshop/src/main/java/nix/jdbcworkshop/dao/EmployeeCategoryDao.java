/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.EmployeeCategory;

/**
 *
 * @author mednorcom
 */
public interface EmployeeCategoryDao {
    public void create(EmployeeCategory employeeCategory);
    public void update(EmployeeCategory employeeCategory);
    public void delete(EmployeeCategory employeeCategory);
    public EmployeeCategory findEmployeeCategoryById(long employeeCategoryId);
    public List<EmployeeCategory> getEmployeeCategoryList();
    public List<EmployeeCategory> getEmployeeCategoryList(int limit);
    public List<EmployeeCategory> getEmployeeCategoryList(int offset, int limit);
}
