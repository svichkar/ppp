package com.nixsolutions.servicestation.entity;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class CarOrderStatus {
    private Integer carOrderStatusId;
    private String name;

    public CarOrderStatus(String name) {
        this.name = name;
    }

    public Integer getCarOrderStatusId() {
        return carOrderStatusId;
    }

    public void setCarOrderStatusId(Integer carOrderStatusId) {
        this.carOrderStatusId = carOrderStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
