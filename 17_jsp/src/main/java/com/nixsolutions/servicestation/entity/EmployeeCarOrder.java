package com.nixsolutions.servicestation.entity;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class EmployeeCarOrder {
    private Integer identifier;
    private Integer employeeId;
    private Integer carOrderId;

    public EmployeeCarOrder() {
    }

    public EmployeeCarOrder(Integer identifier, Integer employeeId, Integer carOrderId) {
        this.identifier = identifier;
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

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }
}
