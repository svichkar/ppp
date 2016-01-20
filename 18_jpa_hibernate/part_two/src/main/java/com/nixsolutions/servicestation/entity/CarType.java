package com.nixsolutions.servicestation.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rybkinrolla on 13.01.2016.
 */
@Entity
@Table(name="car_type")
public class CarType implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_type_id")
    private Long carTypeId;
    @Column(name = "brand", length = 100, nullable = false)
    private String brand;
    @Column(name = "model_name", length = 100, nullable = false)
    private String modelName;

    @Transient
    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_type_id", referencedColumnName = "car_type_id")
    private List<Car> carList;

    public CarType() {
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Long getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
