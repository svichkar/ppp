/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.bean;

import java.io.Serializable;
import nix.jdbcworkshop.entities.CarType;
import nix.jdbcworkshop.entities.Client;

/**
 *
 * @author mednorcom
 */
public class CarBean implements Serializable {

    private Long carId;
    private String serialId;
    private CarType carType;
    private ClientBean clientBean;

    public CarBean() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public ClientBean getClientBean() {
        return clientBean;
    }

    public void setClientBean(ClientBean clientBean) {
        this.clientBean = clientBean;
    }



    
}
