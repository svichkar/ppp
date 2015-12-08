package com.nixsolutions.bean;

import java.sql.Timestamp;

public class OrderBean {
	private long orderId;
	private String orderStatus;
	private String description;
	private String carVin;
	private String carModel;
	private String carDescription;
	private Timestamp timestampStart;
	private Timestamp timestampFinish;

	public long getOrderId() {
		return orderId;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getCarVin() {
		return carVin;
	}
	
	public String getCarModel() {
		return carModel;
	}
	
	public String getCarDescription() {
		return carDescription;
	}
	
	public Timestamp getTimestampStart() {
		return timestampStart;
	}
	
	public Timestamp getTimestampFinish() {
		return timestampFinish;
	}
	
	public void setOrderId(long value) {
		orderId = value;
	}
	
	public void setOrderStatus(String value) {
		orderStatus = value;
	}
	
	public void setDescription(String value) {
		description = value;
	}
	
	public void setCarVin(String value) {
		carVin = value;
	}
	
	public void setCarModel(String value) {
		carModel = value;
	}
	
	public void setCarDescription(String value) {
		carDescription = value;
	}
	
	public void setTimestampStart(Timestamp value) {
		timestampStart = value;
	}
	
	public void setTimestampFinish(Timestamp value) {
		timestampFinish = value;
	}
}
