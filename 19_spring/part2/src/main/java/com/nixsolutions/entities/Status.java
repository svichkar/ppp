package com.nixsolutions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {

	@Id
	@Column(name = "status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long status_id;
	@Column(name = "status_name", nullable = false, length = 255)
	private String status_name;

	public Status() {

	}

	public long getStatusId() {
		return status_id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatusId(long value) {
		status_id = value;
	}

	public void setStatus_name(String value) {
		status_name = value;
	}

}
