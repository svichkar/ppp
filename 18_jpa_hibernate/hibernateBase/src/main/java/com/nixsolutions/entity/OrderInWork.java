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
public class OrderInWork implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer order_id;

	@Column(name = "order_description", length = 500, nullable = false)
	private String order_description;

	@Column(name = "datetime_start",columnDefinition="DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime_start;

	@Column(name = "datetime_finish",columnDefinition="DATETIME", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime_finish;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id")
	private OrderStatus order_status;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id", referencedColumnName = "car_id")
	private Car car;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getOrder_description() {
		return order_description;
	}

	public void setOrder_description(String order_description) {
		this.order_description = order_description;
	}

	public Date getDatetime_start() {
		return datetime_start;
	}

	public void setDatetime_start(Date datetime_start) {
		this.datetime_start = datetime_start;
	}

	public Date getDatetime_finish() {
		return datetime_finish;
	}

	public void setDatetime_finish(Date datetime_finish) {
		this.datetime_finish = datetime_finish;
	}

	public OrderStatus getOrder_status() {
		return order_status;
	}

	public void setOrder_status(OrderStatus order_status) {
		this.order_status = order_status;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
