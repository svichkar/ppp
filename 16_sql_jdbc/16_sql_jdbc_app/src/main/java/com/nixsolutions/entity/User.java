package com.nixsolutions.entity;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int userId;
    private String email;
    private String password;
    private int roleId;
    private static Map<String, String> mapColNames;

    public User() {
    }

    public User(int userId, String email, String password, int roleId) {
	this.userId = userId;
	this.email = email;
	this.password = password;
	this.roleId = roleId;
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_userId", "user_id");
	    mapColNames.put("email", "email");
	    mapColNames.put("password", "password");
	    mapColNames.put("roleId", "role_id");
	}
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

    public int getRoleId() {
	return roleId;
    }

    public void setRoleId(int roleId) {
	this.roleId = roleId;
    }

    public static Map<String, String> getMap() {
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_userId", "user_id");
	    mapColNames.put("email", "email");
	    mapColNames.put("password", "password");
	    mapColNames.put("roleId", "role_id");
	}
	return mapColNames;
    }
}
