/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.entities;

/**
 *
 * @author mednorcom
 */
public class CarType {

    private Long carTypeId;
    private String brand;
    private String model;

    public CarType() {
    }

    
    public CarType(Long carTypeId, String brand, String model) {
        this.carTypeId = carTypeId;
        this.brand = brand;
        this.model = model;
    }

    public long getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
