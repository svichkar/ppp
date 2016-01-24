package com.nixsolutions.entities;

public class Car extends BaseEntity {

	private int car_id;
	private String model;
	private String vin;
	private String description;
	private int customer_id;

	public Car(String model, String vin, String description, int customer_id) {
		this.model = model;
		this.vin = vin;
		this.description = description;
		this.customer_id = customer_id;
	}

	public Car() {

	}

	@Override
	public int getId() {
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

	public int getCustomer_id() {
		return customer_id;
	}

	public void setId(int value) {
		this.car_id = value;
	}

	public void setModel(String value) {
		this.model = value;
	}

	public void setVin(String value) {
		this.vin = value;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public void setCustomer_id(int value) {
		this.customer_id = value;
	}

}
