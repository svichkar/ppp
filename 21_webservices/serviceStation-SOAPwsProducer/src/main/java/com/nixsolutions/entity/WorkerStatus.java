/**
 * 
 */
package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mixeyes
 *
 */
@Entity
@Table(name = "worker_status")
public class WorkerStatus implements Serializable{

	public WorkerStatus() {
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="worker_status_id")
	private Integer workerStatusId;
	
	@Column(name = "worker_status_name", length = 50, unique = true)
	private String workerStatusName;
	
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

}
