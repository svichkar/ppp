package com.nixsolutions.serviceStation.dbObjects;

import java.util.Date;

public class Order_in_work {
	private Integer order_id;
	private String description;
	private Date datetime_start;
	private Date datetime_finish;
	private String order_status;
	private Car car;

	public Order_in_work(Integer order_id, String description, Date datetime_start, Date datetime_finish, String order_status,Car car) {
		this.order_id = order_id;
		this.description = description;
		this.datetime_start = datetime_start;
		this.datetime_finish = datetime_finish;
		this.order_status = order_status;
		this.car=car;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatetime_start() {
		return datetime_start;
	}

	public void setDatetime_start(Date datetime_start) {
		this.datetime_start = datetime_start;
	}

	public Date getDatetime_finish() {
		return datetime_finish;
	}

	public void setDatetime_finish(Date datetime_finish) {
		this.datetime_finish = datetime_finish;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	
}
