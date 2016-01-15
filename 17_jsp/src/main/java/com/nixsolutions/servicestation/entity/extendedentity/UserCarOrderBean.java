package com.nixsolutions.servicestation.entity.extendedentity;

/**
 * Created by rybkinrolla on 12.01.2016.
 */
public class UserCarOrderBean {
    private Integer carOrderId;
    private String carOrderStatus;
    private String serialId;
    private String carModel;
    private String userLogin;

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
}
