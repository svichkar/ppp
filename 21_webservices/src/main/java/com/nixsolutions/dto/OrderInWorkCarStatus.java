package com.nixsolutions.dto;

import java.sql.Timestamp;

public class OrderInWorkCarStatus {

	private long orderId;
	private long orderStatusId;
	private String orderStatusName;
	private String description;
	private long carId;
	private String model;
	private String vin;
	private Timestamp datetimeStart;
	private Timestamp datetimeEnd;

	public OrderInWorkCarStatus(long orderStatusId, String description, long carId, String model, String vin,
			String orderStatusName, Timestamp datetimeStart, Timestamp datetimeEnd) {
		this.orderStatusId = orderStatusId;
		this.description = description;
		this.carId = carId;
		this.model = model;
		this.vin = vin;
		this.orderStatusName = orderStatusName;
		this.datetimeStart = datetimeStart;
		this.datetimeEnd = datetimeEnd;
	}

	public OrderInWorkCarStatus() {

	}

	public long getId() {
		return orderId;
	}

	public long getOrderStatusId() {
		return orderStatusId;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public String getDescription() {
		return description;
	}

	public long getCarId() {
		return carId;
	}

	public String getModel() {
		return model;
	}

	public String getVin() {
		return vin;
	}

	public Timestamp getDatetimeStart() {
		return datetimeStart;
	}

	public Timestamp getDatetimeEnd() {
		return datetimeEnd;
	}

	public void setId(long value) {
		orderId = value;
	}

	public void setOrderStatusId(long value) {
		orderStatusId = value;
	}

	public void setOrderStatusName(String value) {
		orderStatusName = value;
	}

	public void setVin(String value) {
		vin = value;
	}
	
	public void setModel(String value)
	{
		model = value;
	}

	public void setDescription(String value) {
		description = value;
	}

	public void setCarId(long value) {
		carId = value;
	}

	public void setDatetimeStart(Timestamp value) {
		datetimeStart = value;
	}

	public void setDatetimeEnd(Timestamp value) {
		datetimeEnd = value;
	}

}
