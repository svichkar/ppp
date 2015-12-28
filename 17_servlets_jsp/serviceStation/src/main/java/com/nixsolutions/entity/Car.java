package com.nixsolutions.entity;

public class Car {
	private Integer carId;
	private String carModel;
	private String vinNumber;
	private String regNumber;
	private String carDescription;
	private Integer customerId;

	public Car(String carModel, String vinNumber, String regNumber, String carDescription, Integer customerId) {
		super();
		this.carModel = carModel;
		this.vinNumber = vinNumber;
		this.regNumber = regNumber;
		this.carDescription = carDescription;
		this.customerId = customerId;
	}

	public Car(Integer carId, String carModel, String vinNumber, String regNumber, String carDescription,
			Integer customerId) {
		super();
		this.carId = carId;
		this.carModel = carModel;
		this.vinNumber = vinNumber;
		this.regNumber = regNumber;
		this.carDescription = carDescription;
		this.customerId = customerId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getVinNumber() {
		return vinNumber;
	}

	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getCarDescription() {
		return carDescription;
	}

	public void setCarDescription(String carDescription) {
		this.carDescription = carDescription;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Car [carModel=" + carModel + ", vinNumber=" + vinNumber + ", regNumber=" + regNumber
				+ ", carDescription=" + carDescription + ", customerId=" + customerId + "]";
	}

}
