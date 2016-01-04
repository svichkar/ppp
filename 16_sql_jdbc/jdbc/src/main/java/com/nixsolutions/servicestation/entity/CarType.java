package com.nixsolutions.servicestation.entity;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class CarType {
    private Integer carTypeId;
    private String brand;
    private String modelName;

    public CarType(){
    }

    public CarType(String brand, String modelName) {
        this.brand = brand;
        this.modelName = modelName;
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
