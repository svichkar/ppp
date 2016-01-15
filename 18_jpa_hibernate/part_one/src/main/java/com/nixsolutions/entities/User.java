package com.nixsolutions.entities;

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
import javax.persistence.OneToOne;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	@Column(name = "username", nullable = false, length = 255)
	private String username;
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Role role;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;

	public User() {
	}

	public int getUserId() {
		return user_id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setUserId(int value) {
		this.user_id = value;
	}

	public void setUsername(String value) {
		this.username = value;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public void setRole(Role value) {
		this.role = value;
	}

	public void setCustomer(Customer value) {
		this.customer = value;
	}

}
