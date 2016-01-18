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
public class EmployeeCategory {

    private Short employeeCategoryId;
    private String name;

    public EmployeeCategory() {
    }

    public EmployeeCategory(Short employeeCategoryId, String name) {
        this.employeeCategoryId = employeeCategoryId;
        this.name = name;
    }

    public Short getEmployeeCategoryId() {
        return employeeCategoryId;
    }

    public void setEmployeeCategoryId(Short employeeCategoryId) {
        this.employeeCategoryId = employeeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
