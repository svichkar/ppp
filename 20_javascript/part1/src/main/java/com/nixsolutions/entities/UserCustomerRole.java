package com.nixsolutions.entities;

public class UserCustomerRole {

	private long user_id;
	private String username;
	private String password;
	private String f_name;
	private String l_name;
	private String role;
	private long customer_id;
	private long role_id;

	public UserCustomerRole() {

	}

	public String getUsername() {
		return username;
	}

	public long getUserId() {
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

	public long getRoleId() {
		return role_id;
	}

	public long getCustomerId() {
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

	public void setUser_id(long value) {
		this.user_id = value;
	}

	public void setRoleId(long value) {
		this.role_id = value;
	}

	public void setCustomerId(long value) {
		this.customer_id = value;
	}
}
