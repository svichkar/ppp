package com.nixsolutions.studentgrade.entity;

public class User {
	private int userId;
	private String login;
	private String password;
	private String email;
	private int roleId;

	public User(int userId, String login, String password, String email, int roleId) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.email = email;
		this.roleId = roleId;
	}

	public User() {
		this(0, "default", "default", "default", 0);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
