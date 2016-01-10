package com.nixsolutions.entities;

public class OrderWorker extends BaseEntity {

	private int worker_id;
	private int order_id;
	private boolean completed;

	public OrderWorker(int worker_id, int order_id, boolean completed) {
		this.worker_id = worker_id;
		this.order_id = order_id;
		this.completed = completed;
	}
	
	public OrderWorker() {
		
	}

	public int getId() {
		return order_id;
	}

	public int getWorker_id() {
		return worker_id;
	}

	public boolean getCompleted() {
		return completed;
	}

	public void setId(int value) {
		order_id = value;
	}

	public void setWorker_id(int value) {
		worker_id = value;
	}

	public void setCompleted(boolean value) {
		completed = value;
	}

}
