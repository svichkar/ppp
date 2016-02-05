package com.nixsolutions.entities;

import java.io.Serializable;

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
public class OrderWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_worker_id")
	private long order_worker_id;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "worker_id", referencedColumnName = "worker_id")
	private Worker worker;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderInWork order;

	private boolean completed;

	public OrderWorker() {

	}

	public OrderWorker(Worker worker, OrderInWork oiw, Boolean completed) {
		this.worker = worker;
		this.order = oiw;
		this.completed = completed;
	}

	public OrderInWork getOrderInWork() {
		return order;
	}

	public Worker getWorker() {
		return worker;
	}

	public boolean getCompleted() {
		return completed;
	}

	public void setOrderInWork(OrderInWork value) {
		order = value;
	}

	public void setWorker(Worker value) {
		worker = value;
	}

	public void setCompleted(boolean value) {
		completed = value;
	}

}
