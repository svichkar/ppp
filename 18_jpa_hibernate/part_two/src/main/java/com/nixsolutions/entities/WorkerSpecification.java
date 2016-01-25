package com.nixsolutions.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WorkerSpecification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spec_id")
	private long spec_id;
	@Column(name = "spec_name", nullable = false, length = 255)
	private String spec_name;

	public WorkerSpecification() {
	}

	public long getSpecId() {
		return spec_id;
	}

	public String getSpec_name() {
		return spec_name;
	}

	public void setSpecId(long value) {
		spec_id = value;
	}

	public void setSpec_name(String value) {
		spec_name = value;
	}

}
