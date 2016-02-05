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
	private long customer_id;
	@Column(name = "f_name", nullable = false, length = 255)
	private String f_name;
	@Column(name = "l_name", nullable = false, length = 255)
	private String l_name;
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

	public Customer(String f_name, String l_name, String phone, User user) {
		this.f_name = f_name;
		this.l_name = l_name;
		this.phone = phone;
		this.user = user;
	}

	public long getCustomerId() {
		return customer_id;
	}

	public String getF_name() {
		return f_name;
	}

	public String getL_name() {
		return l_name;
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
		customer_id = value;
	}

	public void setF_name(String value) {
		f_name = value;
	}

	public void setL_name(String value) {
		l_name = value;
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
