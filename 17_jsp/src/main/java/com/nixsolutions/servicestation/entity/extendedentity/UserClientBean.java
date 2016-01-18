package com.nixsolutions.servicestation.entity.extendedentity;

/**
 * Created by rybkinrolla on 17.01.2016.
 */
public class UserClientBean {
    private Integer clientId;
    private String clientFName;
    private String clientLName;
    private Integer userId;
    private String login;
    private String password;
    private String role;
    private String roleId;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientFName() {
        return clientFName;
    }

    public void setClientFName(String clientFName) {
        this.clientFName = clientFName;
    }

    public String getClientLName() {
        return clientLName;
    }

    public void setClientLName(String clientLName) {
        this.clientLName = clientLName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
