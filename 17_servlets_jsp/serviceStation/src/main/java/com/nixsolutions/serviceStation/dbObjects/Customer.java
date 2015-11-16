/**
 * 
 */
package com.nixsolutions.serviceStation.dbObjects;

/**
 * @author Михаил
 *
 */
public class Customer {
	private Integer	customer_id;
	private String first_name;
	private String last_name;
	private String phone;
	private Integer user_id;
	
	public Customer(Integer customer_id, String first_name, String last_name, String phone,Integer user_id) {
		super();
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.user_id = user_id;
	}

	public Customer(String first_name, String last_name, String phone,Integer user_id) {
		super();
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.user_id = user_id;
}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Customer " + first_name + " " + last_name;
	}
	
}
