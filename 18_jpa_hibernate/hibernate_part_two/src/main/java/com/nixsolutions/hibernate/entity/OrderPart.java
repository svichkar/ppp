package com.nixsolutions.hibernate.entity;

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

@Entity
@Table(name = "order_part")
public class OrderPart implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderInWork order;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "part_id", referencedColumnName = "part_id")
	private Part part;
	@Column(name = "used_amount", columnDefinition = "bigint default 0")
	private long usedAmount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getUsed_amount() {
		return usedAmount;
	}

	public void setUsedAmount(long usedAmount) {
		this.usedAmount = usedAmount;
	}
}
