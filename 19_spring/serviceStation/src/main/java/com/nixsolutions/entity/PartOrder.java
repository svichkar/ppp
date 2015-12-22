/**
 * 
 */
package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * @author mixeyes
 *
 */
@Entity
@Table(name = "part_order")
public class PartOrder implements Serializable{

	public PartOrder() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="part_order_id")
	private Integer partOrderId;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderInWork order;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "part_id", referencedColumnName = "part_id")
	private Part part;

	@Column(name = "amount", columnDefinition = "int default 0")
	private Integer amount;

	public Integer getPart_order_id() {
		return partOrderId;
	}

	public void setPart_order_id(Integer part_order_id) {
		this.partOrderId = part_order_id;
	}

	public OrderInWork getOrder() {
		return order;
	}

	public void setOrder(OrderInWork order) {
		this.order = order;
	}


	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((part == null) ? 0 : part.hashCode());
		result = prime * result + ((partOrderId == null) ? 0 : partOrderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartOrder other = (PartOrder) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (part == null) {
			if (other.part != null)
				return false;
		} else if (!part.equals(other.part))
			return false;
		if (partOrderId == null) {
			if (other.partOrderId != null)
				return false;
		} else if (!partOrderId.equals(other.partOrderId))
			return false;
		return true;
	}

}
