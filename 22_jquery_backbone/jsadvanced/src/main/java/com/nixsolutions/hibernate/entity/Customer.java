package com.nixsolutions.hibernate.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Integer customerId;
	@Column(name = "first_name", length = 25, nullable = false)
	private String firstName;
	@Column(name = "last_name", length = 25, nullable = false)
	private String lastName;
	@Column(name = "phone", length = 25, nullable = true)
	private String phone;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer(String firstName, String lastName, String phone, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.user = user;
	}
	
	public Customer() {
		this("", "", "", null);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (customerId == null ? 0 : customerId.hashCode());
		result = prime * result + firstName.hashCode();
		result = prime * result + lastName.hashCode();
		result = prime * result + phone.hashCode();
		result = prime * result + (user == null ? 0 : user.hashCode());
		return result;
	}

	public boolean equals(Customer other) {
		return ((this.customerId == null && other.customerId == null) 
				|| customerId.equals(other.customerId)) 
				&& this.firstName.equals(other.firstName) 
				&& this.lastName.equals(other.lastName) 
				&& this.phone.equals(other.phone) 
				&& ((this.user == null 
				&& other.user == null) 
				|| this.user.equals(other.user));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		else 
			return this.equals((Customer) obj);
	}
}
