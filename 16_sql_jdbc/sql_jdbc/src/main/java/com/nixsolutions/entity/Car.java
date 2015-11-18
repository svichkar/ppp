package com.nixsolutions.entity;

public class Car {
	
	private int car_id;
	private String model;
	private String vin;
	private String description;
	private int customer_id;

	public int getId() {
		return car_id;
	}

	public String getValuesCommaSeparated() {
		return "'" + model + "', '" + vin + "', '" + description + "', " + (customer_id == 0 ? "null" : customer_id);
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
	
	public int getCustomerId() {
		return customer_id;
	}
	
	public void setId(int value) {
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
	
	public void setCustomerId(int value) {
		customer_id = value;
	}
	
	@Override
	public String toString() {
		String res = "model = '" + model + "', vin = '" + vin + 
				"', description = '" + description + "', customer_id = " + 
				(customer_id == 0 ? "null" :  customer_id);
		return res;
	}
	
	public Car(String model, String vin, String description, int customerId) {
		this.model = model;
		this.vin = vin;
		this.description = description;
		this.customer_id = customerId;
	}
	
	public Car() {
		this("", "", "null", 0);
	}
}
