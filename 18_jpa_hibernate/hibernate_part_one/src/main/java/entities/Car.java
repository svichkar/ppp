package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "car_id")
	private int car_id;
	@Column(name = "model", length = 100, nullable = false)
	private String model;
	@Column(name = "vin", length = 17, nullable = false)
	private String vin;
	@Column(name = "description", length = 200, nullable = true)
	private String description;
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Customer customer;
	
	public void setCarId(int car_id) {
		this.car_id = car_id;
	}
	
	public int getCarId() {
		return car_id;
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
}
