package com.nixsolutions.dto;

public class WorkerStatusSpecification {

	private long workerId;
	private String fName;
	private String lName;
	private long specId;
	private String specName;
	private long statusId;
	private String statusName;

	public WorkerStatusSpecification() {

	}

	public long getId() {
		return workerId;
	}

	public String getFname() {
		return fName;
	}

	public String getLname() {
		return lName;
	}

	public long getSpecId() {
		return specId;
	}

	public long getStatusId() {
		return statusId;
	}

	public String getSpecName() {
		return specName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setId(long value) {
		workerId = value;
	}

	public void setFname(String value) {
		fName = value;
	}

	public void setLname(String value) {
		lName = value;
	}

	public void setSpecId(long value) {
		specId = value;
	}

	public void setStatusId(long value) {
		statusId = value;
	}

	public void setSpecName(String value) {
		specName = value;
	}

	public void setStatusName(String value) {
		statusName = value;
	}

	@Override
	public boolean equals(Object obj) {
		WorkerStatusSpecification value = (WorkerStatusSpecification) obj;
		if (value.workerId == this.workerId & value.specName == this.specName) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getFname().hashCode();
	}
}
