/**
 * 
 */
package com.nixsolutions.bean;

/**
 * @author mixeyes
 *
 */
public class CarFullInfo {
	private String car_ID;
	private String carModel;
	private String regNumber;
	private String vinNumber;
	private String carOwner;
	private String ownerPhone;
	private String car_description;

	public String getCar_description() {
		return car_description;
	}

	public void setCar_description(String car_description) {
		this.car_description = car_description;
	}

	public String getCar_ID() {
		return car_ID;
	}

	public void setCar_ID(String car_ID) {
		this.car_ID = car_ID;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getVinNumber() {
		return vinNumber;
	}

	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

}
