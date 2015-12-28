/**
 * 
 */
package com.nixsolutions.entity;

/**
 * @author mixeyes
 *
 */
public class Part {
	private Integer partId;
	private String partName;
	private String vendor;
	private Integer amount;

	public Part(Integer partId, String partName, String vendor, Integer amount) {
		this.partId = partId;
		this.partName = partName;
		this.vendor = vendor;
		this.amount = amount;
	}

	public Integer getPartId() {
		return partId;
	}

	public void setPartId(Integer partId) {
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Part [partId=" + partId + ", partName=" + partName + ", vendor=" + vendor + ", amount=" + amount + "]";
	}

}
