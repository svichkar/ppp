package com.nixsolutions.entities;

public class WorkerStatusSpecification {

	private int worker_id;
	private String f_name;
	private String l_name;
	private int spec_id;
	private String spec_name;
	private int status_id;
	private String status_name;

	public WorkerStatusSpecification() {

	}

	public int getId() {
		return worker_id;
	}

	public String getF_name() {
		return f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public int getSpec_id() {
		return spec_id;
	}

	public int getStatus_id() {
		return status_id;
	}
	
	public String getSpec_name()
	{
		return spec_name;
	}
	
	public String getStatus_name()
	{
		return status_name;
	}

	public void setId(int value) {
		worker_id = value;
	}

	public void setF_name(String value) {
		f_name = value;
	}

	public void setL_name(String value) {
		l_name = value;
	}

	public void setSpec_id(int value) {
		spec_id = value;
	}

	public void setStatus_id(int value) {
		status_id = value;
	}
	
	public void setSpec_name(String value)
	{
		spec_name = value;
	}
	
	public void setStatus_name(String value)
	{
		status_name = value;
	}
}
