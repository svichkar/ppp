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

    private Long carOrderId;
    private Long carId;
    private Short carOrderStatusId;
    private Date startDate;
    private Date endDate;

    public CarOrder() {
    }

    public CarOrder(Long carOrderId, Long carId, Short carOrderStatusId, Date startDate, Date endDate) {
        this.carOrderId = carOrderId;
        this.carId = carId;
        this.carOrderStatusId = carOrderStatusId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(Long carOrderId) {
        this.carOrderId = carOrderId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Short getCarOrderStatusId() {
        return carOrderStatusId;
    }

    public void setCarOrderStatusId(Short carOrderStatusId) {
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

    public void setEndDate(Date enddate) {
        this.endDate = enddate;
    }

}
