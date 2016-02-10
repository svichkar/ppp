package com.nixsolutions.servicestation.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rybkinrolla on 13.01.2016.
 */
@Entity
@Table(name="employee_category")
public class EmployeeCategory implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_category_id")
    private Long employeeCategoryId;
    @Column(name = "employee_category_name", length = 100, nullable = false)
    private String employeeCategoryName;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_category_id", referencedColumnName = "employee_category_id")
    private Set<Employee> employeeList;

    public EmployeeCategory() {
    }

    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Long getEmployeeCategoryId() {
        return employeeCategoryId;
    }

    public void setEmployeeCategoryId(Long employeeCategoryId) {
        this.employeeCategoryId = employeeCategoryId;
    }

    public String getEmployeeCategoryName() {
        return employeeCategoryName;
    }

    public void setEmployeeCategoryName(String employeeCategoryName) {
        this.employeeCategoryName = employeeCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeCategory that = (EmployeeCategory) o;

        if (employeeCategoryId != null ? !employeeCategoryId.equals(that.employeeCategoryId) : that.employeeCategoryId != null)
            return false;
        return employeeCategoryName.equals(that.employeeCategoryName);

    }

    @Override
    public int hashCode() {
        int result = employeeCategoryId != null ? employeeCategoryId.hashCode() : 0;
        result = 31 * result + employeeCategoryName.hashCode();
        return result;
    }
}
