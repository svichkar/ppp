package com.nixsolutions.servicestation.entity.extendedentity;

/**
 * Created by rybkinrolla on 15.01.2016.
 */
public class CarBean {
    private Integer carId;
    private String login;
    private Integer clientId;
    private String clientFSName;
    private String carBrand;
    private String carModel;
    private String carVIN;

    public String getCarVIN() {
        return carVIN;
    }

    public void setCarVIN(String carVIN) {
        this.carVIN = carVIN;
    }

    public String getClientFSName() {
        return clientFSName;
    }

    public void setClientFSName(String clientFSName) {
        this.clientFSName = clientFSName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
