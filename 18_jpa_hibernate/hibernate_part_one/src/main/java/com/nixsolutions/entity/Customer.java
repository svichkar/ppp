package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customer_id;
	@Column(name = "first_name", length = 25, nullable = false)
	private String first_name;
	@Column(name = "last_name", length = 25, nullable = false)
	private String last_name;
	@Column(name = "phone", length = 25, nullable = true)
	private String phone;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	public void setCustomerId(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getCustomerId() {
		return customer_id;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getLastName() {
		return last_name;
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
}
