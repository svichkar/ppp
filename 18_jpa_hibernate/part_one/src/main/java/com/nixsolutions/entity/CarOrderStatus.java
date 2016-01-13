package com.nixsolutions.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @Transient
    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_order_status_id", referencedColumnName = "car_order_status_id")
    private List<CarOrder> carOrderList;

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

    public List<CarOrder> getCarOrderList() {
        return carOrderList;
    }

    public void setCarOrderList(List<CarOrder> carOrderList) {
        this.carOrderList = carOrderList;
    }
}
