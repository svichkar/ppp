package com.nixsolutions.entities;

public class CarCustomer extends BaseEntity {

	private int car_id;
	private String model;
	private String vin;
	private String description;
	private int customer_id;
	private String f_name;
	private String l_name;

	public CarCustomer(String model, String vin, String description, int customer_id, String f_name, String l_name) {
		this.model = model;
		this.vin = vin;
		this.description = description;
		this.customer_id = customer_id;
		this.f_name = f_name;
		this.l_name = l_name;
	}

	public CarCustomer() {

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

	public String getF_name() {
		return f_name;
	}

	public String getL_name() {
		return l_name;
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

	public void setF_name(String value) {
		this.f_name = value;
	}

	public void setL_name(String value) {
		this.l_name = value;
	}

}
