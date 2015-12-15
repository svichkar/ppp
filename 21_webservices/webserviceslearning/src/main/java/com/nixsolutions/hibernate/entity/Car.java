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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Integer carId;
	@Column(name = "model", length = 100, nullable = false)
	private String model;
	@Column(name = "vin", length = 17, nullable = false)
	private String vin;
	@Column(name = "description", length = 200, nullable = true)
	private String description;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getVin() {
		return vin;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Car(String model, String vin, String description, Customer customer) {
		this.model = model;
		this.vin = vin;
		this.description = description;
		this.customer = customer;
	}

	public Car() {
		this("", "", "", null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (carId == null ? 0 : carId.hashCode());
		result = prime * result + model.hashCode();
		result = prime * result + vin.hashCode();
		result = prime * result + description.hashCode();
		result = prime * result + (customer == null ? 0 : customer.hashCode());
		return result;
	}

	public boolean equals(Car other) {
		return ((this.carId == null && other.carId == null) 
				|| carId.equals(other.carId)) 
				&& this.model.equals(other.model) 
				&& this.vin.equals(other.vin) 
				&& this.description.equals(other.description) 
				&& ((this.customer == null 
				&& other.customer == null) 
				|| this.customer.equals(other.customer));
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
			return this.equals((Car) obj);
	}
}
