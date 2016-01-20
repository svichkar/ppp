package com.nixsolutions.studentgrade.entity;

public class User {
	private Long userId;
	private String login;
	private String password;
	private String email;
	private Long roleId;

	public User(long userId, String login, String password, String email, long roleId) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.email = email;
		this.roleId = roleId;
	}

	public User() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
