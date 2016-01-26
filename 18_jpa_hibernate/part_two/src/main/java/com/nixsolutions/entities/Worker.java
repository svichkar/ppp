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
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Worker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "worker_id")
	private long worker_id;
	@Column(name = "f_name", nullable = false, length = 255)
	private String f_name;
	@Column(name = "l_name", nullable = false, length = 255)
	private String l_name;
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

	public Worker(String f_name, String l_name, WorkerSpecification spec, Status status) {
		this.f_name = f_name;
		this.l_name = l_name;
		this.spec = spec;
		this.status = status;
	}

	public long getWorkerId() {
		return worker_id;
	}

	public String getF_name() {
		return f_name;
	}

	public String getL_name() {
		return l_name;
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
		worker_id = value;
	}

	public void setF_name(String value) {
		f_name = value;
	}

	public void setL_name(String value) {
		l_name = value;
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
