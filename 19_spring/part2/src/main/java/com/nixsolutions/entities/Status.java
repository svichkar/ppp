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
	private long statusId;
	@Column(name = "status_name", nullable = false, length = 255)
	private String statusName;

	public Status() {

	}

	public long getStatusId() {
		return statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusId(long value) {
		statusId = value;
	}

	public void setStatusName(String value) {
		statusName = value;
	}

}
