package com.nixsolutions.dto;

public class AllWorkersInOrder extends BaseEntity {

	private long workerId;
	private long orderId;
	private boolean completed;
	private String fName;
	private String lName;
	
	public AllWorkersInOrder() {
		
	}

	public long getId() {
		return orderId;
	}

	public long getWorkerId() {
		return workerId;
	}
	
	public String getFname()
	{
		return fName;
	}
	
	public String getLname()
	{
		return lName;
	}

	public boolean getCompleted() {
		return completed;
	}

	public void setId(long value) {
		orderId = value;
	}

	public void setWorkerId(long value) {
		workerId = value;
	}

	public void setCompleted(boolean value) {
		completed = value;
	}
	
	public void setFname(String value)
	{
		fName = value;
	}
	
	public void setLname(String value)
	{
		lName = value;
	}

	
}
