package com.nixsolutions.entities;

public class UserCustomerRole {

	private int user_id;
	private String username;
	private String password;
	private String f_name;
	private String l_name;
	private String role;
	private int customer_id;
	private int role_id;

	public UserCustomerRole() {

	}

	public String getUsername() {
		return username;
	}

	public int getUserId() {
		return user_id;
	}

	public String getPassword() {
		return password;
	}

	public String getF_name() {
		return f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public String getRole() {
		return role;
	}

	public int getRoleId() {
		return role_id;
	}

	public int getCustomerId() {
		return customer_id;
	}

	public void setUsername(String value) {
		this.username = value;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public void setF_name(String value) {
		this.f_name = value;
	}

	public void setL_name(String value) {
		this.l_name = value;
	}

	public void setRole(String value) {
		this.role = value;
	}

	public void setUser_id(int value) {
		this.user_id = value;
	}

	public void setRoleId(int value) {
		this.role_id = value;
	}

	public void setCustomerId(int value) {
		this.customer_id = value;
	}
}
