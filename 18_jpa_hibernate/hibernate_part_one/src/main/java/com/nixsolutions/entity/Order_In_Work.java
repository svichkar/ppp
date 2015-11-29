package com.nixsolutions.entity;

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

@Entity
public class Order_In_Work implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long order_id;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id")
	private Order_Status order_status;
	@Column(name = "description", length = 250, nullable = false)
	private String description;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id", referencedColumnName = "car_id")
	private Car car;
	@Column(name = "timestamp_start", nullable= false, columnDefinition = "timestamp default current_timestamp()")
	private Timestamp timestamp_start;
	@Column(name = "timestamp_finish")
	private Timestamp timestamp_finish;

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public Order_Status getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Order_Status order_status) {
		this.order_status = order_status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Timestamp getTimestamp_start() {
		return timestamp_start;
	}

	public void setTimestamp_start(Timestamp timestamp_start) {
		this.timestamp_start = timestamp_start;
	}

	public Timestamp getTimestamp_finish() {
		return timestamp_finish;
	}

	public void setTimestamp_finish(Timestamp timestamp_finish) {
		this.timestamp_finish = timestamp_finish;
	}
}
