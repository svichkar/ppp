package com.nixsolutions.bean;

public class OrderPartBean {
	private long orderId;
	private String partName;
	private long usedAmount;
	private long partId;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public long getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(long usedAmount) {
		this.usedAmount = usedAmount;
	}

	public long getPartId() {
		return partId;
	}

	public void setPartId(long partId) {
		this.partId = partId;
	}

}
