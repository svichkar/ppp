/**
 * 
 */
package com.nixsolutions.entity;

/**
 * @author Михаил
 *
 */
public class Worker {
	private Integer workerId;
	private Integer specializationId;
	private String firstName;
	private String lastName;
	private Integer workerStatusId;
	private Integer userId;

	public Worker(Integer workerId, Integer specializationId, String lastName, String firstName, Integer workerStatusId,
			Integer userId) {
		super();
		this.workerId = workerId;
		this.specializationId = specializationId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.workerStatusId = workerStatusId;
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Worker(Integer specializationId, String lastName, String firstName, Integer workerStatusId,
			Integer userId) {
		super();
		this.specializationId = specializationId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.workerStatusId = workerStatusId;
		this.userId = userId;
	}

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public Integer getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Integer specializationId) {
		this.specializationId = specializationId;
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

	public Integer getWorkerStatusId() {
		return workerStatusId;
	}

	public void setWorkerStatusId(Integer workerStatusId) {
		this.workerStatusId = workerStatusId;
	}

	@Override
	public String toString() {
		return "Worker [workerId=" + workerId + ", specializationId=" + specializationId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", workerStatusId=" + workerStatusId + "]";
	}

}