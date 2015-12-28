/**
 * 
 */
package com.nixsolutions.entity;

/**
 * @author mixeyes
 *
 */
public class WorkerStatus {
	private Integer workerStatusId;
	private String workerStatusName;

	public WorkerStatus(Integer workerStatusId, String workerStatusName) {
		this.workerStatusId = workerStatusId;
		this.workerStatusName = workerStatusName;
	}

	public Integer getWorkerStatusId() {
		return workerStatusId;
	}

	public void setWorkerStatusId(Integer workerStatusId) {
		this.workerStatusId = workerStatusId;
	}

	public String getWorkerStatusName() {
		return workerStatusName;
	}

	public void setWorkerStatusName(String workerStatusName) {
		this.workerStatusName = workerStatusName;
	}

	@Override
	public String toString() {
		return "WorkerStatus [workerStatusId=" + workerStatusId + ", workerStatusName=" + workerStatusName
				+ "]";
	}

}
