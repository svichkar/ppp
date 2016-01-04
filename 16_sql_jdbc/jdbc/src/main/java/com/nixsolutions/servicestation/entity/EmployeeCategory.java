package com.nixsolutions.servicestation.entity;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class EmployeeCategory {
    private Integer employeeCategoryId;
    private String name;

    public EmployeeCategory() {
    }

    public EmployeeCategory(String name) {
        this.name = name;
    }

    public Integer getEmployeeCategoryId() {
        return employeeCategoryId;
    }

    public void setEmployeeCategoryId(Integer employeeCategoryId) {
        this.employeeCategoryId = employeeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
