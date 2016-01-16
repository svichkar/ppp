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

    @Override
    public boolean equals(Object obj) {
        CarType carType = (CarType) obj;
        if(brand.equals(carType.brand) && modelName.equals(carType.modelName)){
            return true;
        } else {
            return false;
        }
    }
}
