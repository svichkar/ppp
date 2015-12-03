package com.nixsolutions.bean;

public class PartFullInfo {
	private String part_id;
	private String part_name;
	private String vendor;
	private String quntityInOrder;
	private String totalQuantity;

	public String getPart_id() {
		return part_id;
	}

	public void setPart_id(String part_id) {
		this.part_id = part_id;
	}

	public String getPart_name() {
		return part_name;
	}

	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getQuntityInOrder() {
		return quntityInOrder;
	}

	public void setQuntityInOrder(String quntityInOrder) {
		this.quntityInOrder = quntityInOrder;
	}

	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
}
