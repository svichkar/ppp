package com.nixsolutions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Part {

	@Id
	@Column(name = "part_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long part_id;
	@Column(name = "part_name", nullable = false, length = 255)
	private String part_name;
	@Column(name = "vendor", nullable = false, length = 255)
	private String vendor;
	@Column(name="amount", nullable=false)
	private long amount;

	public Part() {

	}

	public long getPartId() {
		return part_id;
	}

	public long getAmount() {
		return amount;
	}

	public String getPart_name() {
		return part_name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setPartId(int value) {
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
