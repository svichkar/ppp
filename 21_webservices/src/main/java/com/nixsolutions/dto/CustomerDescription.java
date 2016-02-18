package com.nixsolutions.dto;

public class CustomerDescription {

	private String description;
	private long customerId;
	private String fName;
	private String lName;

	public CustomerDescription(long customerId, String fName, String lName, String Description) {
		this.customerId = customerId;
		this.fName = fName;
		this.lName = lName;
		this.description = Description;
	}

	public CustomerDescription() {

	}

	
	public String getDescription() {
		return description;
	}

	public long getCustomerId() {
		return customerId;
	}

	public String getFname() {
		return fName;
	}

	public String getLname() {
		return lName;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public void setCustomerId(long value) {
		this.customerId = value;
	}

	public void setFname(String value) {
		this.fName = value;
	}

	public void setLname(String value) {
		this.lName = value;
	}

}
