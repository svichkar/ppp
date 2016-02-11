/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.bean;

import nix.jdbcworkshop.entities.EmployeeCategory;
import nix.jdbcworkshop.entities.WebUser;



/**
 *
 * @author mednorcom
 */
public class EmployeeBean {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private EmployeeCategory employeeCategory;
    private WebUserBean webUserBean;


    public EmployeeBean() {
    }
    
    
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeCategory getEmployeeCategory() {
        return employeeCategory;
    }

    public void setEmployeeCategory(EmployeeCategory employeeCategory) {
        this.employeeCategory = employeeCategory;
    }

    public WebUserBean getWebUserBean() {
        return webUserBean;
    }

    public void setWebUserBean(WebUserBean webUserBean) {
        this.webUserBean = webUserBean;
    }





}
