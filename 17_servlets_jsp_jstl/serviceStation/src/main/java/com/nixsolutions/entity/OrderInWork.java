package com.nixsolutions.entity;

import java.util.Date;

public class OrderInWork {
	private Integer order_id;
	private String order_description;
	private Date datetime_start;
	private Date datetime_finish;
	private Integer order_status_id;
	private Integer  car_id;
	public OrderInWork(Integer order_id, String order_description, Date datetime_start, Date datetime_finish,
			Integer order_status_id, Integer  car_id) {
		this.order_id = order_id;
		this.order_description = order_description;
		this.datetime_start = datetime_start;
		this.datetime_finish = datetime_finish;
		this.order_status_id = order_status_id;
		this.car_id =  car_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getOrder_description() {
		return order_description;
	}
	public void setOrder_description(String order_description) {
		this.order_description = order_description;
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
	public Integer getOrder_status_id() {
		return order_status_id;
	}
	public void setOrder_status_id(Integer order_status_id) {
		this.order_status_id = order_status_id;
	}
	public Integer getCar_id() {
		return  car_id;
	}
	public void setCar_id(Integer  car_id) {
		this.car_id =  car_id;
	}
	@Override
	public String toString() {
		return "Order_in_work [order_id=" + order_id + ", order_description=" + order_description + ", datetime_start="
				+ datetime_start + ", datetime_finish=" + datetime_finish + ", order_status_id=" + order_status_id
				+ ",  car_id=" +  car_id + "]";
	}
	
	
}
