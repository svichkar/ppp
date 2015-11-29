package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Order_Worker implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private Order_In_Work order_id;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "worker_id", referencedColumnName = "worker_id")
	private Worker worker_id;
	@Column(name = "is_completed")
	@Enumerated(EnumType.STRING)
	private IsCompletedValue is_completed;

	public Order_In_Work getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Order_In_Work order_id) {
		this.order_id = order_id;
	}

	public Worker getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(Worker worker_id) {
		this.worker_id = worker_id;
	}

	public IsCompletedValue getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(IsCompletedValue is_completed) {
		this.is_completed = is_completed;
	}

}
