/**
 * 
 */
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author mixeyes
 *
 */
@Entity
@Table(name = "car")
public class Car implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer car_id;

	@Column(name = "car_model", length = 100, nullable = false)
	private String car_model;

	@Column(name = "vin_number", length = 17, nullable = false, unique = true)
	private String vin_number;

	@Column(name = "reg_number", length = 17, nullable = false, unique = true)
	private String reg_number;

	@Column(name = "car_description", length = 200)
	private String car_description;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;

	public Integer getCar_id() {
		return car_id;
	}

	public void setCar_id(Integer car_id) {
		this.car_id = car_id;
	}

	public String getCar_model() {
		return car_model;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public String getVin_number() {
		return vin_number;
	}

	public void setVin_number(String vin_number) {
		this.vin_number = vin_number;
	}

	public String getReg_number() {
		return reg_number;
	}

	public void setReg_number(String reg_number) {
		this.reg_number = reg_number;
	}

	public String getCar_description() {
		return car_description;
	}

	public void setCar_description(String car_description) {
		this.car_description = car_description;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
