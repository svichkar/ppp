package com.nixsolutions.entity;

public class WorkerSpecialization {
	private Integer specializationId;
	private String specializationName;

	public WorkerSpecialization(Integer specializationId, String specializationName) {
		this.specializationId = specializationId;
		this.specializationName = specializationName;
	}

	public Integer getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Integer specializationId) {
		this.specializationId = specializationId;
	}

	public String getSpecialization_name() {
		return specializationName;
	}

	public void setSpecialization_name(String specializationName) {
		this.specializationName = specializationName;
	}

	@Override
	public String toString() {
		return "WorkerSpecialization [specializationId=" + specializationId + ", specializationName="
				+ specializationName + "]";
	}

}
