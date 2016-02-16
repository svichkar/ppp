package com.nixsolutions.servicestation.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Created by rybkinrolla on 15.02.2016.
 */
@XmlRootElement
public class Employees {
    private Set<Employee> employeeSet;

    public Employees() {
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }
}
