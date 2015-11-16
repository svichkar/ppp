/**
 * 
 */
package com.nixsolutions.serviceStation.dbObjects;

/**
 * @author mixeyes
 *
 */
public class Part_order {
	private Integer order_id;
	private Integer part_id;
	private Integer amount;

	public Part_order(Integer order_id, Integer part_id, Integer amount) {
		this.order_id = order_id;
		this.part_id = part_id;
		this.amount = amount;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getPart_id() {
		return part_id;
	}

	public void setPart_id(Integer part_id) {
		this.part_id = part_id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Part_order [order_id=" + order_id + ", part_id=" + part_id + ", amount=" + amount + "]";
	}

}
