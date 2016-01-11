package com.nixsolutions.servicestation.entity;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
public class User {
    private Integer userId;
    private String login;
    private String password;
    private Integer roleId;

    public User() {
    }

    public User(Integer userId, String login, String password, Integer roleId) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
