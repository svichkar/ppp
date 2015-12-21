package com.nixsolutions.hibernate.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_worker")
public class OrderWorker implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderInWork order;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "worker_id", referencedColumnName = "worker_id")
	private Worker worker;
	@Column(name = "is_completed")
	@Enumerated(EnumType.STRING)
	private IsCompletedValue isCompleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderInWork getOrder() {
		return order;
	}

	public void setOrder(OrderInWork order) {
		this.order = order;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public IsCompletedValue getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(IsCompletedValue isCompleted) {
		this.isCompleted = isCompleted;
	}

	public OrderWorker(OrderInWork order, Worker worker, IsCompletedValue isCompleted) {
		this.order = order;
		this.worker = worker;
		this.isCompleted = isCompleted;
	}
	
	public OrderWorker() {
		this(null, null, IsCompletedValue.N);
	}

}
