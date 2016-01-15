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
	private int order_status_id;
	@Column(name = "order_status_name", nullable = false, length = 255)
	private String order_status_name;

	public OrderStatus() {

	}

	public int getOrderStatusId() {
		return order_status_id;
	}

	public String getOrder_status_name() {
		return order_status_name;
	}

	public void setOrderStatusId(int value) {
		order_status_id = value;
	}

	public void setOrder_status_name(String value) {
		order_status_name = value;
	}
}
