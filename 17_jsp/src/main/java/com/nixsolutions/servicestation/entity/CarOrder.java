package com.nixsolutions.servicestation.entity;

import java.util.Date;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public class CarOrder {
    private Integer carOrderId;
    private Integer carId;
    private Integer carOrderStatusId;
    private Date startDate;
    private Date endDate;

    public CarOrder() {
    }

    public CarOrder(Integer carId, Integer carOrderStatusId, Date startDate) {
        this.carId = carId;
        this.carOrderStatusId = carOrderStatusId;
        this.startDate = startDate;
    }

    public Integer getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(Integer carOrderId) {
        this.carOrderId = carOrderId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCarOrderStatusId() {
        return carOrderStatusId;
    }

    public void setCarOrderStatusId(Integer carOrderStatusId) {
        this.carOrderStatusId = carOrderStatusId;
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
}
