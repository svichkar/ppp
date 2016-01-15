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

@Entity
public class OrderInWork implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int order_id;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id")
	private OrderStatus order_status;
	@Column(name = "description", nullable = false, length = 255)
	private String description;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id", referencedColumnName = "car_id")
	private Car car;
	@Column(name = "datetime_start", nullable = false)
	private Timestamp datetime_start;
	@Column(name = "datetime_end")
	private Timestamp datetime_end;

	public OrderInWork() {

	}

	public int getOrderInWorkId() {
		return order_id;
	}

	public OrderStatus getOrder_status() {
		return order_status;
	}

	public String getDescription() {
		return description;
	}

	public Car getCar() {
		return car;
	}

	public Timestamp getDatetime_start() {
		return datetime_start;
	}

	public Timestamp getDatetime_end() {
		return datetime_end;
	}

	public void setOrderInWorkId(int value) {
		order_id = value;
	}

	public void setOrder_status(OrderStatus value) {
		order_status = value;
	}

	public void setDescription(String value) {
		description = value;
	}

	public void setCar(Car value) {
		car = value;
	}

	public void setDatetime_start(Timestamp value) {
		datetime_start = value;
	}

	public void setDatetime_end(Timestamp value) {
		datetime_end = value;
	}

}
