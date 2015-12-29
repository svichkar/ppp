package com.nixsolutions.servicestation.entity;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class Employee {
    private Integer employeeId;
    private String firstName;
    private String secondName;
    private Integer employeeCategoryId;

    public Employee(String firstName, String secondName, Integer employeeCategoryId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.employeeCategoryId = employeeCategoryId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getEmployeeCategoryId() {
        return employeeCategoryId;
    }

    public void setEmployeeCategoryId(Integer employeeCategoryId) {
        this.employeeCategoryId = employeeCategoryId;
    }
}
