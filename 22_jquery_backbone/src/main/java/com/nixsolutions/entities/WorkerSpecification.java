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
	private long specId;
	@Column(name = "spec_name", nullable = false, length = 255)
	private String specName;

	public WorkerSpecification() {
	}

	public long getSpecId() {
		return specId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecId(long value) {
		specId = value;
	}

	public void setSpecName(String value) {
		specName = value;
	}

}
