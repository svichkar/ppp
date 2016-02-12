package com.nixsolutions.dto;

public class UserCustomerRole {

	private long userId;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private String role;
	private long customerId;
	private long roleId;

	public UserCustomerRole() {

	}

	public String getUsername() {
		return username;
	}

	public long getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getFname() {
		return fName;
	}

	public String getLname() {
		return lName;
	}

	public String getRole() {
		return role;
	}

	public long getRoleId() {
		return roleId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setUsername(String value) {
		this.username = value;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public void setFname(String value) {
		this.fName = value;
	}

	public void setLname(String value) {
		this.lName = value;
	}

	public void setRole(String value) {
		this.role = value;
	}

	public void setUserId(long value) {
		this.userId = value;
	}

	public void setRoleId(long value) {
		this.roleId = value;
	}

	public void setCustomerId(long value) {
		this.customerId = value;
	}
}
