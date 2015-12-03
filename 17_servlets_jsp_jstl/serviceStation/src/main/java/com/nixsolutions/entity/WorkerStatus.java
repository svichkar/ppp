/**
 * 
 */
package com.nixsolutions.entity;

/**
 * @author mixeyes
 *
 */
public class WorkerStatus {
	private Integer worker_status_id;
	private String worker_status_name;

	public WorkerStatus(Integer worker_status_id, String worker_status_name) {
		this.worker_status_id = worker_status_id;
		this.worker_status_name = worker_status_name;
	}
	public Integer getWorker_status_id() {
		return worker_status_id;
	}
	public void setWorker_status_id(Integer worker_status_id) {
		this.worker_status_id = worker_status_id;
	}
	public String getWorker_status_name() {
		return worker_status_name;
	}
	public void setWorker_status_name(String worker_status_name) {
		this.worker_status_name = worker_status_name;
	}
	@Override
	public String toString() {
		return "WorkerStatus [worker_status_id=" + worker_status_id + ", worker_status_name=" + worker_status_name
				+ "]";
	}

	

}
