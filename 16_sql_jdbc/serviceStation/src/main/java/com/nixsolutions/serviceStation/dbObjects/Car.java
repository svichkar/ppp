package com.nixsolutions.serviceStation.dbObjects;

public class Car {
	private Integer car_id;
	private String model;
	private String vin_number;
	private String description;
	private Integer customer_id;
	
	public Car(Integer car_id, String model, String vin_number, String description,Integer customer_id) {
		this.car_id = car_id;
		this.model = model;
		this.vin_number = vin_number;
		this.description = description;
		this.customer_id=customer_id;
	}

	public Integer getCar_id() {
		return car_id;
	}
	public void setCar_id(Integer car_id) {
		this.car_id = car_id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVin_number() {
		return vin_number;
	}
	public void setVin_number(String vin_number) {
		this.vin_number = vin_number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	@Override
	public String toString() {
		return "Car [car_id=" + car_id + ", model=" + model + ", vin_number=" + vin_number + ", description="
				+ description + ", customer_id=" + customer_id + "]";
	}

}
