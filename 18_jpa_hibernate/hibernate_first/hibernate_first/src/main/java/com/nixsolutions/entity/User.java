package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int userId;
    @Column(name = "email", length = 256, nullable = false)
    private String email;
    @Column(name = "password", length = 256, nullable = false)
    private String password;
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
