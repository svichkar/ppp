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
@Table(name = "car_type")
public class CarType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_type_id")
    private Long carTypeId;
    @Column(name = "brand", length = 100, nullable = false)
    private String brand;
    @Column(name = "model_name", length = 100, nullable = false)
    private String modelName;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_type_id", referencedColumnName = "car_type_id")
    private Set<Car> carList;

    public CarType() {
    }

    @Transient
    public String getFullName() {
        return brand + " " + modelName;
    }

    public Set<Car> getCarList() {
        return carList;
    }

    public void setCarList(Set<Car> carList) {
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

    @Override
    public boolean equals(Object obj) {
        CarType carType = (CarType) obj;
        if (brand.equals(carType.brand) && modelName.equals(carType.modelName)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 5;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
        return result;
    }
}
