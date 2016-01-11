/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.entities;

/**
 *
 * @author mednorcom
 */
public class EmployeeCarOrder {

    private long employeeId;
    private long carOrderId;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(long carOrderId) {
        this.carOrderId = carOrderId;
    }

}
