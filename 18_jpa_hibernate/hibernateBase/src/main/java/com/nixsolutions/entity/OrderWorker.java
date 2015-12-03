/**
 * 
 */
package com.nixsolutions.entity;

import java.io.Serializable;
import java.util.Set;

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

/**
 * @author mixeyes
 *
 */
@Entity
@Table(name = "order_worker")
public class OrderWorker implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer order_worker_id;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderInWork order;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "worker_id", referencedColumnName = "worker_id")
	private Set<Worker> worker;

	@Column(name = "isCompleted", columnDefinition = "boolean default false")
	private boolean isCompleted;

	public Integer getOrder_worker_id() {
		return order_worker_id;
	}

	public void setOrder_worker_id(Integer order_worker_id) {
		this.order_worker_id = order_worker_id;
	}

	public OrderInWork getOrder() {
		return order;
	}

	public void setOrder(OrderInWork order) {
		this.order = order;
	}

	public Set<Worker> getWorker() {
		return worker;
	}

	public void setWorker(Set<Worker> worker) {
		this.worker = worker;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
