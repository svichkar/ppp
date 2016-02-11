/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.bean;

import java.util.Date;
import nix.jdbcworkshop.entities.CarOrderStatus;

/**
 *
 * @author mednorcom
 */
public class CarOrderBean {

    private Long carOrderId;
    private CarBean carBean;
    private CarOrderStatus carOrderStatus;
    private Date startDate;
    private Date endDate;

    public CarOrderBean() {
    }

    public Long getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(Long carOrderId) {
        this.carOrderId = carOrderId;
    }

    public CarBean getCarBean() {
        return carBean;
    }

    public void setCarBean(CarBean carBean) {
        this.carBean = carBean;
    }

    public CarOrderStatus getCarOrderStatus() {
        return carOrderStatus;
    }

    public void setCarOrderStatus(CarOrderStatus carOrderStatus) {
        this.carOrderStatus = carOrderStatus;
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
