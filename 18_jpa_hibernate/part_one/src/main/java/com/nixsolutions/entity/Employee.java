package com.nixsolutions.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rybkinrolla on 13.01.2016.
 */
@Entity
public class Employee implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_category_id", referencedColumnName = "employee_category_id")
    private EmployeeCategory employeeCategory;

    @ManyToMany
    @JoinTable(name="employee_car_order",
                joinColumns ={@JoinColumn(name="employee_id")},
                inverseJoinColumns = {@JoinColumn(name="car_order_id")})
    private List<CarOrder> carOrderList;

    public Long getEmployeeId() {
        return employeeId;
    }

    public List<CarOrder> getCarOrderList() {
        return carOrderList;
    }

    public void setCarOrderList(List<CarOrder> carOrderList) {
        this.carOrderList = carOrderList;
    }

    public void setEmployeeId(Long employeeId) {
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

    public EmployeeCategory getEmployeeCategory() {
        return employeeCategory;
    }

    public void setEmployeeCategory(EmployeeCategory employeeCategory) {
        this.employeeCategory = employeeCategory;
    }
}
