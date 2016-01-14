package com.nixsolutions.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "order_in_work")
public class OrderInWork implements Serializable {
	
	public OrderInWork() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "order_description", length = 500, nullable = false)
	private String orderDescription;

	@Column(name = "datetime_start", columnDefinition = "DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetimeStart;

	@Column(name = "datetime_finish", columnDefinition = "DATETIME", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetimeFinish;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id")
	private OrderStatus orderStatus;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id", referencedColumnName = "car_id")
	private Car car;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public Date getDatetimeStart() {
		return datetimeStart;
	}

	public void setDatetimeStart(Date datetimeStart) {
		this.datetimeStart = datetimeStart;
	}

	public Date getDatetimeFinish() {
		return datetimeFinish;
	}

	public void setDatetimeFinish(Date datetimeFinish) {
		this.datetimeFinish = datetimeFinish;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((datetimeFinish == null) ? 0 : datetimeFinish.hashCode());
		result = prime * result + ((datetimeStart == null) ? 0 : datetimeStart.hashCode());
		result = prime * result + ((orderDescription == null) ? 0 : orderDescription.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
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
		OrderInWork other = (OrderInWork) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (datetimeFinish == null) {
			if (other.datetimeFinish != null)
				return false;
		} else if (!datetimeFinish.equals(other.datetimeFinish))
			return false;
		if (datetimeStart == null) {
			if (other.datetimeStart != null)
				return false;
		} else if (!datetimeStart.equals(other.datetimeStart))
			return false;
		if (orderDescription == null) {
			if (other.orderDescription != null)
				return false;
		} else if (!orderDescription.equals(other.orderDescription))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		return true;
	}

}
