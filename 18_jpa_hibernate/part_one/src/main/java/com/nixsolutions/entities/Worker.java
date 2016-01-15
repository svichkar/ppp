package com.nixsolutions.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Worker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "worker_id")
	private int worker_id;
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

	public Worker() {

	}

	public int getWorkerId() {
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

	public void setWorkerId(int value) {
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
}
