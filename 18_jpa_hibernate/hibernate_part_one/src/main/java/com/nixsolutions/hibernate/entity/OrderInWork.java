package com.nixsolutions.hibernate.entity;

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
import javax.persistence.Table;

@Entity
@Table(name="order_in_work")
public class OrderInWork implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderId;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id")
	private OrderStatus orderStatus;
	@Column(name = "description", length = 250, nullable = false)
	private String description;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id", referencedColumnName = "car_id")
	private Car car;
	@Column(name = "timestamp_start", nullable= false, columnDefinition = "timestamp default current_timestamp()")
	private Timestamp timestampStart;
	@Column(name = "timestamp_finish")
	private Timestamp timestampFinish;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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

	public Timestamp getTimestampStart() {
		return timestampStart;
	}

	public void setTimestampStart(Timestamp timestampStart) {
		this.timestampStart = timestampStart;
	}

	public Timestamp getTimestampFinish() {
		return timestampFinish;
	}

	public void setTimestampFinish(Timestamp timestampFinish) {
		this.timestampFinish = timestampFinish;
	}
}
