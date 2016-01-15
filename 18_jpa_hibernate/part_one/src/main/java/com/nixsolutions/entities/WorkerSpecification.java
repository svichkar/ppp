package com.nixsolutions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WorkerSpecification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spec_id")
	private int spec_id;
	@Column(name = "spec_name", nullable = false, length = 255)
	private String spec_name;

	public WorkerSpecification() {
	}

	public int getId() {
		return spec_id;
	}

	public String getSpec_name() {
		return spec_name;
	}

	public void setId(int value) {
		spec_id = value;
	}

	public void setSpec_name(String value) {
		spec_name = value;
	}

}
