package com.nixsolutions.studentgrade.entity;

/**
 * Created by svichkar on 12/18/2015.
 */
public class User {

    private Long userId;
    private String firstName;
    private String lastName;
    private String login;
    private String userPassword;
    private String email;
    private Long roleId;

    public User() {
    }

    public User(String firstName, String lastName, String login, String userPassword, String email, Long roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.userPassword = userPassword;
        this.email = email;
        this.roleId = roleId;
    }

    public User(Long userId, String firstName, String lastName, String login, String userPassword, String email, Long roleId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.userPassword = userPassword;
        this.email = email;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
