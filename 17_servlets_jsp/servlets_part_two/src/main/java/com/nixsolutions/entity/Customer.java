package com.nixsolutions.entity;

public class Customer {
	
	private int customer_id;
	private String first_name;
	private String last_name;
	private String phone;
	private int user_id;
	
	public int getId() {
		return customer_id;
	}
	
	public String getValuesCommaSeparated() {
		return "'" + first_name + "', '" + last_name + "', '" + phone + "'";
	}
	
	public String getFirstName() {
		return first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setId(int value) {
		customer_id = value;
	}
	
	public void setFirstName(String value) {
		first_name = value;
	}
	
	public void setLastName(String value) {
		last_name = value;
	}
	
	public void setPhone(String value) {
		phone = value;
	}
	
	@Override
	public String toString() {
		String res = "first_name = '" + first_name + "', last_name = '" + 
	last_name + "', phone = '" + phone + "'";
		return res;
	}
	
	public Customer(String first_name, String last_name, String phone, int user_id) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.user_id = user_id;
	}
	
	public Customer() {
		this("", "", "null", 0);
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
}
