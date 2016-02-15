package com.nixsolutions.dto;

public class CarCustomer {

	private long carId;
	private String model;
	private String vin;
	private String description;
	private long customerId;
	private String fName;
	private String lName;

	public CarCustomer(String model, String vin, String description, long customerId, String fName, String lName) {
		this.model = model;
		this.vin = vin;
		this.description = description;
		this.customerId = customerId;
		this.fName = fName;
		this.lName = lName;
	}

	public CarCustomer() {

	}

	public long getId() {
		return carId;
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

	public long getCustomerId() {
		return customerId;
	}

	public String getFname() {
		return fName;
	}

	public String getLname() {
		return lName;
	}

	public void setId(long value) {
		this.carId = value;
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

	public void setCustomerId(long value) {
		this.customerId = value;
	}

	public void setFname(String value) {
		this.fName = value;
	}

	public void setLname(String value) {
		this.lName = value;
	}

}
