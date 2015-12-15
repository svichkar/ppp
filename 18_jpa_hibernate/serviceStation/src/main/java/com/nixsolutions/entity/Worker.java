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

	public Worker() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="worker_id")
	private Integer workerId;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "specialization_id", referencedColumnName = "specialization_id")
	private WorkerSpecialization specialization;

	@Column(name = "first_name", length = 20)
	private String firstName;
	
	@Column(name = "last_name", length = 20)
	private String lastName;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "worker_status_id", referencedColumnName = "worker_status_id")
	private WorkerStatus wStatus;

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	public Integer getWorker_id() {
		return workerId;
	}

	public void setWorker_id(Integer worker_id) {
		this.workerId = worker_id;
	}

	public WorkerSpecialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(WorkerSpecialization specialization) {
		this.specialization = specialization;
	}

	public String getFirst_name() {
		return firstName;
	}

	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}

	public String getLast_name() {
		return lastName;
	}

	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}

	public WorkerStatus getWorker_status() {
		return wStatus;
	}

	public void setWorker_status(WorkerStatus worker_status) {
		this.wStatus = worker_status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}