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
	private long part_id;
	@Column(name = "part_name", nullable = false, length = 255)
	private String part_name;
	@Column(name = "vendor", nullable = false, length = 255)
	private String vendor;
	@Column(name="amount", nullable=false)
	private long amount;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "part_id", referencedColumnName = "part_id")
	private Set<PartOrder> partsInOrders;
	

	public Part() {

	}
	
	public Part(String part_name, String vendor, long amount)
	{
		this.part_name = part_name;
		this.vendor = vendor;
		this.amount = amount;
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
	
	public Set<PartOrder> getPartsInOrders()
	{
		return partsInOrders;
	}

	public void setPartId(long value) {
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
	
	public void setPartsInOrders(Set<PartOrder> value)
	{
		partsInOrders = value;
	}
	
}
