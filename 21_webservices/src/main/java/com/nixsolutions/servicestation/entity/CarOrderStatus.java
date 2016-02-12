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
@Table(name="car_order_status")
public class CarOrderStatus implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_order_status_id")
    private Long carOrderStatusId;
    @Column(name = "car_order_status_name", length = 100, nullable = false)
    private String carOrderStatusName;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_order_status_id", referencedColumnName = "car_order_status_id")
    private Set<CarOrder> carOrderList;

    public CarOrderStatus() {
    }

    public Long getCarOrderStatusId() {
        return carOrderStatusId;
    }

    public void setCarOrderStatusId(Long carOrderStatusId) {
        this.carOrderStatusId = carOrderStatusId;
    }

    public String getCarOrderStatusName() {
        return carOrderStatusName;
    }

    public void setCarOrderStatusName(String carOrderStatusName) {
        this.carOrderStatusName = carOrderStatusName;
    }

    public Set<CarOrder> getCarOrderList() {
        return carOrderList;
    }

    public void setCarOrderList(Set<CarOrder> carOrderList) {
        this.carOrderList = carOrderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarOrderStatus that = (CarOrderStatus) o;

        if (carOrderStatusId != null ? !carOrderStatusId.equals(that.carOrderStatusId) : that.carOrderStatusId != null)
            return false;
        return carOrderStatusName.equals(that.carOrderStatusName);

    }

    @Override
    public int hashCode() {
        int result = carOrderStatusId != null ? carOrderStatusId.hashCode() : 0;
        result = 31 * result + carOrderStatusName.hashCode();
        return result;
    }
}
