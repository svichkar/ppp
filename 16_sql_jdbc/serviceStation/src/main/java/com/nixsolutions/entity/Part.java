/**
 * 
 */
package com.nixsolutions.entity;

/**
 * @author mixeyes
 *
 */
public class Part {
	private Integer part_id;
	private String part_name;
	private String vendor;
	private Integer amount;
	public Part(Integer part_id, String part_name, String vendor, Integer amount) {
		this.part_id = part_id;
		this.part_name = part_name;
		this.vendor = vendor;
		this.amount = amount;
	}
	public Integer getPart_id() {
		return part_id;
	}
	public void setPart_id(Integer part_id) {
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
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Part [part_id=" + part_id + ", part_name=" + part_name + ", vendor=" + vendor + ", amount=" + amount
				+ "]";
	}

	
}
