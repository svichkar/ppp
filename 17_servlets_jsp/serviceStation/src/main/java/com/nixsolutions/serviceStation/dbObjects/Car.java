package com.nixsolutions.serviceStation.dbObjects;

public class Car {
	private Integer car_id;
	private String car_model;
	private String vin_number;
	private String car_description;
	private Integer customer_id;

	public Car(Integer car_id, String car_model, String vin_number, String car_description, Integer customer_id) {
		this.car_id = car_id;
		this.car_model = car_model;
		this.vin_number = vin_number;
		this.car_description = car_description;
		this.customer_id = customer_id;
	}

	public Car(String car_model, String vin_number, String car_description, Integer customer_id) {
		this.car_id = car_id;
		this.car_model = car_model;
		this.vin_number = vin_number;
		this.car_description = car_description;
		this.customer_id = customer_id;
	}

	public Integer getCar_id() {
		return car_id;
	}

	public void setCar_id(Integer car_id) {
		this.car_id = car_id;
	}

	public String getModel() {
		return car_model;
	}

	public void setModel(String model) {
		this.car_model = model;
	}

	public String getVin_number() {
		return vin_number;
	}

	public void setVin_number(String vin_number) {
		this.vin_number = vin_number;
	}

	public String getDescription() {
		return car_description;
	}

	public void setDescription(String description) {
		this.car_description = description;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	@Override
	public String toString() {
		return "Car [car_id=" + car_id + ",  car_model=" + car_model + ", vin_number=" + vin_number
				+ ",  car_description=" + car_description + ", customer_id=" + customer_id + "]";
	}

}
