/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.entities;

import java.util.Date;

/**
 *
 * @author mednorcom
 */
public class CarOrder {

    private long carOrderId;
    private long carId;
    private int carOrderStatusId;
    private Date startDate;
    private Date enddate;

    public long getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(long carOrderId) {
        this.carOrderId = carOrderId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public int getCarOrderStatusId() {
        return carOrderStatusId;
    }

    public void setCarOrderStatusId(int carOrderStatusId) {
        this.carOrderStatusId = carOrderStatusId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

}
