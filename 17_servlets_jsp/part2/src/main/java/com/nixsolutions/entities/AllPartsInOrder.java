package com.nixsolutions.entities;

public class AllPartsInOrder {

	private int order_id;
	private int part_id;
	private int amount;
	private String part_name;
	private String vendor;

	public AllPartsInOrder() {

	}

	public int getId() {
		return order_id;
	}

	public int getPart_id() {
		return part_id;
	}

	public String getPart_name() {
		return part_name;
	}

	public String getVendor() {
		return vendor;
	}

	public int getAmount() {
		return amount;
	}

	public void setId(int value) {
		order_id = value;
	}

	public void setPart_id(int value) {
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
