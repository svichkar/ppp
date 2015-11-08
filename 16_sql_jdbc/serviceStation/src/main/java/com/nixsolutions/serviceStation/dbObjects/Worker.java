/**
 * 
 */
package com.nixsolutions.serviceStation.dbObjects;

/**
 * @author Михаил
 *
 */
public class Worker {
	private Integer worker_id;
	private String specialization;
	private String first_name;
	private String last_name;
	
	public Worker(String specialization, String last_name, String first_name) {
		super();
		this.specialization = specialization;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	public Integer getWorker_id() {
		return worker_id;
	}
	public void setWorker_id(Integer worker_id) {
		this.worker_id = worker_id;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	@Override
	public String toString() {
		return "Worker [worker_id=" + worker_id + ", specialization=" + specialization + ", first_name=" + first_name
				+ ", last_name=" + last_name + "]";
	}
	
	
}
