package com.nixsolutions.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	@Column(name = "f_name", nullable = false, length = 255)
	private String fName;
	@Column(name = "l_name", nullable = false, length = 255)
	private String lName;
	@Column(name = "phone", nullable = false, length = 255)
	private String phone;
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Set<Car> cars;

	public Customer() {

	}

	public Customer(String fName, String lName, String phone, User user) {
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.user = user;
	}

	public long getCustomerId() {
		return customerId;
	}

	public String getFname() {
		return fName;
	}

	public String getLname() {
		return lName;
	}

	public String getPhone() {
		return phone;
	}

	public User getUser() {
		return user;
	}
	
	public Set<Car> getCars() {
		return cars;
	}

	public void setCustomerId(long value) {
		customerId = value;
	}

	public void setFname(String value) {
		fName = value;
	}

	public void setLname(String value) {
		lName = value;
	}

	public void setPhone(String value) {
		phone = value;
	}

	public void setUser(User value) {
		user = value;
	}
	
	public void setCars(Set<Car> value) {
		cars = value;
	}

}
