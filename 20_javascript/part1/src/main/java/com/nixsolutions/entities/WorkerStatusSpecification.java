package com.nixsolutions.entities;

public class WorkerStatusSpecification {

	private long worker_id;
	private String f_name;
	private String l_name;
	private long spec_id;
	private String spec_name;
	private long status_id;
	private String status_name;

	public WorkerStatusSpecification() {

	}

	public long getId() {
		return worker_id;
	}

	public String getF_name() {
		return f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public long getSpec_id() {
		return spec_id;
	}

	public long getStatus_id() {
		return status_id;
	}

	public String getSpec_name() {
		return spec_name;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setId(long value) {
		worker_id = value;
	}

	public void setF_name(String value) {
		f_name = value;
	}

	public void setL_name(String value) {
		l_name = value;
	}

	public void setSpec_id(long value) {
		spec_id = value;
	}

	public void setStatus_id(long value) {
		status_id = value;
	}

	public void setSpec_name(String value) {
		spec_name = value;
	}

	public void setStatus_name(String value) {
		status_name = value;
	}

	@Override
	public boolean equals(Object obj) {
		WorkerStatusSpecification value = (WorkerStatusSpecification) obj;
		if (value.worker_id == this.worker_id & value.spec_name == this.spec_name) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getF_name().hashCode();
	}
}
