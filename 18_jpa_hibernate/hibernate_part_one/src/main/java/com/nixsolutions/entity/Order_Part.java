package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Order_Part implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private Order_In_Work order_id;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "part_id", referencedColumnName = "part_id")
	private Part part_id;
	@Column(name = "used_amount", columnDefinition = "int default 0")
	private int used_amount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Order_In_Work getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Order_In_Work order_id) {
		this.order_id = order_id;
	}

	public Part getPart_id() {
		return part_id;
	}

	public void setPart_id(Part part_id) {
		this.part_id = part_id;
	}

	public int getUsed_amount() {
		return used_amount;
	}

	public void setUsed_amount(int used_amount) {
		this.used_amount = used_amount;
	}
}
