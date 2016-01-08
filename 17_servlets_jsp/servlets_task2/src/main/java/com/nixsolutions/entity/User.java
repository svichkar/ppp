package com.nixsolutions.entity;

public class User {
	private Integer userId;
	private String userName;
	private String userPassword;
	private Integer roleId;

	public User(String userName, String userPassword, Integer roleId) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.roleId = roleId;
	}

	public User(String userName, String userPassword, Integer roleId, Integer userId) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.roleId = roleId;
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String toString() {
		return "[User with userId: " + this.userId + "; name: " + this.userName + "; password: " + this.userPassword
				+ "; roleId: " + this.roleId + "]";

	}
}
