package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_status")
public class OrderStatus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderStatus() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_status_id")
	private Integer orderStatusId;

	@Column(name = "order_status_name", length = 20, unique = true)
	private String orderStatusName;

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderStatusId == null) ? 0 : orderStatusId.hashCode());
		result = prime * result + ((orderStatusName == null) ? 0 : orderStatusName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderStatus other = (OrderStatus) obj;
		if (orderStatusId == null) {
			if (other.orderStatusId != null)
				return false;
		} else if (!orderStatusId.equals(other.orderStatusId))
			return false;
		if (orderStatusName == null) {
			if (other.orderStatusName != null)
				return false;
		} else if (!orderStatusName.equals(other.orderStatusName))
			return false;
		return true;
	}

}
