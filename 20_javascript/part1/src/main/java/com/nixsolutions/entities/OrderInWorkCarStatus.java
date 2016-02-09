package com.nixsolutions.entities;

import java.sql.Timestamp;

public class OrderInWorkCarStatus {

	private long order_id;
	private long order_status_id;
	private String order_status_name;
	private String description;
	private long car_id;
	private String model;
	private String vin;
	private Timestamp datetime_start;
	private Timestamp datetime_end;

	public OrderInWorkCarStatus(long order_status_id, String description, long car_id, String model, String vin,
			String order_status_name, Timestamp datetime_start, Timestamp datetime_end) {
		this.order_status_id = order_status_id;
		this.description = description;
		this.car_id = car_id;
		this.model = model;
		this.vin = vin;
		this.order_status_name = order_status_name;
		this.datetime_start = datetime_start;
		this.datetime_end = datetime_end;
	}

	public OrderInWorkCarStatus() {

	}

	public long getId() {
		return order_id;
	}

	public long getOrder_status_id() {
		return order_status_id;
	}

	public String getOrder_status_name() {
		return order_status_name;
	}

	public String getDescription() {
		return description;
	}

	public long getCar_id() {
		return car_id;
	}

	public String getModel() {
		return model;
	}

	public String getVin() {
		return vin;
	}

	public Timestamp getDatetime_start() {
		return datetime_start;
	}

	public Timestamp getDatetime_end() {
		return datetime_end;
	}

	public void setId(long value) {
		order_id = value;
	}

	public void setOrder_status_id(long value) {
		order_status_id = value;
	}

	public void setOrder_status_name(String value) {
		order_status_name = value;
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

	public void setCar_id(long l) {
		car_id = l;
	}

	public void setDatetime_start(Timestamp value) {
		datetime_start = value;
	}

	public void setDatetime_end(Timestamp value) {
		datetime_end = value;
	}

}
