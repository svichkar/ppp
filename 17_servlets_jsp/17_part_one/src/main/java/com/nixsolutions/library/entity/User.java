package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/28/2015.
 */
public class User {
    private Integer userId;
    private String login;
    private String password;
    private Integer roleId;

    public User(Integer userId, String login, String password, Integer roleId) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public User(String login, String password, Integer roleId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
