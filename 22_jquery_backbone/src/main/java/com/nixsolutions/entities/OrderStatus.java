package com.nixsolutions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderStatus {

	@Id
	@Column(name = "order_status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderStatusId;
	@Column(name = "order_status_name", nullable = false, length = 255)
	private String orderStatusName;

	public OrderStatus() {

	}

	public long getOrderStatusId() {
		return orderStatusId;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusId(long value) {
		orderStatusId = value;
	}

	public void setOrderStatusName(String value) {
		orderStatusName = value;
	}
}
