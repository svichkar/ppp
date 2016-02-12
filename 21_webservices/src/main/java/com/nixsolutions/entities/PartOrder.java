package com.nixsolutions.entities;

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

@Entity
public class PartOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "part_order_id")
	private long partOrderId;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderInWork order;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "part_id", referencedColumnName = "part_id")
	private Part part;
	private long amount;

	public PartOrder() {

	}
	
	public PartOrder(OrderInWork order, Part part, long amount)
	{
		this.order = order;
		this.part = part;
		this.amount = amount;
	}

	public OrderInWork getOrder() {
		return order;
	}

	public Part getPart() {
		return part;
	}

	public long getAmount() {
		return amount;
	}

	public void setOrder(OrderInWork value) {
		order = value;
	}

	public void setPart(Part value) {
		part = value;
	}

	public void setAmount(long value) {
		amount = value;
	}

}
