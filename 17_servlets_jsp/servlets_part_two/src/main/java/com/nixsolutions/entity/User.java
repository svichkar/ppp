package com.nixsolutions.entity;

public class User {

	private int user_id;
	private String user_login;
	private String user_password;
	private int role_id;

	public int getId() {
		return user_id;
	}
	
	public String getUserLogin() {
		return user_login;
	}
	
	public String getUserPassword() {
		return user_password;
	}
	
	public int getRoleId() {
		return role_id;
	}
	
	public void setId(int value) {
		user_id = value;
	}
	
	public void setUserLogin(String value) {
		user_login = value;
	}
	
	public void setUserPassword(String value) {
		user_password = value;
	}
	
	public void setRoleId(int value) {
		role_id = value;
	}

	public String getValuesCommaSeparated() {
		return "'" + user_login + "', '" + user_password + "', " + (role_id == 0 ? "null" : role_id);
	}

	@Override
	public String toString() {
		String res = "user_login = '" + user_login + "', user_password = '" + user_password + "', role_id = " +
				(role_id == 0 ? "null" : role_id);
		return res;
	}
	
	public User(String user_login, String user_password, int role_id) {
		this.user_login = user_login;
		this.user_password = user_password;
		this.role_id = role_id;
	}
	
	public User() {
		this("", "", 0);
	}

}
