package com.nixsolutions.entities;

public class AllPartsInOrder {

	private long order_id;
	private long part_id;
	private long amount;
	private String part_name;
	private String vendor;

	public AllPartsInOrder() {

	}

	public long getId() {
		return order_id;
	}

	public long getPart_id() {
		return part_id;
	}

	public String getPart_name() {
		return part_name;
	}

	public String getVendor() {
		return vendor;
	}

	public long getAmount() {
		return amount;
	}

	public void setId(long value) {
		order_id = value;
	}

	public void setPart_id(long value) {
		part_id = value;
	}

	public void setAmount(long value) {
		amount = value;
	}

	public void setPart_name(String value) {
		part_name = value;
	}

	public void setVendor(String value) {
		vendor = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		AllPartsInOrder value = (AllPartsInOrder) obj;
		if (value.part_name == this.part_name & value.getId() == this.getId()) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getPart_name().hashCode();
	}

}
