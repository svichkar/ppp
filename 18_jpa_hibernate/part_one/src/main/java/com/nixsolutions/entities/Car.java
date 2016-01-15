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

@Entity
public class Car implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int car_id;
	@Column(name = "model", nullable = false, length = 255)
	private String model;
	@Column(name = "vin", nullable = false, length = 255)
	private String vin;
	@Column(name = "description", length = 255)
	private String description;
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id", referencedColumnName="customer_id")
	private Customer customer;

	public Car() {

	}

	public int getCarId() {
		return car_id;
	}

	public String getModel() {
		return model;
	}

	public String getVin() {
		return vin;
	}

	public String getDescription() {
		return description;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCarId(int value) {
		car_id = value;
	}

	public void setModel(String value) {
		model = value;
	}

	public void setVin(String value) {
		vin = value;
	}

	public void setDescription(String value) {
		description = value;
	}

	public void setCustomer(Customer value) {
		customer = value;
	}

}
