package com.nixsolutions.entities;

public class WorkerSpecification extends BaseEntity {

	private int spec_id;
	private String spec_name;

	public WorkerSpecification(String spec_name) {
		this.spec_name = spec_name;
	}

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
