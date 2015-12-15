package com.nixsolutions.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarBean {
	private int carId;
	private String carModel;
	private String carVin;
	private String carDescription;
	private String customerName;
	
	public int getCarId() {
		return carId;
	}
	
	public String getCarModel() {
		return carModel;
	}
	
	public String getCarVin() {
		return carVin;
	}
	
	public String getCarDescription() {
		return carDescription;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCarId(int value) {
		carId = value;
	}
	
	public void setCarModel(String value) {
		carModel = value;
	}
	
	public void setCarVin(String value) {
		carVin = value;
	}
	
	public void setCarDescription(String value) {
		carDescription = value;
	}
	
	public void setCustomerName(String value) {
		customerName = value;
	}
}
