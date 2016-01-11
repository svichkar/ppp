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
public class Employee {

    private long employeeId;
    private String firstName;
    private String lastName;
    private int employeeCategoryId;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeCategoryId() {
        return employeeCategoryId;
    }

    public void setEmployeeCategoryId(int employeeCategoryId) {
        this.employeeCategoryId = employeeCategoryId;
    }

}
