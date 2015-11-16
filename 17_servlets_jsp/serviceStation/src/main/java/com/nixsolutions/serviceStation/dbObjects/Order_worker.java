/**
 * 
 */
package com.nixsolutions.serviceStation.dbObjects;

/**
 * @author mixeyes
 *
 */
public class Order_worker {
	private Integer order_id;
	private Integer worker_id;
	private boolean isCompleted;
	
	public Order_worker(Integer order_id, Integer worker_id, boolean isCompleted) {
		this.order_id = order_id;
		this.worker_id = worker_id;
		this.isCompleted = isCompleted;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(Integer worker_id) {
		this.worker_id = worker_id;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public String toString() {
		return "Order_worker [order_id=" + order_id + ", worker_id=" + worker_id + ", isCompleted=" + isCompleted + "]";
	}

	
	
}
