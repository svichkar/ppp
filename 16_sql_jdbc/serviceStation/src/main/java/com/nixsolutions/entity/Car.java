package com.nixsolutions.entity;

public class Car {
	private Integer car_id;
	private String car_model;
	private String vin_number;
	private String reg_number;
	private String car_description;
	private Integer customer_id;

	public Car(String car_model, String vin_number, String reg_number, String car_description, Integer customer_id) {
		super();
		this.car_model = car_model;
		this.vin_number = vin_number;
		this.reg_number = reg_number;
		this.car_description = car_description;
		this.customer_id = customer_id;
	}

	public Car(Integer car_id, String car_model, String vin_number, String reg_number, String car_description,
			Integer customer_id) {
		super();
		this.car_id = car_id;
		this.car_model = car_model;
		this.vin_number = vin_number;
		this.reg_number = reg_number;
		this.car_description = car_description;
		this.customer_id = customer_id;
	}

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

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

}
