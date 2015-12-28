/**
 * 
 */
package com.nixsolutions.entity;

/**
 * @author mixeyes
 *
 */
public class OrderWorker {
	private Integer orderId;
	private Integer workerId;
	private boolean isCompleted;
	
	public OrderWorker(Integer orderId, Integer workerId, boolean isCompleted) {
		this.orderId = orderId;
		this.workerId = workerId;
		this.isCompleted = isCompleted;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer worker_id) {
		this.workerId = worker_id;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public String toString() {
		return "Order_worker [order_id=" + orderId + ", workerId=" + workerId + ", isCompleted=" + isCompleted + "]";
	}

	
	
}
