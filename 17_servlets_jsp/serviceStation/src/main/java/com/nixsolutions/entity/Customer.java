/**
 * 
 */
package com.nixsolutions.entity;

/**
 * @author Михаил
 *
 */
/**
 * @author mixeyes
 *
 */
public class Customer {
	private Integer customerId;
	private String firstName;
	private String lastName;
	private String phone;
	private Integer userId;

	public Customer(Integer customerId, String firstName, String lastName, String phone, Integer userId) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.userId = userId;
	}

	public Customer(String firstName, String lastName, String phone, Integer userId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.userId = userId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Customer [first_name=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", userId="
				+ userId + "]";
	}

}
