package com.nixsolutions.entity;

public class Part {

	private int partId;
	private String partName;
	private String vendor;
	private long amount;
	
	public int getId() {
		return partId;
	}
	
	public String getValuesCommaSeparated() {
		return "'" + partName + "', '" + vendor + "', " + amount;
	}
	
	public String getPartName() {
		return partName;
	}
	
	public String getVendor() {
		return vendor;
	}
	
	public long getAmount() {
		return amount;
	}
	
	public void setId(int value) {
		partId = value;
	}
	
	public void setPartName(String value) {
		partName = value;
	}
	
	public void setVendor(String value) {
		vendor = value;
	}
	
	public void setAmount(long value) {
		amount = value;
	}
	
	@Override
	public String toString() {
		String res = "part_name = '" + partName + "', vendor = '" + 
				vendor + "', amount = " + amount;
		return res;
	}
	
	public Part(String part_name, String vendor, long amount) {
		this.partName = part_name;
		this.vendor = vendor;
		this.amount = amount;
	}
	
	public Part() {
		this("", "", 0L);
	}
}
