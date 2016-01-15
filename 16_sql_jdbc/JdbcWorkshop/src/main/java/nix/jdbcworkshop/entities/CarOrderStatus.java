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

    private short carOrderStatusId;
    private String name;

    public short getCarOrderStatusId() {
        return carOrderStatusId;
    }

    public void setCarOrderStatusId(short carOrderStatusId) {
        this.carOrderStatusId = carOrderStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
