package com.nixsolutions.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Worker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "worker_id")
	private long workerId;
	@Column(name = "f_name", nullable = false, length = 255)
	private String fName;
	@Column(name = "l_name", nullable = false, length = 255)
	private String lName;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "spec_id", referencedColumnName = "spec_id")
	private WorkerSpecification spec;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", referencedColumnName = "status_id")
	private Status status;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "worker_id", referencedColumnName = "worker_id")
	private Set<OrderWorker> workerInOrder;

	public Worker() {

	}

	public Worker(String fName, String lName, WorkerSpecification spec, Status status) {
		this.fName = fName;
		this.lName = lName;
		this.spec = spec;
		this.status = status;
	}

	public long getWorkerId() {
		return workerId;
	}

	public String getFname() {
		return fName;
	}

	public String getLname() {
		return lName;
	}

	public WorkerSpecification getSpec() {
		return spec;
	}

	public Status getStatus() {
		return status;
	}

	public Set<OrderWorker> getWorkerInOrder() {
		return workerInOrder;
	}

	public void setWorkerId(long value) {
		workerId = value;
	}

	public void setFname(String value) {
		fName = value;
	}

	public void setLname(String value) {
		lName = value;
	}

	public void setSpec(WorkerSpecification value) {
		spec = value;
	}

	public void setStatus(Status value) {
		status = value;
	}

	public void setWorkerInOrder(Set<OrderWorker> value) {
		workerInOrder = value;
	}
}
