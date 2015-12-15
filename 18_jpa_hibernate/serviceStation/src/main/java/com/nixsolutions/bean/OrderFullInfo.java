/**
 * 
 */
package com.nixsolutions.bean;

/**
 * @author mixeyes
 *
 */
public class OrderFullInfo {
	private String customerFullName;
	private String carRegNumber;
	private String carModel;
	private String carDescription;
	private String orderStatus;
	private String oredrInfo;
	private String orderStartTime;
	private String orderFinishTime;
	private String orderId;

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarDescription() {
		return carDescription;
	}

	public void setCarDescription(String carDescription) {
		this.carDescription = carDescription;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	public String getCarRegNumber() {
		return carRegNumber;
	}

	public void setCarRegNumber(String carRegNumber) {
		this.carRegNumber = carRegNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOredrInfo() {
		return oredrInfo;
	}

	public void setOredrInfo(String oredrInfo) {
		this.oredrInfo = oredrInfo;
	}

	public String getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public String getOrderFinishTime() {
		return orderFinishTime;
	}

	public void setOrderFinishTime(String orderFinishTime) {
		this.orderFinishTime = orderFinishTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
