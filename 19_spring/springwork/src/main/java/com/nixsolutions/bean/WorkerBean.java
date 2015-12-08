package com.nixsolutions.bean;

public class WorkerBean {
	private int workerId;
	private String firstName;
	private String lastName;
	private String workerSpecialization;
	private String status;

	public int getWorkerId() {
		return workerId;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWorkerSpecialization() {
		return workerSpecialization;
	}

	public void setWorkerSpecialization(String workerSpecialization) {
		this.workerSpecialization = workerSpecialization;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
