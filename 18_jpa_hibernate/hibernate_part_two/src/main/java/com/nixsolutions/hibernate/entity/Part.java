package com.nixsolutions.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Part implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "part_id")
	private Long partId;
	@Column(name = "part_name", length = 250, nullable = false)
	private String partName;
	@Column(name = "vendor", length = 100, nullable = false)
	private String vendor;
	@Column(name = "amount", nullable = false)
	private Long amount;

	public Long getPartId() {
		return partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Part(String partName, String vendor, Long amount) {
		this.partName = partName;
		this.vendor = vendor;
		this.amount = amount;
	}
	
	public Part() {
		this("", "", 0L);
	}

}
