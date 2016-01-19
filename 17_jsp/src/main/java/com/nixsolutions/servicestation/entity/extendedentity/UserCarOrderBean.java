package com.nixsolutions.servicestation.entity.extendedentity;

import java.util.Date;

/**
 * Created by rybkinrolla on 12.01.2016.
 */
public class UserCarOrderBean {
    private Integer carOrderId;
    private Integer carId;
    private Integer carOrderStatusId;
    private String carOrderStatus;
    private String serialId;
    private String carModel;
    private String userLogin;
    private Date startDate;
    private Date endDate;

    public Integer getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(Integer carOrderId) {
        this.carOrderId = carOrderId;
    }

    public String getCarOrderStatus() {
        return carOrderStatus;
    }

    public void setCarOrderStatus(String carOrderStatus) {
        this.carOrderStatus = carOrderStatus;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

    public Integer getCarOrderStatusId() {
        return carOrderStatusId;
    }

    public void setCarOrderStatusId(Integer carOrderStatusId) {
        this.carOrderStatusId = carOrderStatusId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
