/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.bean;

import java.io.Serializable;

/**
 *
 * @author mednorcom
 */
public class AssignmentBean implements Serializable {

    private CarOrderBean carOrderBean;
    private EmployeeBean employeeBean;
    
    public AssignmentBean() {
    }

    public CarOrderBean getCarOrderBean() {
        return carOrderBean;
    }

    public void setCarOrderBean(CarOrderBean carOrderBean) {
        this.carOrderBean = carOrderBean;
    }

    public EmployeeBean getEmployeeBean() {
        return employeeBean;
    }

    public void setEmployeeBean(EmployeeBean employeeBean) {
        this.employeeBean = employeeBean;
    }    

}
