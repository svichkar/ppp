package com.nixsolutions.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int customer_id;
	@Column(name = "f_name", nullable = false, length = 255)
	private String f_name;
	@Column(name = "l_name", nullable = false, length = 255)
	private String l_name;
	@Column(name = "phone", nullable = false, length = 255)
	private String phone;

	public Customer() {

	}

	public int getCustomerId() {
		// TODO Auto-generated method stub
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

	public void setCustomerId(int value) {
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

}
