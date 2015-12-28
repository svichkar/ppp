package com.nixsolutions.entity;

import java.util.Date;

public class OrderInWork {
	private Integer orderId;
	private String orderDescription;
	private Date datetimeStart;
	private Date datetimeFinish;
	private Integer orderStatusId;
	private Integer carId;

	public OrderInWork(Integer orderId, String orderDescription, Date datetimeStart, Date datetimeFinish,
			Integer orderStatusId, Integer carId) {
		this.orderId = orderId;
		this.orderDescription = orderDescription;
		this.datetimeStart = datetimeStart;
		this.datetimeFinish = datetimeFinish;
		this.orderStatusId = orderStatusId;
		this.carId = carId;
	}

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

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	@Override
	public String toString() {
		return "Order_in_work [order_id=" + orderId + ", orderDescription=" + orderDescription + ", datetimeStart="
				+ datetimeStart + ", datetimeFinish=" + datetimeFinish + ", orderStatusId=" + orderStatusId
				+ ",  carId=" + carId + "]";
	}

}
