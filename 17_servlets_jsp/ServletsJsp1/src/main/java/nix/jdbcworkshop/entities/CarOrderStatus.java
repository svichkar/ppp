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
public class CarOrderStatus {

    private Short carOrderStatusId;
    private String name;

    public CarOrderStatus() {
    }

    public CarOrderStatus(Short carOrderStatusId, String name) {
        this.carOrderStatusId = carOrderStatusId;
        this.name = name;
    }
    
    

    public Short getCarOrderStatusId() {
        return carOrderStatusId;
    }

    public void setCarOrderStatusId(Short carOrderStatusId) {
        this.carOrderStatusId = carOrderStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
