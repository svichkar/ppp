package com.nixsolutions.entities;

public class UserCustomerRole {

	private String username;
	private String password;
	private String f_name;
	private String l_name;
	private String role;

	public UserCustomerRole() {

	}

	public String username() {
		return username;
	}
	
	public String password()
	{
		return password;
	}

	public String f_name() {
		return f_name;
	}

	public String l_name() {
		return l_name;
	}

	public String role() {
		return role;
	}

	public void setUsername(String value) {
		this.username = value;
	}
	
	public void setPassword(String value)
	{
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
}
