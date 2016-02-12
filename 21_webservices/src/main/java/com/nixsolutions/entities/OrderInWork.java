package com.nixsolutions.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderInWork implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderId;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id")
	private OrderStatus orderStatus;
	@Column(name = "description", nullable = false, length = 255)
	private String description;
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id", referencedColumnName = "car_id")
	private Car car;
	@Column(name = "datetime_start", nullable = false)
	private Timestamp datetimeStart;
	@Column(name = "datetime_end")
	private Timestamp datetimeEnd;

	public OrderInWork() {

	}
	
	public OrderInWork(OrderStatus orderStatus, String description, Car car,
			Timestamp datetimeStart, Timestamp datetimeEnd) {
		this.orderStatus = orderStatus;
		this.description = description;
		this.car = car;
		this.datetimeStart = datetimeStart;
		this.datetimeEnd = datetimeEnd;
	}

	public long getOrderInWorkId() {
		return orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public String getDescription() {
		return description;
	}

	public Car getCar() {
		return car;
	}

	public Timestamp getDatetimeStart() {
		return datetimeStart;
	}

	public Timestamp getDatetimeEnd() {
		return datetimeEnd;
	}

	public void setOrderInWorkId(long value) {
		orderId = value;
	}

	public void setOrderStatus(OrderStatus value) {
		orderStatus = value;
	}

	public void setDescription(String value) {
		description = value;
	}

	public void setCar(Car value) {
		car = value;
	}

	public void setDatetimeStart(Timestamp value) {
		datetimeStart = value;
	}

	public void setDatetimeEnd(Timestamp value) {
		datetimeEnd = value;
	}

}
