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
@Table(name = "worker")
public class WorkerStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer worker_status_id;
	@Column(name = "specialization_name", length = 50, unique = true)
	private String worker_status_name;
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

}
