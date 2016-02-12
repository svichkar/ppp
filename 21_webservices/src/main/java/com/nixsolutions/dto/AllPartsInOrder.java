package com.nixsolutions.dto;

public class AllPartsInOrder {

	private long orderId;
	private long partId;
	private long amount;
	private String partName;
	private String vendor;

	public AllPartsInOrder() {

	}

	public long getId() {
		return orderId;
	}

	public long getPartId() {
		return partId;
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

	public void setId(long value) {
		orderId = value;
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

	@Override
	public boolean equals(Object obj) {
		AllPartsInOrder value = (AllPartsInOrder) obj;
		if (value.partName == this.partName & value.getId() == this.getId()) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		String resultHash = this.getPartName() + String.valueOf(this.getId());
		return resultHash.hashCode();
	}

}
