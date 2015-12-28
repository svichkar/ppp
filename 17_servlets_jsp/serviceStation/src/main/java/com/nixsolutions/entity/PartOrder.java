/**
 * 
 */
package com.nixsolutions.entity;

/**
 * @author mixeyes
 *
 */
public class PartOrder {
	private Integer orderId;
	private Integer partId;
	private Integer amount;

	public PartOrder(Integer orderId, Integer partId, Integer amount) {
		this.orderId = orderId;
		this.partId = partId;
		this.amount = amount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getPartId() {
		return partId;
	}

	public void setPartId(Integer partId) {
		this.partId = partId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Part_order [order_id=" + orderId + ", partId=" + partId + ", amount=" + amount + "]";
	}

}
