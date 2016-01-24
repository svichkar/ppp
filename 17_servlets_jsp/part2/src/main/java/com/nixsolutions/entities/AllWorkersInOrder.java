package com.nixsolutions.entities;

public class AllWorkersInOrder extends BaseEntity {

	private int worker_id;
	private int order_id;
	private boolean completed;
	private String f_name;
	private String l_name;
	
	public AllWorkersInOrder() {
		
	}

	public int getId() {
		return order_id;
	}

	public int getWorker_id() {
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

	public void setId(int value) {
		order_id = value;
	}

	public void setWorker_id(int value) {
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
