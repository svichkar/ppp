/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.EmployeeCarOrder;

/**
 *
 * @author mednorcom
 */
public interface EmployeeCarOrderDao {
    public void create(EmployeeCarOrder employeeCarOrder);
    public void delete(EmployeeCarOrder employeeCarOrder);
    public EmployeeCarOrder findEmployeeCarOrderByCarOrderId(long carOrderId);
    public EmployeeCarOrder findEmployeeCarOrderByEmployeeId(long employeeId);
    public List<EmployeeCarOrder> getEmployeeCarOrderList();
    public List<EmployeeCarOrder> getEmployeeCarOrderList(int limit);
    public List<EmployeeCarOrder> getEmployeeCarOrderList(int offset, int limit);
}
