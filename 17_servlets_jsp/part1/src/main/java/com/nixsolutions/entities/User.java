package com.nixsolutions.entities;

public class User extends BaseEntity {

	private int user_id;
	private String username;
	private String password;
	private int role_id;
	private int customer_id;

	public User() {

	}

	public User(String username, int role_id, int customer_id) {
		this.username = username;
		this.role_id = role_id;
		this.customer_id = customer_id;
	}

	@Override
	public int getId() {
		return user_id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getRole_id() {
		return role_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setId(int value) {
		this.user_id = value;
	}

	public void setUsername(String value) {
		this.username = value;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public void setRole_id(int value) {
		this.role_id = value;
	}
	
	public void setCustomer_id(int value)
	{
		this.customer_id = value;
	}

}
