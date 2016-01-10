package com.nixsolutions.entities;

import java.sql.Timestamp;

public class OrderInWork extends BaseEntity {

	private int order_id;
	private int order_status_id;
	private String description;
	private int car_id;
	private Timestamp datetime_start;
	private Timestamp datetime_end;

	public OrderInWork(int order_status_id, String description, int car_id,
			Timestamp datetime_start, Timestamp datetime_end) {
		this.order_status_id = order_status_id;
		this.description = description;
		this.car_id = car_id;
		this.datetime_start = datetime_start;
		this.datetime_end = datetime_end;
	}

	public OrderInWork() {

	}

	public int getId() {
		return order_id;
	}

	public int getOrder_status_id() {
		return order_status_id;
	}

	public String getDescription() {
		return description;
	}

	public int getCar_id() {
		return car_id;
	}

	public Timestamp getDatetime_start() {
		return datetime_start;
	}

	public Timestamp getDatetime_end() {
		return datetime_end;
	}

	public void setId(int value) {
		order_id = value;
	}

	public void setOrder_status_id(int value) {
		order_status_id = value;
	}

	public void setDescription(String value) {
		description = value;
	}

	public void setCar_id(int value) {
		car_id = value;
	}

	public void setDatetime_start(Timestamp value) {
		datetime_start = value;
	}

	public void setDatetime_end(Timestamp value) {
		datetime_end = value;
	}

}
