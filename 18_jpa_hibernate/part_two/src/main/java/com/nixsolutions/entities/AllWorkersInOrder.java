package com.nixsolutions.entities;

public class AllWorkersInOrder extends BaseEntity {

	private long worker_id;
	private long order_id;
	private boolean completed;
	private String f_name;
	private String l_name;
	
	public AllWorkersInOrder() {
		
	}

	public long getId() {
		return order_id;
	}

	public long getWorker_id() {
		return worker_id;
	}
	
	public String getF_name()
	{
		return f_name;
	}
	
	public String getL_name()
	{
		return l_name;
	}

	public boolean getCompleted() {
		return completed;
	}

	public void setId(long value) {
		order_id = value;
	}

	public void setWorker_id(long value) {
		worker_id = value;
	}

	public void setCompleted(boolean value) {
		completed = value;
	}
	
	public void setF_name(String value)
	{
		f_name = value;
	}
	
	public void setL_name(String value)
	{
		l_name = value;
	}

	
}
