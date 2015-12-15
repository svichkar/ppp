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
public class Car implements Serializable {

	public Car() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Integer carId;

	@Column(name = "car_model", length = 100, nullable = false)
	private String carModel;

	@Column(name = "vin_number", length = 17, nullable = false, unique = true)
	private String vinNumber;

	@Column(name = "reg_number", length = 17, nullable = false, unique = true)
	private String regNumber;

	@Column(name = "car_description", length = 200)
	private String carDescription;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;

	public Integer getCar_id() {
		return carId;
	}

	public void setCar_id(Integer car_id) {
		this.carId = car_id;
	}

	public String getCar_model() {
		return carModel;
	}

	public void setCar_model(String car_model) {
		this.carModel = car_model;
	}

	public String getVin_number() {
		return vinNumber;
	}

	public void setVin_number(String vin_number) {
		this.vinNumber = vin_number;
	}

	public String getReg_number() {
		return regNumber;
	}

	public void setReg_number(String reg_number) {
		this.regNumber = reg_number;
	}

	public String getCar_description() {
		return carDescription;
	}

	public void setCar_description(String car_description) {
		this.carDescription = car_description;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carModel == null) ? 0 : carModel.hashCode());
		result = prime * result + ((carDescription == null) ? 0 : carDescription.hashCode());
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((regNumber == null) ? 0 : regNumber.hashCode());
		result = prime * result + ((vinNumber == null) ? 0 : vinNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carModel == null) {
			if (other.carModel != null)
				return false;
		} else if (!carModel.equals(other.carModel))
			return false;
		if (carDescription == null) {
			if (other.carDescription != null)
				return false;
		} else if (!carDescription.equals(other.carDescription))
			return false;
		if (carId == null) {
			if (other.carId != null)
				return false;
		} else if (!carId.equals(other.carId))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (regNumber == null) {
			if (other.regNumber != null)
				return false;
		} else if (!regNumber.equals(other.regNumber))
			return false;
		if (vinNumber == null) {
			if (other.vinNumber != null)
				return false;
		} else if (!vinNumber.equals(other.vinNumber))
			return false;
		return true;
	}

}
