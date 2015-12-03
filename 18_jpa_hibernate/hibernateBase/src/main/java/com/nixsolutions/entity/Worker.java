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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Михаил
 *
 */
@Entity
@Table(name = "worker")
public class Worker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer worker_id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "specialization_id", referencedColumnName = "specialization_id")
	private WorkerSpecialization specialization;

	@Column(name = "first_name", length = 20)
	private String first_name;
	@Column(name = "last_name", length = 20)
	private String last_name;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "worker_status_id", referencedColumnName = "worker_status_id")
	private WorkerStatus worker_status;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	public Integer getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(Integer worker_id) {
		this.worker_id = worker_id;
	}

	public WorkerSpecialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(WorkerSpecialization specialization) {
		this.specialization = specialization;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public WorkerStatus getWorker_status() {
		return worker_status;
	}

	public void setWorker_status(WorkerStatus worker_status) {
		this.worker_status = worker_status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}