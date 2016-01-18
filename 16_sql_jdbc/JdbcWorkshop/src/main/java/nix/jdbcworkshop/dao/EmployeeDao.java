/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.Employee;

/**
 *
 * @author mednorcom
 */
public interface EmployeeDao {

    public void create(Employee employee);

    public void update(Employee employee);

    public void delete(Employee employee);

    public Employee findEmployeeById(long employeeId);

    public List<Employee> getEmployeeList();

    public List<Employee> getEmployeeList(int limit);

    public List<Employee> getEmployeeList(int offset, int limit);
}
