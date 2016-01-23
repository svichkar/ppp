package com.nixsolutions.servicestation.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by rybkinrolla on 13.01.2016.
 */
@Entity
@Table(name = "car_order")
public class CarOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_order_id")
    private Long carOrderId;
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Car car;
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_order_status_id", referencedColumnName = "car_order_status_id")
    private CarOrderStatus carOrderStatus;
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "employee_car_order",
            joinColumns = {@JoinColumn(name = "car_order_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "employee_id", nullable = false)})
    private Set<Employee> employeeList;

    public CarOrder() {
    }

    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Long getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(Long carOrderId) {
        this.carOrderId = carOrderId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CarOrderStatus getCarOrderStatus() {
        return carOrderStatus;
    }

    public void setCarOrderStatus(CarOrderStatus carOrderStatus) {
        this.carOrderStatus = carOrderStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object obj) {
        CarOrder carOrder = (CarOrder) obj;
        if (carOrderId.equals(carOrder.carOrderId) && car.equals(carOrder.car) &&
                carOrderStatus.equals(carOrder.carOrderStatus) && startDate.equals(carOrder.startDate) &&
                endDate.equals(carOrder.endDate)) {
            return true;
        } else {
            return false;
        }
    }
}
