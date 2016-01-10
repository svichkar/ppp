package com.nixsolutions.entities;

public class Part extends BaseEntity{

	private int part_id;
	private String part_name;
	private String vendor;
	private int amount;

	public Part(String part_name, String vendor, int amount) {
		this.part_name = part_name;
		this.vendor = vendor;
		this.amount = amount;
	}
	
	public Part() {
		
	}

	public int getId() {
		return part_id;
	}

	public int getAmount() {
		return amount;
	}

	public String getPart_name() {
		return part_name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setId(int value) {
		part_id = value;
	}

	public void setAmount(int value) {
		amount = value;
	}

	public void setPart_name(String value) {
		part_name = value;
	}

	public void setVendor(String value) {
		vendor = value;
	}

}
