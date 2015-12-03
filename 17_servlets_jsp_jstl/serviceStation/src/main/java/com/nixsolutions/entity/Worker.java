/**
 * 
 */
package com.nixsolutions.entity;

/**
 * @author Михаил
 *
 */
public class Worker {
	private Integer worker_id;
	private Integer specialization_id;
	private String first_name;
	private String last_name;
	private Integer worker_status_id;
	private Integer user_id;
	
	public Worker(Integer worker_id, Integer specialization_id, String last_name, String first_name,
			Integer worker_status_id, Integer user_id) {
		super();
		this.worker_id = worker_id;
		this.specialization_id = specialization_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.worker_status_id = worker_status_id;
		this.user_id=user_id;
	}
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Worker(Integer specialization_id, String last_name, String first_name,
			Integer worker_status_id, Integer user_id) {
		super();
		this.specialization_id = specialization_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.worker_status_id = worker_status_id;
		this.user_id=user_id;
}
	public Integer getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(Integer worker_id) {
		this.worker_id = worker_id;
	}

	public Integer getSpecialization_id() {
		return specialization_id;
	}

	public void setSpecialization_id(Integer specialization_id) {
		this.specialization_id = specialization_id;
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

	public Integer getWorker_status_id() {
		return worker_status_id;
	}

	public void setWorker_status_id(Integer worker_status_id) {
		this.worker_status_id = worker_status_id;
	}

	@Override
	public String toString() {
		return "Worker [worker_id=" + worker_id + ", specialization_id=" + specialization_id + ", first_name="
				+ first_name + ", last_name=" + last_name + ", worker_status_id=" + worker_status_id + "]";
	}
	
	
	
}