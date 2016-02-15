package com.nixsolutions.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Part {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "part_id")
	private long partId;
	@Column(name = "part_name", nullable = false, length = 255)
	private String partName;
	@Column(name = "vendor", nullable = false, length = 255)
	private String vendor;
	@Column(name="amount", nullable=false)
	private long amount;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "part_id", referencedColumnName = "part_id")
	private Set<PartOrder> partsInOrders;
	

	public Part() {

	}
	
	public Part(String partName, String vendor, long amount)
	{
		this.partName = partName;
		this.vendor = vendor;
		this.amount = amount;
	}

	public long getPartId() {
		return partId;
	}

	public long getAmount() {
		return amount;
	}

	public String getPartName() {
		return partName;
	}

	public String getVendor() {
		return vendor;
	}
	
	public Set<PartOrder> getPartsInOrders()
	{
		return partsInOrders;
	}

	public void setPartId(long value) {
		partId = value;
	}

	public void setAmount(long value) {
		amount = value;
	}

	public void setPartName(String value) {
		partName = value;
	}

	public void setVendor(String value) {
		vendor = value;
	}
	
	public void setPartsInOrders(Set<PartOrder> value)
	{
		partsInOrders = value;
	}
	
}
