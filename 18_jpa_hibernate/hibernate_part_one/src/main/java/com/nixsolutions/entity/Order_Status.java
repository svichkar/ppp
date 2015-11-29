package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order_Status implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_status_id;
	@Column(name = "order_status_name", length = 25, nullable = false)
	private String order_status_name;

	public int getOrder_status_id() {
		return order_status_id;
	}

	public void setOrder_status_id(int order_status_id) {
		this.order_status_id = order_status_id;
	}

	public String getOrder_status_name() {
		return order_status_name;
	}

	public void setOrder_status_name(String order_status_name) {
		this.order_status_name = order_status_name;
	}
}
