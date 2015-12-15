/**
 * 
 */
package com.nixsolutions.entity;

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
	public OrderWorker() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_worker_id")
	private Integer orderWorkerId;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderInWork order;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "worker_id", referencedColumnName = "worker_id")
	private Worker worker;

	@Column(name = "isCompleted", columnDefinition = "boolean default false")
	private boolean isCompleted;

	public Integer getOrder_worker_id() {
		return orderWorkerId;
	}

	public void setOrder_worker_id(Integer order_worker_id) {
		this.orderWorkerId = order_worker_id;
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

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isCompleted ? 1231 : 1237);
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((orderWorkerId == null) ? 0 : orderWorkerId.hashCode());
		result = prime * result + ((worker == null) ? 0 : worker.hashCode());
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
		OrderWorker other = (OrderWorker) obj;
		if (isCompleted != other.isCompleted)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (orderWorkerId == null) {
			if (other.orderWorkerId != null)
				return false;
		} else if (!orderWorkerId.equals(other.orderWorkerId))
			return false;
		if (worker == null) {
			if (other.worker != null)
				return false;
		} else if (!worker.equals(other.worker))
			return false;
		return true;
	}

}
