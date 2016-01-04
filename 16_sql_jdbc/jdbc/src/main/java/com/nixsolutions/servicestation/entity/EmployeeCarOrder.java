package com.nixsolutions.servicestation.entity;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class EmployeeCarOrder {
    private Integer employeeId;
    private Integer carOrderId;

    public EmployeeCarOrder() {
    }

    public EmployeeCarOrder(Integer employeeId, Integer carOrderId) {
        this.employeeId = employeeId;
        this.carOrderId = carOrderId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(Integer carOrderId) {
        this.carOrderId = carOrderId;
    }
}
